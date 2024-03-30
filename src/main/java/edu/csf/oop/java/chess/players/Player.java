package edu.csf.oop.java.chess.players;

import edu.csf.oop.java.chess.Alliance;
import edu.csf.oop.java.chess.board.Board;
import edu.csf.oop.java.chess.board.Tile;
import edu.csf.oop.java.chess.pieces.*;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import org.codehaus.jackson.annotate.JsonSubTypes;
import org.codehaus.jackson.annotate.JsonTypeInfo;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.PROPERTY,
        property = "type")
@JsonSubTypes({
        @JsonSubTypes.Type(value = WhitePlayer.class, name = "whitePlayer"),
        @JsonSubTypes.Type(value = BlackPlayer.class, name = "blackPlayer"),
})
public abstract class Player {
    private Board board;
    private List<Piece> pieces = new ArrayList<>();
    private Alliance alliance;
    private King king;
    private Integer[] shahTile;

    public Player(Board board, Alliance alliance) {
        this.board = board;
        this.alliance = alliance;
        fillListPieces();
    }

    public Player() {
    }

    public void removePiece(final Piece removePiece) {
        if(removePiece != null){
            pieces.removeIf(removePiece::equals);
            if (removePiece.getClass() == King.class)
                king = null;
        }
    }

    public void fillListPieces() {
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                Piece newPiece = board.getTiles(i, j).getPiece();
                if (newPiece != null && newPiece.getPieceAlliance().equals(alliance)) {
                    pieces.add(newPiece);
                    if (newPiece.getClass() == King.class)
                        king = (King) newPiece;
                }
            }
        }
    }

    public void shahKing(Player attackPlayer) {
        for (Piece attackPiece: attackPlayer.getPieces()){
            if (attackPiece.getCanAttackThis() != null) {
                for (Integer[] tile : attackPiece.getCanAttackThis()) {
                    if (board.getTiles(tile[0], tile[1]).getPiece() != null && board.getTiles(tile[0], tile[1]).getPiece().equals(king)){
                        if (king.isShah())
                            stopGame();
                        king.setShah(true);
                        window("Шах");
                        shahTile = new Integer[]{king.getX(), king.getY()};
                        break;
                    }
                }
            }
        }
    }

    public void checkmate() {
        if (king == null)
            stopGame();

        if(shahTile != null && !(shahTile[0] == king.getX() && shahTile[1] == king.getY())){
            king.setShah(false);
            System.out.println("НЕ шах");
        }
        if (king.isShah() && king.getCanGoFromThis().size() == 0 && king.getCanAttackThis().size() == 0)
                stopGame();
    }

    public void stopGame() {
        window("Конец");
        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (Exception e){
            e.printStackTrace();
        }
        System.exit(0);
    }

    public void window (String message) {
        Stage stage = new Stage();
        Group group = new Group();
        Scene scene = new Scene(group, 250, 100);

        Text text1 = new Text(message);
        text1.setFont(new Font(18));
        text1.setLayoutX(20);
        text1.setLayoutY(55);
        group.getChildren().add(text1);

        stage.setScene(scene);
        stage.show();
    }

//    @Override
//    public String toString(){
//        StringBuilder str = new StringBuilder(alliance.toStr() + " : ");
//        for (Piece piece: pieces){
//            str.append(piece.getName()).append(", ");
//        }
//        return str.toString();
//    }

    public Board getBoard() {
        return board;
    }

    public void setBoard(Board board) {
        this.board = board;
    }

    public List<Piece> getPieces() {
        return pieces;
    }

    public void setPieces(List<Piece> pieces) {
        this.pieces = pieces;
    }

    public Alliance getAlliance() {
        return alliance;
    }

    public void setAlliance(Alliance alliance) {
        this.alliance = alliance;
    }

    public King getKing() {
        return king;
    }

    public void setKing(King king) {
        this.king = king;
    }

    public Integer[] getShahTile() {
        return shahTile;
    }

    public void setShahTile(Integer[] shahTile) {
        this.shahTile = shahTile;
    }

}
