package com.DesignPattern.MementoPattern;

public class MementoClient {
    public void replayGame() {
        GameOriginator gameOriginator = new GameOriginator();
        GameCareTaker gameCareTaker = new GameCareTaker();

        //玩游戏
        gameOriginator.playGame();
        //保存进度
        gameCareTaker.saveMemento(gameOriginator.saveProcess());
        //退出游戏
        gameOriginator.exitGame();
        //重新打开游戏，恢复进度
        gameOriginator.restoreProcess(gameCareTaker.getMemento(0));
        gameOriginator.playGame();

    }

    public static void main(String[] args) {
        MementoClient mementoClient = new MementoClient();
        mementoClient.replayGame();
    }
}
