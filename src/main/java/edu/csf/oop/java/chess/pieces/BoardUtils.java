package edu.csf.oop.java.chess.pieces;


public class BoardUtils {

    protected static final boolean[][] FIRST_COLUMN = initColumn(0);
    protected static final boolean[][] SECOND_COLUMN = initColumn(1);
    protected static final boolean[][] NINE_COLUMN = initColumn(8);
    protected static final boolean[][] TEN_COLUMN = initColumn(9);



    public static final int NUM_TILES = 10;
    public BoardUtils() {
        throw  new RuntimeException("Невозможно создать экземпляр!");
    }

    private static boolean[][] initColumn(int j) {
        final boolean[][] column = new boolean[NUM_TILES][NUM_TILES];
        for (int i = 0; i < NUM_TILES; i++) {
            column[i][j] = true;
        }
        return column;
    }


    public static boolean isValidTileCoordinate(int x, int y) {
        return x >= 0 && x < NUM_TILES && y >= 0 && y < NUM_TILES;
    }
}
