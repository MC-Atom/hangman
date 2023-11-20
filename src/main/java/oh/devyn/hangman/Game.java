package oh.devyn.hangman;

import javafx.geometry.HPos;
import javafx.geometry.VPos;
import javafx.scene.Scene;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.stage.Stage;

/* the scene where the actual playing of the game happens */
public class Game {
    public Game(Stage stage){
        GridPane grid = new GridPane();
        // Adds columns to the gridpane
        grid.getColumnConstraints().add(new ColumnConstraints(200));
        grid.getColumnConstraints().add(new ColumnConstraints(300));
        grid.getColumnConstraints().add(new ColumnConstraints(300));
        grid.getColumnConstraints().add(new ColumnConstraints(200));

        // Adds rows to the gridpane
        grid.getRowConstraints().add(new RowConstraints(600));
        grid.getRowConstraints().add(new RowConstraints(200));
        grid.getRowConstraints().add(new RowConstraints(100));

//        grid.setGridLinesVisible(true);

        Scene game = new Scene(grid);
        stage.setTitle("Hangman");
        stage.setScene(game);

        Hangman hangman = new Hangman();
        Keyboard keyboard = new Keyboard(true);
        keyboard.addKeyPressedListener(new KeyPressedListener() {
            public void keyWasPressed(KeyPressedEvent evt) {
                char guessedCharacter = ((Keyboard) evt.getSource()).getLastGuessedLetter();
                hangman.guessLetter(guessedCharacter);
            }
        });


        grid.add(hangman,0,0);
        grid.add(keyboard,1,1);

        GridPane.setHalignment(hangman, HPos.LEFT);
        GridPane.setValignment(hangman, VPos.TOP);
    }
}
