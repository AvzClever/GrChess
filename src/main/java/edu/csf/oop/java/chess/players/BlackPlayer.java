package edu.csf.oop.java.chess.players;

import edu.csf.oop.java.chess.Alliance;
import edu.csf.oop.java.chess.board.Board;
import edu.csf.oop.java.chess.pieces.Piece;

import java.util.List;

public class BlackPlayer extends Player{
    public BlackPlayer(Board board) {
        super(board, Alliance.BLACK);
    }

    public BlackPlayer() {
    }
}
