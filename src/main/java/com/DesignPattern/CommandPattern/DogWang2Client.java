package com.DesignPattern.CommandPattern;

/**
 * 优点
 * 1、将调用操作与具体执行者解耦
 * 你只管发出命令，至于命令由谁执行你不用关心。我们也可以随时将命令中具体执行者换掉，发出命令者是不知道的。
 * 例如你大老板交给经理一个任务，至于经理安排小张，还是小王来做，他根本就不关心
 *
 * 2、添加一个命令非常容易
 * 很容易实现序列操作及实现回调系统
 * 你把命令加到一个列表中，迭代执行就可以实现序列操作了。
 * 因为Java不能将函数作为参数，此处我们可以将命令对象当做参数，而这个对象还可执行，所以就实现了回调功能。
 *
 * 缺点
 * 类太多，每次增加一个命令，就要多加一个类。
 */
public class DogWang2Client {
    //享受xa机器人的服务
    public void enjoySexRobot() {
//robot 控制系统，用户通过此系统来发出命令
        RobotInvoker invoker = new RobotInvoker();

        //生成脱衣命令
        FanBingBingReceiver bingBingReceiver = new FanBingBingReceiver();
        TuoXiongZhaoCommand tuoXiongZhaoCommand = new TuoXiongZhaoCommand(bingBingReceiver);
        TuoKuZiCommand tuoKuZiCommand = new TuoKuZiCommand(bingBingReceiver);
        //构建脱衣命令序列
        invoker.addCommands(tuoXiongZhaoCommand);
        invoker.addCommands(tuoKuZiCommand);
        //执行命令
        invoker.executeCommand();

        //生成跳舞命令
        YangMiReceiver yangMiReceiver = new YangMiReceiver();
        TiaoLaWuCommand tiaoLaWuCommand = new TiaoLaWuCommand(yangMiReceiver, "半小时");
        //构建跳舞命令
        invoker.clearCommand();
        invoker.addCommands(tiaoLaWuCommand);

        //执行命令
        invoker.executeCommand();
    }

    public static void main(String[] args) {
        DogWang2Client dogWang2Client = new DogWang2Client();
        dogWang2Client.enjoySexRobot();
    }
}
