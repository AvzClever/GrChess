package edu.csf.oop.java.chess.board;

import edu.csf.oop.java.chess.Alliance;
import edu.csf.oop.java.chess.pieces.*;

public class Board {

    private int BOARD_LENGTH = 10;
    private Tile[][] tiles = new Tile[BOARD_LENGTH][BOARD_LENGTH];

    public Tile getTiles(int i, int j){ return tiles[i][j];}

    public Board(Board board){
        this.tiles = board.getTiles();
    }

    public Board(){
        for (int i = 0; i < BOARD_LENGTH; i++) {
            for (int j = 0; j < BOARD_LENGTH; j++) {
                tiles[i][j] = new Tile(i, j);
            }
        }
        tiles[0][0].setPiece(new Rook(Alliance.BLACK, 0, 0));
        tiles[0][1].setPiece(new Knight(Alliance.BLACK, 0, 1));
        tiles[0][2].setPiece(new Bishop(Alliance.BLACK, 0, 2));
        tiles[0][3].setPiece(new Queen(Alliance.BLACK, 0, 3));
        tiles[0][4].setPiece(new King(Alliance.BLACK, 0, 4));
        tiles[0][5].setPiece(new Giraffe(Alliance.BLACK, 0, 5));
        tiles[0][6].setPiece(new Vizier(Alliance.BLACK, 0, 6));
        tiles[0][7].setPiece(new Bishop(Alliance.BLACK, 0, 7));
        tiles[0][8].setPiece(new Knight(Alliance.BLACK, 0, 8));
        tiles[0][9].setPiece(new Rook(Alliance.BLACK, 0, 9));

        for (int i = 0; i < 4; i++) {
            tiles[1][i].setPiece(new Pawn(Alliance.BLACK, 1, i));
            tiles[1][i + 6].setPiece(new Pawn(Alliance.BLACK, 1, i + 6));
        }
        tiles[1][4].setPiece(new WM(Alliance.BLACK, 1, 4));
        tiles[1][5].setPiece(new WM(Alliance.BLACK, 1, 5));
        tiles[2][4].setPiece(new Pawn(Alliance.BLACK, 2, 4));
        tiles[2][5].setPiece(new Pawn(Alliance.BLACK, 2, 5));
        ////////////////
        tiles[9][9].setPiece(new Rook(Alliance.WHITE, 9, 9));
        tiles[9][8].setPiece(new Knight(Alliance.WHITE, 9, 8));
        tiles[9][7].setPiece(new Bishop(Alliance.WHITE, 9, 7));
        tiles[9][6].setPiece(new Queen(Alliance.WHITE, 9, 6));
        tiles[9][5].setPiece(new King(Alliance.WHITE, 9, 5));
        tiles[9][4].setPiece(new Giraffe(Alliance.WHITE, 9, 4));
        tiles[9][3].setPiece(new Vizier(Alliance.WHITE, 9, 3));
        tiles[9][2].setPiece(new Bishop(Alliance.WHITE, 9, 2));
        tiles[9][1].setPiece(new Knight(Alliance.WHITE, 9, 1));
        tiles[9][0].setPiece(new Rook(Alliance.WHITE, 9, 0));

        for (int i = 0; i < 4; i++) {
            tiles[8][i].setPiece(new Pawn(Alliance.WHITE, 8, i));
            tiles[8][i+6].setPiece(new Pawn(Alliance.WHITE, 8, i + 6));
        }
        tiles[8][4].setPiece(new WM(Alliance.WHITE, 8, 4));
        tiles[8][5].setPiece(new WM(Alliance.WHITE, 8, 5));
        tiles[7][4].setPiece(new Pawn(Alliance.WHITE, 7, 4));
        tiles[7][5].setPiece(new Pawn(Alliance.WHITE, 7, 5));
    }

    public void buildGraphConnections(){
        for (int i = 0; i < BOARD_LENGTH; i++) {
            for (int j = 0; j < BOARD_LENGTH; j++) {
                if(tiles[i][j].getPiece() != null) {
                    tiles[i][j].getPiece().setLegalMoves(this);
                }
            }
        }
    }


    public Tile[][] getTiles() {
        return tiles;
    }
    public int getBOARD_LENGTH() {
        return BOARD_LENGTH;
    }

    public void setTiles(Tile[][] tiles) {
        this.tiles = tiles;
    }

    public void setBOARD_LENGTH(int BOARD_LENGTH) {
        this.BOARD_LENGTH = BOARD_LENGTH;
    }

}