package edu.csf.oop.java.chess.pieces;


import edu.csf.oop.java.chess.Alliance;
import edu.csf.oop.java.chess.board.Board;
import edu.csf.oop.java.chess.board.Move;
import edu.csf.oop.java.chess.board.Tile;
import org.codehaus.jackson.annotate.JsonTypeName;

import java.util.ArrayList;
import java.util.List;
import static edu.csf.oop.java.chess.board.Move.*;

@JsonTypeName("king")
public class King extends Piece{

    private final int[][] CANDIDATE_MOVE_COORDINATES = {{- 1, -1}, {-1, 0}, {-1, 1}, {0, 1}, {1, 1}, {1, 0}, {1, -1}, {0, -1}};
    private boolean shah;


    public King(Alliance pieceAlliance) {
        super(pieceAlliance);
        shah = false;
    }

    public King(Alliance pieceAlliance, int x, int y) {
        super(pieceAlliance, x, y);
        shah = false;
    }

    public King(List<Integer[]> canGoFromThis, List<Integer[]> canAttackThis, int x, int y, Alliance pieceAlliance, boolean shah) {
        super(canGoFromThis, canAttackThis, x, y, pieceAlliance);
        this.shah = shah;
    }

    public King(boolean shah) {
        this.shah = shah;
    }

    public King() {}
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
//        System.out.println("King: ________________________");
        for (final int[] candidateMove : CANDIDATE_MOVE_COORDINATES) {
//            System.out.print(candidateMove[0] + " ");
//            System.out.print(candidateMove[1]  + ", ");
            int x = this.x;
            int y = this.y;
            if (BoardUtils.isValidTileCoordinate(x, y)) {
                if (isFirstColumnExclusion(candidateMove, x) ||
                        isTenColumnExclusion(candidateMove, x)) {
                    continue;
                }
                x = this.x + candidateMove[0];
                y = this.y + candidateMove[1];
                if (BoardUtils.isValidTileCoordinate(x, y)) {
                    final Piece pieceAtDestination = board.getTiles(x, y).getPiece();
                    if (pieceAtDestination == null) {
//                        System.out.print(x  + " ");
//                        System.out.print(y);
                        legalMoves.add(new MajorMove(board, this, x, y));
                    } else {
                        final Alliance pieceAtDestinationAllegiance = pieceAtDestination.getPieceAlliance();
                        if (this.pieceAlliance != pieceAtDestinationAllegiance) {
                            legalMoves.add(new AttackMove(board, this, x, y,
                                    pieceAtDestination));
                        }
                    }
//                    System.out.println();
                }
            }
        }
//        System.out.println("_______________________");
        return legalMoves;
    }

    public boolean isShah() {
        return shah;
    }

    public void setShah(boolean shah) {
        this.shah = shah;
    }


    private boolean isFirstColumnExclusion(final int[] step,
                                                  final int x) {
        return BoardUtils.FIRST_COLUMN[x][0] && ((step == new int[]{-1, -1})
                || (step == new int[]{0, -1}) || (step == new int[]{1, -1}));
    }

    private boolean isTenColumnExclusion(final int[] step,
                                                  final int x) {
        return BoardUtils.TEN_COLUMN[x][9] && ((step == new int[]{-1, 1})
                || (step == new int[]{0, 1}) || (step == new int[]{1, 1}));
    }
}
