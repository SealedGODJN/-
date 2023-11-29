package com.NPU.分布式实验.实验3;

import java.io.PrintWriter;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

/**
 * class <em>Server</em> represents a distributed calendar server written using
 * RMI.
 *
 * @author wben
 * @version 1.0
 */

public class Server {

    /**
     * Output is written to the screen (standard out)
     */
    static PrintWriter screen = new PrintWriter(System.out, true);

    /**
     * main method.
     */
    public static void main(String[] args) {

        int portNumber = 0;
        if ((args.length == 1)) {
            portNumber = Integer.parseInt(args[0]);
        } else {
            screen.println("Usage: Server port");
            System.exit(0);
        }

        try {
            // 1.create rmi
            Registry registry = LocateRegistry.createRegistry(portNumber);

            /*
             * Create an instance of the agenda implementation.
             */
            AgendaImpl calendar = new AgendaImpl();

            /*
             * Bind the agenda.
             */
            registry.rebind("SSD8Exercise3", calendar);
            screen.println("Server is running");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
