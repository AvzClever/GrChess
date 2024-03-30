package edu.csf.oop.java.chess.pieces;

import edu.csf.oop.java.chess.Alliance;
import edu.csf.oop.java.chess.board.Board;
import edu.csf.oop.java.chess.board.Move;
import edu.csf.oop.java.chess.board.Tile;
import org.codehaus.jackson.annotate.JsonTypeName;

import java.util.ArrayList;

import java.util.List;

import static edu.csf.oop.java.chess.board.Move.*;

@JsonTypeName("rook")
public class Rook extends Piece { // оладья

    private final int[][] CANDIDATE_MOVE_COORDINATES = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};

    public Rook(Alliance pieceAlliance) {
        super(pieceAlliance);
    }

    public Rook(Alliance pieceAlliance, int x, int y) {
        super(pieceAlliance, x, y);
    }

    public Rook(List<Integer[]> canGoFromThis, List<Integer[]> canAttackThis, int x, int y, Alliance pieceAlliance) {
        super(canGoFromThis, canAttackThis, x, y, pieceAlliance);
    }

    public Rook() {
    }
    public List<Integer[]> getCanGoFromThis() {
        return super.getCanGoFromThis();
    }
    public void setCanGoFromThis(List<Integer[]> canGoFromThis) {
        super.setCanGoFromThis(canGoFromThis);
    }

    public List<Integer[]> getCanAttackThis() {
        return super.getCanAttackThis();
    }

    public void setCanAttackThis(List<Integer[]> canAttackThis) {
        super.setCanAttackThis(canAttackThis);
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
    public List<Move> calculateLegalMoves(final Board board) {
        final List<Move> legalMoves = new ArrayList<>();
        for (final int[] candidateMove : CANDIDATE_MOVE_COORDINATES) {
            int x = this.x;
            int y = this.y;

            while (BoardUtils.isValidTileCoordinate(x, y)) {
                if (isFirstColumnExclusion(candidateMove, x) ||
                        isTenColumnExclusion(candidateMove, x)) {
                    break;
                }
                x += candidateMove[0];
                y += candidateMove[1];
                if (BoardUtils.isValidTileCoordinate(x, y)) {
                    final Piece pieceAtDestination = board.getTiles(x, y).getPiece();
                    if (pieceAtDestination == null) {
                        legalMoves.add(new MajorMove(board, this, x, y));
                    } else {
                        final Alliance pieceAtDestinationAllegiance = pieceAtDestination.getPieceAlliance();
                        if (this.pieceAlliance != pieceAtDestinationAllegiance) {
                            legalMoves.add(new AttackMove(board, this, x, y,
                                    pieceAtDestination));
                        }
                        break;
                    }
                }
            }
        }
        return legalMoves;
    }

    private boolean isFirstColumnExclusion(final int[] step,
                                                  final int x) {
        return BoardUtils.FIRST_COLUMN[x][0] && ((step == new int[]{0, -1}));
    }
    private boolean isTenColumnExclusion(final int[] step,
                                                final int x) {
        return BoardUtils.TEN_COLUMN[x][9] && ((step == new int[]{0, 1}));
    }
}
