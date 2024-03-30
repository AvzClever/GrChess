package sample;

import edu.csf.oop.java.chess.Game;
import edu.csf.oop.java.chess.board.Board;
import org.codehaus.jackson.map.ObjectMapper;

import java.io.*;

public class Serialization {
    public static void serialization(final Game game) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            Cell cell =  new Cell();
            cell.setGame(game);
            File file = new File("src\\main\\resources\\save.json");
            try {
                if (!file.exists()) {
                    file.getParentFile().mkdirs();
                    file.createNewFile();
                }
            } catch (IOException e) {
                System.out.println("File creation error!");
            }

            mapper.writeValue(file, cell);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Cell deserialization() throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        File file = new File("src\\main\\resources\\save.json");
        if (!file.exists()) {
            throw new FileNotFoundException("File Not Found");
        } else {
            Cell cell = mapper.readValue(new File("src\\main\\resources\\save.json"), Cell.class);
            return cell;
        }
    }
}
