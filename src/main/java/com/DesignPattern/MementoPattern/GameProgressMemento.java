package com.DesignPattern.MementoPattern;

public class GameProgressMemento {
    private int score;

    public GameProgressMemento(int score) {
        this.score = score;
    }

    public int getScore() {
        return score;
    }
}

