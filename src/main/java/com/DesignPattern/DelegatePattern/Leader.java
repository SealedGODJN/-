package com.DesignPattern.DelegatePattern;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class Leader {

    private final Map<String, Employee> register = new HashMap<>();

    public Leader() {
        register.put("writing", new EmployeeW());
        register.put("coding", new EmployeeC());
    }

    public void doing(String command) {
        Optional.ofNullable(register.get(command)).orElseThrow(() -> new RuntimeException("命令不存在")).working(command);
    }
}