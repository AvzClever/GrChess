package edu.csf.oop.java.chess;

import edu.csf.oop.java.chess.board.Board;
import edu.csf.oop.java.chess.board.Tile;
import edu.csf.oop.java.chess.players.BlackPlayer;
import edu.csf.oop.java.chess.players.WhitePlayer;

//import java.util.logging.;
;
//import java.util.logging.

public class Game {
    private Board board;
    private Alliance alliance;
    private Tile tile = null;
    private WhitePlayer whitePlayer;
    private BlackPlayer blackPlayer;

    public Game() {
        board  = new Board();
        alliance = Alliance.WHITE;
        whitePlayer = new WhitePlayer(board);
        blackPlayer = new BlackPlayer(board);
    }
//    private final Logger logger = LoggerFactory.getLogger(Game.class);


    public Board getBoard() {
        return board;
    }

    public void MouseClick(Tile tile){
        if (tile == null)
            return;
        if(this.tile == null) {
            if(tile.getPiece() != null){
                if (tile.getPiece().getPieceAlliance() == alliance) {
                    this.tile = tile;
                }
            }
        }
        else{
            if(tile.getPiece() != null){
                if (tile.getPiece().getPieceAlliance() == alliance) {
                    this.tile = tile;
                }
            }
            boolean flag = false;
            for (Integer[] goTile: this.tile.getPiece().getCanGoFromThis()) {
                System.out.print("tile " + tile.getX());
                System.out.print(" " + tile.getY() + " == ");
                System.out.print("goTile " + goTile[0]);
                System.out.println(" " + goTile[1] + " ?");
                System.out.println("_______________________");
                if(goTile[0] == tile.getX() && goTile[1] == tile.getY()){
//                    logger.info(String.format("Походили %s, перемещение фигуры %s : (%d, %d) ---> (%d, %d)",
//                            alliance.toStr(), this.tile.getPiece().getClass(),
//                            this.tile.getX(), this.tile.getY(), goTile.getX(), goTile.getY()));
                    flag = true;
                    break;
                }
            }

            for (Integer[] attackTile: this.tile.getPiece().getCanAttackThis()) {
                if(attackTile[0] == tile.getX() && attackTile[1] == tile.getY()){
//                    logger.info(String.format("Атаковали %s, перемещение фигуры %s : (%d, %d) ---> (%d, %d), убита фигура",
//                            alliance.toStr(), this.tile.getPiece().getClass(),
//                            this.tile.getX(), this.tile.getY(), attackTile.getX(),
//                            attackTile.getY()));
                    if (alliance.isWhite()){
                        blackPlayer.removePiece(tile.getPiece());
                    } else {
                        whitePlayer.removePiece(tile.getPiece());
                    }
                    System.out.println(blackPlayer);
                    System.out.println();
                    System.out.println(whitePlayer);
                    flag = true;
                    break;
                }
            }

            if (flag) {
                Turn(this.tile, tile);

                blackPlayer.shahKing(whitePlayer);
                blackPlayer.checkmate();
                whitePlayer.shahKing(blackPlayer);
                whitePlayer.checkmate();
                this.tile = null;
            }
        }
    }

    private void Turn (Tile from, Tile to){
        to.setPiece(from.getPiece());
        from.setPiece(null);
        board.buildGraphConnections();
        if (alliance.isBlack())
            alliance = Alliance.WHITE;
        else alliance = Alliance.BLACK;
    }

    public void setBoard(Board board) {
        this.board = board;
    }

    public Alliance getAlliance() {
        return alliance;
    }

    public void setAlliance(Alliance alliance) {
        this.alliance = alliance;
    }

    public Tile getTile() {
        return tile;
    }

    public void setTile(Tile tile) {
        this.tile = tile;
    }

    public WhitePlayer getWhitePlayer() {
        return whitePlayer;
    }

    public void setWhitePlayer(WhitePlayer whitePlayer) {
        this.whitePlayer = whitePlayer;
    }

    public BlackPlayer getBlackPlayer() {
        return blackPlayer;
    }

    public void setBlackPlayer(BlackPlayer blackPlayer) {
        this.blackPlayer = blackPlayer;
    }

//    public Logger getLogger() {
//        return logger;
//    }
}
