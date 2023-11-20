module oh.devyn.hangman {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires javafx.graphics;

    opens oh.devyn.hangman to javafx.fxml;
    exports oh.devyn.hangman;
}