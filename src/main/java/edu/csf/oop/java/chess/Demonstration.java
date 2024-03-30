package edu.csf.oop.java.chess;

import edu.csf.oop.java.chess.board.Board;
import edu.csf.oop.java.chess.board.Tile;
import edu.csf.oop.java.chess.players.BlackPlayer;
import edu.csf.oop.java.chess.players.WhitePlayer;
import javafx.scene.chart.Axis;

public class Demonstration {
    Board board = new Board();
    WhitePlayer whitePlayer = new WhitePlayer(board);
    BlackPlayer blackPlayer = new BlackPlayer(board);

    private Tile tileGenerator(){
        Tile tile = board.getTiles((int) (Math.random() * 10), (int) (Math.random() * 10));
        while (tile.getPiece() == null){
            tile = board.getTiles((int) (Math.random() * 10), (int) (Math.random() * 10));
        }
        return tile;
    }

//    private void move(){
//
//    }
}
