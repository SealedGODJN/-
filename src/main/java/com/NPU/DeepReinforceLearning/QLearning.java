package com.NPU.DeepReinforceLearning;

import java.util.*;

public class QLearning {
    
    private Map<State, Map<Integer, Double>> Qs;

    public QLearning() {
        Qs = new HashMap<>();
    }

    private State getState(int t, Map<Pair, List<QueueResource>> queue_resources, List<Flow> F) {
        State state = new State();
        state.t = t;
        state.queue_resources = queue_resources;
        state.nodes = F.size();
        state.offsets = new ArrayList<>();
        for (Flow f : F) {
            state.offsets.add(f.offset);
        }
        return state;
    }

    private List<Integer> getAction(Map<State, Map<Integer, Double>> Q, State state, double epsilon) {
        List<Integer> action = new ArrayList<>();
        Random random = new Random();
        int length = random.nextInt(state.offsets.size());
        for (int i = 0; i < length; i++) {
            double randomValue = random.nextDouble();
            if (randomValue < epsilon) {
                action.add(random.nextInt(10));
            } else {
                Map<Integer, Double> qValues = Q.getOrDefault(state, new HashMap<>());
                int offset = Collections.max(qValues.entrySet(), Map.Entry.comparingByValue()).getKey();
                action.add(offset);
            }
        }
        return action;
    }

    private double getReward(List<Flow> flows, NetworkTopology networkTopology, int T_slot,
                             Map<Pair, List<QueueResource>> queue_resources) {
        double reward = 0;
        for (Flow f : flows) {
            if (MSS.count_e2e_latency(f, f.offset, T_slot) > f.deadline) {
                reward += -1;
            } else if (MSS.is_queue_overflow(f, f.offset, networkTopology, queue_resources)) {
                reward += -1;
            } else {
                reward += 1;
            }
        }
        return reward;
    }

    public List<Integer> qLearning(List<Flow> F, NetworkTopology network) {
        double learning_rate = 0.1;
        double discount_factor = 0.9;
        double epsilon = 0.1;
        double total_reward = 0;

        FlowSet fs = new FlowSet();
        fs.flows = F;
        int T_slot = tools.time_slot(fs);
        int comm_mul = tools.com_mul(fs, network.period);

        Map<Pair, List<QueueResource>> queue_resources = new HashMap<>();
        for (Node node : network.nodes) {
            for (int t = 0; t < comm_mul / T_slot; t++) {
                queue_resources.put(new Pair(node.id, t), Arrays.asList(node.src_queue, node.dst_queue));
            }
        }

        int t = 0;
        State state = getState(t, queue_resources, F);

        while (t < 100 * comm_mul) {
            List<Integer> action = getAction(Qs, state, epsilon);
            int i = 0;
            for (Flow f : F) {
                f.offset = action.get(i++);
                f.scheduleFlag = Result.SUCCESS;
            }

            t += T_slot;
            State next_state = getState(t, queue_resources, F);

            double reward = getReward(F, network, T_slot, queue_resources);
            total_reward += reward;

            Map<Integer, Double> q_values = Qs.getOrDefault(state, new HashMap<>());
            for (int a : action) {
                double max_q_value = Collections.max(q_values.values());
                q_values.put(a, q_values.getOrDefault(a, 0.0) + learning_rate * (reward + discount_factor * max_q_value - q_values.getOrDefault(a, 0.0)));
            }

            state = next_state;
        }

        List<Integer> best_offsets = new ArrayList<>();
        for (Flow f : F) {
            Map<Integer, Double> q_values = Qs.getOrDefault(getState(0, queue_resources, F), new HashMap<>());
            int offset = Collections.max(q_values.entrySet(), Map.Entry.comparingByValue()).getKey();
            best_offsets.add(offset);
        }

        return best_offsets;
    }
}

class State implements Comparable<State> {
    int t;
    int nodes;
    Map<Pair, List<QueueResource>> queue_resources;
    List<Integer> offsets;

    @Override
    public int compareTo(State other) {
        if (t != other.t) {
            return Integer.compare(t, other.t);
        } else {
            int q_r = 0;
            int o_q_r = 0;
            for (int i = 0; i < nodes; i++) {
                q_r += queue_resources.get(new Pair(i, t)).get(1).capacity;
                o_q_r += other.queue_resources.get(new Pair(i, t)).get(1).capacity;
            }
            return Integer.compare(q_r, o_q_r);
        }
    }
}


class QueueResource {
    int capacity;
}

class NetworkTopology {
    List<Node> nodes;
    int period;
}

class Flow {
    int offset;
    int deadline;
    int scheduleFlag;
}

class FlowSet {
    List<Flow> flows;
}

class Node {
    int id;
    QueueResource src_queue;
    QueueResource dst_queue;
}

class MSS {
    static int count_e2e_latency(Flow f, int offset, int T_slot) {
        // Implementation of count_e2e_latency
        return 0;
    }

    static boolean is_queue_overflow(Flow f, int offset, NetworkTopology networkTopology, Map<Pair, List<QueueResource>> queue_resources) {
        // Implementation of is_queue_overflow
        return false;
    }
}

class tools {
    static int time_slot(FlowSet fs) {
        // Implementation of time_slot
        return 0;
    }

    static int com_mul(FlowSet fs, int period) {
        // Implementation of com_mul
        return 0;
    }
}


class Pair {
    int first;
    int second;

    Pair(int first, int second) {
        this.first = first;
        this.second = second;
    }

    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pair pair = (Pair) o;
        return first == pair.first && second == pair.second;
    }

    public int hashCode() {
        return Objects.hash(first, second);
    }
}