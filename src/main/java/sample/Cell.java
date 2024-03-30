package sample;

import edu.csf.oop.java.chess.Game;
import edu.csf.oop.java.chess.board.Board;

public class Cell {
//    private Board board;
    private Game game;

    public Cell() {
    }

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }

//    public Board getBoard() {
//        return board;
//    }
//
//    public void setBoard(Board board) {
//        this.board = board;
//    }
}
