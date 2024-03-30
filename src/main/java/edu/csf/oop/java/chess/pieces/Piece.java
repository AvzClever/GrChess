package edu.csf.oop.java.chess.pieces;

import edu.csf.oop.java.chess.Alliance;
import edu.csf.oop.java.chess.board.Board;
import edu.csf.oop.java.chess.board.Move;
import edu.csf.oop.java.chess.board.Move.AttackMove;
import edu.csf.oop.java.chess.board.Move.MajorMove;
import edu.csf.oop.java.chess.board.Tile;
import org.codehaus.jackson.annotate.JsonSubTypes;
import org.codehaus.jackson.annotate.JsonTypeInfo;

import java.util.ArrayList;
import java.util.List;

@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.PROPERTY,
        property = "type")
@JsonSubTypes({
        @JsonSubTypes.Type(value = Bishop.class, name = "bishop"),
        @JsonSubTypes.Type(value = Giraffe.class, name = "giraffe"),
        @JsonSubTypes.Type(value = King.class, name = "king"),
        @JsonSubTypes.Type(value = Knight.class, name = "knight"),
        @JsonSubTypes.Type(value = Pawn.class, name = "pawn"),
        @JsonSubTypes.Type(value = Queen.class, name = "queen"),
        @JsonSubTypes.Type(value = Rook.class, name = "rook"),
        @JsonSubTypes.Type(value = Vizier.class, name = "vizier"),
        @JsonSubTypes.Type(value = WM.class, name = "wm"),
})
public abstract class Piece {
    private List<Integer[]> canGoFromThis = new ArrayList<>();
    private List<Integer[]> canAttackThis = new ArrayList<>();
    protected int x;
    protected int y;
    protected Alliance pieceAlliance;

    public Piece(List<Integer[]> canGoFromThis, List<Integer[]> canAttackThis, int x, int y, Alliance pieceAlliance) {
        this.canGoFromThis = canGoFromThis;
        this.canAttackThis = canAttackThis;
        this.x = x;
        this.y = y;
        this.pieceAlliance = pieceAlliance;
    }

    public Piece() {
    }

    public Alliance getPieceAlliance() {
        return pieceAlliance;
    }
    protected Piece(Alliance pieceAlliance, int x, int y) {
        this.pieceAlliance = pieceAlliance;
        this.x = x;
        this.y = y;
    }
    protected Piece(Alliance pieceAlliance) {
        this.pieceAlliance = pieceAlliance;
    }

    public abstract List<Move> calculateLegalMoves(final Board board);

    public void setLegalMoves(final Board board){
        List<Move> moveList = this.calculateLegalMoves(board);
        canGoFromThis.clear();
        canAttackThis.clear();

        System.out.print(this.getClass().toGenericString()+ ":  ");
        System.out.print(this.getX() + " ");
        System.out.print(this.getY() + "; ");

        for (Move value: moveList){
            if (value instanceof AttackMove) {
                canAttackThis.add(new Integer[]{value.getX(), value.getY()});
            } else if (value instanceof MajorMove) {
                canGoFromThis.add(new Integer[]{value.getX(), value.getY()});
                System.out.print(value.getX() + " ");
                System.out.print(value.getY() + " ");
            }
        }
        System.out.println();
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj.getClass() != this.getClass())
            return false;
        Piece piece = (Piece) obj;
        return x == piece.getX() && y == piece.getY() && pieceAlliance.equals(piece.getPieceAlliance());
    }

    public List<Integer[]> getCanGoFromThis() {
        return canGoFromThis;
    }

    public void setCanGoFromThis(List<Integer[]> canGoFromThis) {
        this.canGoFromThis = canGoFromThis;
    }

    public List<Integer[]> getCanAttackThis() {
        return canAttackThis;
    }

    public void setCanAttackThis(List<Integer[]> canAttackThis) {
        this.canAttackThis = canAttackThis;
    }
}