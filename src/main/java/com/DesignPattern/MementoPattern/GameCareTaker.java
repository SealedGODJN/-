package com.DesignPattern.MementoPattern;

import java.util.ArrayList;
import java.util.List;

public class GameCareTaker {

    private List<GameProgressMemento> memento= new ArrayList<>();

    public void saveMemento(GameProgressMemento memento) {
        this.memento.add(memento);
    }

    public GameProgressMemento getMemento(int index) {
        return this.memento.get(index);
    }
}
