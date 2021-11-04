package com.DesignPattern.FlyWeightPattern;

public class FlyweightClient {

    public static void main(String[] args) {
        //下黑子
        Chess backChess1 = ChessFactory.getChess(Color.BLACK);
        backChess1.draw(2, 5);

        //下白子
        Chess whiteChess = ChessFactory.getChess(Color.WHITE);
        whiteChess.draw(3, 5);

        //下黑子
        Chess backChess2 = ChessFactory.getChess(Color.BLACK);
        backChess2.draw(2, 6);

        System.out.println(String.format("backChess1:%d | backChess2:%d | whiteChess:%d",
                backChess1.hashCode(), backChess2.hashCode(), whiteChess.hashCode()));
        // backChess1:644117698 | backChess2:644117698 | whiteChess:1872034366
        // 两个黑棋的hashcode一样，说明是同一个对象的复用
        // 不论棋盘上有多少颗棋子，程序中只会保持最多两个棋子对象，这就极大的节约了内存
    }
}
