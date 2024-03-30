package edu.csf.oop.java.chess.board;
import edu.csf.oop.java.chess.pieces.Piece;

public class Tile {
    private Piece piece = null;
    private int x;
    private int y;

    public Tile(){}

    public Tile(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public Piece getPiece() {
        return piece;
    }

    public void setPiece(Piece piece) {
        this.piece = piece;
        if (piece != null){
            this.piece.setX(x);
            this.piece.setY(y);
        }
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    @Override
    public boolean equals(Object o){
        if (o.getClass() != this.getClass()){
            return false;
        } else{
            Tile tile = (Tile) o;
            return x == tile.getX() && y == tile.getY();
        }
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }
}
