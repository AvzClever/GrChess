package edu.csf.oop.java.chess.pieces;

import edu.csf.oop.java.chess.Alliance;
import edu.csf.oop.java.chess.board.Board;
import edu.csf.oop.java.chess.board.Move;
import edu.csf.oop.java.chess.board.Tile;
import org.codehaus.jackson.annotate.JsonTypeName;

import java.util.ArrayList;
import java.util.List;

@JsonTypeName("pawn")
public class Pawn extends Piece {

    private int[][] CANDIDATE_MOVE_COORDINATES;
    private boolean firstMove;
    private int allianceConst = -1; // белые

    public Pawn(final Alliance allegiance) {
        super(allegiance);
    }

    public Pawn(Alliance pieceAlliance, int x, int y) {
        super(pieceAlliance, x, y);
        firstMove = true;
        if (pieceAlliance.isBlack())
            allianceConst = 1;
    }

    public Pawn(List<Integer[]> canGoFromThis, List<Integer[]> canAttackThis, int x, int y, Alliance pieceAlliance,
                int[][] CANDIDATE_MOVE_COORDINATES, boolean firstMove, int allianceConst) {
        super(canGoFromThis, canAttackThis, x, y, pieceAlliance);
        this.CANDIDATE_MOVE_COORDINATES = CANDIDATE_MOVE_COORDINATES;
        this.firstMove = firstMove;
        this.allianceConst = allianceConst;
    }

    public Pawn(int[][] CANDIDATE_MOVE_COORDINATES, boolean firstMove, int allianceConst) {
        this.CANDIDATE_MOVE_COORDINATES = CANDIDATE_MOVE_COORDINATES;
        this.firstMove = firstMove;
        this.allianceConst = allianceConst;
    }

    public Pawn() {
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
    public List<Move> calculateLegalMoves(Board board) {
//        System.out.println(this.firstMove);
        final List<Move> legalMoves = new ArrayList<>();

        CANDIDATE_MOVE_COORDINATES = new int[][]{{allianceConst, allianceConst}, {allianceConst, -allianceConst}};

        for (final int[] candidateMove : CANDIDATE_MOVE_COORDINATES) {
            int x = this.x + candidateMove[0];
            int y = this.y + candidateMove[1];
            if (BoardUtils.isValidTileCoordinate(x, y)) {
                final Piece pieceAtDestination = board.getTiles(x, y).getPiece();
                if (pieceAtDestination != null) {
                    final Alliance pieceAtDestinationAllegiance = pieceAtDestination.getPieceAlliance();
                    if (this.pieceAlliance != pieceAtDestinationAllegiance) {
                        legalMoves.add(new Move.AttackMove(board, this, x, y,
                                pieceAtDestination));
                    }
                }
            }
        }
        if (firstMove){
            int x = this.x + 2 * allianceConst;
            int y = this.y;
            final Piece pieceAtDestination = board.getTiles(x, y).getPiece();
            if (pieceAtDestination == null) {
                legalMoves.add(new Move.MajorMove(board, this, x, y));
            }
            firstMove = false;
        }
        int x = this.x + allianceConst;
        int y = this.y;
        if (BoardUtils.isValidTileCoordinate(x, y)) {
            final Piece pieceAtDestination = board.getTiles(x, y).getPiece();
            if (pieceAtDestination == null) {
                legalMoves.add(new Move.MajorMove(board, this, x, y));
            }
        }
        return legalMoves;
    }

    public boolean isFirstMove() {
        return firstMove;
    }

    public void setFirstMove(boolean firstMove) {
        this.firstMove = firstMove;
    }

    public int getAllianceConst() {
        return allianceConst;
    }

    public void setAllianceConst(int allianceConst) {
        this.allianceConst = allianceConst;
    }
}
