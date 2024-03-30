package edu.csf.oop.java.chess.board;

import edu.csf.oop.java.chess.pieces.Piece;

public abstract class Move {

    final Board board;
    final Piece piece;
    final int x;
    final int y;


    public Move(Board board, Piece piece, int x, int y) {
        this.board = board;
        this.piece = piece;
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public static final class MajorMove extends Move {
        public MajorMove(final Board board, final Piece piece, int x, int y) {
            super(board, piece, x, y);
        }
    }

    public static final class AttackMove extends Move {
        final Piece attackPiece;

        public AttackMove(final Board board, final Piece piece, int x, int y, final Piece attackPiece) {
            super(board, piece, x, y);
            this.attackPiece = attackPiece;
        }
    }
}
