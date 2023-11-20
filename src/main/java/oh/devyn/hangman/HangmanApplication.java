package oh.devyn.hangman;

import javafx.application.Application;
import javafx.stage.Stage;

import java.io.IOException;

public class HangmanApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        Menu menu = new Menu(stage);

        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}