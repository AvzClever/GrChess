package edu.csf.oop.java.chess.players;

import edu.csf.oop.java.chess.Alliance;
import edu.csf.oop.java.chess.board.Board;

public class WhitePlayer extends Player{

    public WhitePlayer(Board board) {
        super(board, Alliance.WHITE);
    }

    public WhitePlayer() {
    }
}
