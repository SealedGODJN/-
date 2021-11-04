package com.DesignPattern.FlyWeightPattern;

import java.util.HashMap;
import java.util.Map;

public class ChessFactory {
    private static final Map<Color, Chess> chessMap = new HashMap<>();

    public static Chess getChess(Color color) {
        Chess chess = chessMap.get(color);
        if (chess == null) {
            chess = color == Color.WHITE ? new WhiteChess() : new BlackChess();
            chessMap.put(color, chess);
        }
        return chess;
    }
}

