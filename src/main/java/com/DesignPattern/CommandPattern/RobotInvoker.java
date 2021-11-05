package com.DesignPattern.CommandPattern;

import java.util.ArrayList;
import java.util.List;

public class RobotInvoker {
    private final List<Command> sexRobotCommands = new ArrayList<>();

    public void clearCommand(){
        sexRobotCommands.clear();
    }

    //设置一套命令，不知道具体执行者是谁
    public void addCommands(Command command) {
        sexRobotCommands.add(command);
    }

    //执行脱衣系列命令
    public void executeCommand() {
        for (Command tuoYiCommand : sexRobotCommands) {
            tuoYiCommand.execute();
        }
    }
}
