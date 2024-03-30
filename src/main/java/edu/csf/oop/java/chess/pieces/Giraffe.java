package edu.csf.oop.java.chess.pieces;


import edu.csf.oop.java.chess.Alliance;
import edu.csf.oop.java.chess.board.Board;
import edu.csf.oop.java.chess.board.Move;
import edu.csf.oop.java.chess.board.Tile;
import org.codehaus.jackson.annotate.JsonTypeName;

import java.util.ArrayList;
import java.util.List;

@JsonTypeName("giraffe")
public class Giraffe extends Piece { // ферзь и конь

    private final int[][] CANDIDATE_MOVE_COORDINATES_KNIGHT = {{-2, -1}, {-2, 1}, {-1, 2}, {1, 2},
                                                                {2, 1}, {2, -1}, {1, -2}, {-1, -2}};
    private final int[][] CANDIDATE_MOVE_COORDINATES_QUEEN = {{- 1, -1}, {-1, 0}, {-1, 1}, {0, 1}, {1, 1}, {1, 0}, {1, -1}, {0, -1}};

    public Giraffe(Alliance pieceAlliance) {
        super(pieceAlliance);
    }

    public Giraffe(List<Integer[]> canGoFromThis, List<Integer[]> canAttackThis, int x, int y, Alliance pieceAlliance) {
        super(canGoFromThis, canAttackThis, x, y, pieceAlliance);
    }

    public Giraffe() {
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

    public Giraffe(Alliance pieceAlliance, int x, int y) {
        super(pieceAlliance, x, y);
    }


    @Override
    public List<Move> calculateLegalMoves(final Board board) {
        final List<Move> legalMoves = new ArrayList<>();
        for (final int[] candidateMove : CANDIDATE_MOVE_COORDINATES_KNIGHT) {
            int x = this.x;
            int y = this.y;
            if(isFirstColumnExclusion(candidateMove, x) ||
                    isSecondColumnExclusion(candidateMove, x) ||
                    isNineColumnExclusion(candidateMove, x) ||
                    isTenColumnExclusion(candidateMove, x)) {
                continue;
            }
            x = this.x + candidateMove[0];
            y = this.y + candidateMove[1];
            if (BoardUtils.isValidTileCoordinate(x, y)) {
                final Piece pieceAtDestination = board.getTiles(x, y).getPiece();
                if (pieceAtDestination == null) {
                    legalMoves.add(new Move.MajorMove(board, this, x, y));
                } else {
                    final Alliance pieceAtDestinationAllegiance = pieceAtDestination.getPieceAlliance();
                    if (this.pieceAlliance != pieceAtDestinationAllegiance) {
                        legalMoves.add(new Move.AttackMove(board, this, x, y,
                                pieceAtDestination));
                    }
                }
            }
        }
        for (final int[] candidateMove : CANDIDATE_MOVE_COORDINATES_QUEEN) {
            int x = this.x;
            int y = this.y;

            while (true) {
                if (isFirstColumnExclusion(candidateMove, x) ||
                        isTenColumnExclusion(candidateMove, x)) {
                    break;
                }
                x += candidateMove[0];
                y += candidateMove[1];

                if (!BoardUtils.isValidTileCoordinate(x, y)) {
                    break;
                } else {
                    final Piece pieceAtDestination = board.getTiles(x, y).getPiece();
                    if (pieceAtDestination == null) {
                        legalMoves.add(new Move.MajorMove(board, this, x, y));
                    } else {
                        final Alliance pieceAtDestinationAllegiance = pieceAtDestination.getPieceAlliance();
                        if (this.pieceAlliance != pieceAtDestinationAllegiance) {
                            legalMoves.add(new Move.AttackMove(board, this, x, y,
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
        return BoardUtils.FIRST_COLUMN[x][0] && ((step == new int[]{-2, -1})
                || (step == new int[]{2, -1}) || (step == new int[]{1, -2})
                || (step == new int[]{-1, -2}) || (step == new int[]{-1, -1})
                || (step == new int[]{0, -1}) || (step == new int[]{1, -1}));
    }

    private boolean isSecondColumnExclusion(final int[] step,
                                                   final int x) {
        return BoardUtils.SECOND_COLUMN[x][0] && ((step == new int[]{1, -2})
                || (step == new int[]{-1, -2}));
    }

    private boolean isNineColumnExclusion(final int[] step,
                                                 final int x) {
        return BoardUtils.NINE_COLUMN[x][0] && ((step == new int[]{-1, 2})
                || (step == new int[]{1, 2}));
    }

    private boolean isTenColumnExclusion(final int[] step,
                                                final int x) {
        return BoardUtils.FIRST_COLUMN[x][0] && ((step == new int[]{-2, 1})
                || (step == new int[]{-1, 2}) || (step == new int[]{1, 2})
                || (step == new int[]{2, 1})) || (step == new int[]{-1, 1})
                || (step == new int[]{0, 1}) || (step == new int[]{1, 1});
    }
}
