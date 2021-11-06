package com.DesignPattern.DelegatePattern;

public class Boss {
    public void command(String command, Leader leader) {
        leader.doing(command);
    }

    public static void main(String[] args) {
        new Boss().command("writing", new Leader());
        new Boss().command("coding", new Leader());
    }
}