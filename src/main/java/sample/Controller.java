package sample;

import edu.csf.oop.java.chess.Alliance;
import edu.csf.oop.java.chess.Game;
import edu.csf.oop.java.chess.board.Tile;
import edu.csf.oop.java.chess.pieces.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Controller implements Initializable {

    @FXML
    GridPane gridPane;
    @FXML
    GridPane gridPaneDemo;
    @FXML
    AnchorPane anchorPane;
    @FXML
    Button saveButton;

    private Button[][] buttons;
    public static Game game = new Game();
    private boolean fl = false;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        buttons = new Button[10][10];
    }

    @FXML
    public void newGame(String path) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(Controller.class.getResource(path));
            Scene scene = new Scene(fxmlLoader.load(), 600, 600);
            Stage stage = new Stage();
            stage.setTitle("Games");
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            Logger logger = Logger.getLogger(Controller.class.getName());
            logger.log(Level.SEVERE, "Failed to create new Window.", e);
        }
    }

    @FXML
    public void handleStartBtnClick() {

        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                buttons[i][j] = but(i, j);

                String beigeStyle = "-fx-background-color: #A5A2A3;",
                        brownStyle = "-fx-background-color: #645D5B;";

                if (i % 2 == 0) {
                    if (j % 2 == 0) {
                        buttons[i][j].setStyle(beigeStyle);
                    } else buttons[i][j].setStyle(brownStyle);
                } else {
                    if (j % 2 != 0) {
                        buttons[i][j].setStyle(beigeStyle);
                    } else {
                        buttons[i][j].setStyle(brownStyle);
                    }
                }
                buttons[i][j].setPrefSize(60.0, 60.0);
                gridPane.add(buttons[i][j], j, i);
            }
        }
        game.getBoard().buildGraphConnections();
    }

    @FXML
    public void handleDemoBtnClick() {
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                buttons[i][j] = new Button(name2(game.getBoard().getTiles(i, j).getPiece()));

                String beigeStyle = "-fx-background-color: #A5A2A3;",
                        brownStyle = "-fx-background-color: #645D5B;";

                if (i % 2 == 0) {
                    if (j % 2 == 0) {
                        buttons[i][j].setStyle(beigeStyle);
                    } else buttons[i][j].setStyle(brownStyle);
                } else {
                    if (j % 2 != 0) {
                        buttons[i][j].setStyle(beigeStyle);
                    } else {
                        buttons[i][j].setStyle(brownStyle);
                    }
                }
                buttons[i][j].setPrefSize(60.0, 60.0);
                gridPaneDemo.add(buttons[i][j], j, i);
            }
        }
        game.getBoard().buildGraphConnections();
        fl = true;
    }

    private void piecesNames() {
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                buttons[i][j].setText(name2(game.getBoard().getTiles(i, j).getPiece()));
            }
        }
    }

    private static String name2(Piece piece) {
        String res = "";
        if(piece == null) return res;
        if(piece.getPieceAlliance() == Alliance.WHITE) {
            res += "white\n   ";
        }else{
            res += "black\n   ";
        }
        if(piece instanceof Pawn) return res + "pawn";
        if(piece instanceof Knight) return res + "knight";
        if(piece instanceof King) return res + "king";
        if(piece instanceof Queen) return res + "queen";
        if(piece instanceof Bishop) return res + "bishop";
        if(piece instanceof Giraffe) return res + "giraffe";
        if(piece instanceof Rook) return res + "rook";
        if(piece instanceof Vizier) return res + "vizier";
        if(piece instanceof WM) return res + "wm";
        else return " ";
    }

    private Button but(int i, int j) {
        Button b = new Button(name2(game.getBoard().getTiles(i, j).getPiece()));
        b.setOnMouseReleased(event ->  {
            game.MouseClick(game.getBoard().getTiles(i, j));
            piecesNames();
        });
        return b;
    }

    private Tile tileGenerator1() {
        Tile tile = game.getBoard().getTiles((int) (Math.random() * 10), (int) (Math.random() * 10));
        while (tile.getPiece() == null){
            tile = game.getBoard().getTiles((int) (Math.random() * 10), (int) (Math.random() * 10));
        }
        return tile;
    }

    private Tile tileGenerator2(Tile tile) {
        List<Integer[]> list = tile.getPiece().getCanGoFromThis();
        list.addAll(tile.getPiece().getCanAttackThis());
        int n = (int) (Math.random() * list.size());
        System.out.println(list.size());
        System.out.println(n);
        //        while (tile.getPiece() != null){
//            tile = game.getBoard().getTiles((int) (Math.random() * 10), (int) (Math.random() * 10));
//        }
        if (list.size() != 0)
            return game.getBoard().getTiles(list.get(n)[0], list.get(n)[1]);
        return null;
    }
    @FXML
    private void demonstration() {
//        for (int i = 0; i < 10; i++) {
            Tile tile = tileGenerator1();
            game.MouseClick(tile);
            game.MouseClick(tileGenerator2(tile));
//            try {
//                TimeUnit.SECONDS.sleep(1);
//            } catch (Exception e){
//                e.printStackTrace();
//            }
            piecesNames();
//        }
    }

    public void startGame() {
        newGame("/d.fxml");
    }

    @FXML
    public void continueLastGame() throws IOException {
        Cell cell = new Serialization().deserialization();
        game = (cell.getGame());
//        game.getBoard().buildGraphConnections();
        newGame("/d.fxml");
    }

    @FXML
    public void watchPlayback() {
        newGame("/demo.fxml");
//        handleDemoBtnClick();
//        if (fl){
//            System.out.println("Начинаю демонстрацию");
//            demonstration();
//        }
//        System.out.println("Начинаю демонстрацию");
//        demonstration();
    }

    @FXML
    public void save() {
        Serialization.serialization(game);
        Stage stage = (Stage) saveButton.getScene().getWindow();
        stage.close();
    }

    @FXML
    public void quitTheGame() {
        Stage stage = (Stage) saveButton.getScene().getWindow();
        stage.close();
    }

    public void demonstration1(ActionEvent actionEvent) {
        for (int i = 0; i < 10; i++) {
            demonstration();
        }
    }
}