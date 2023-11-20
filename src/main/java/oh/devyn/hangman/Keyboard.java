package oh.devyn.hangman;

import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;

import java.util.*;

class KeyPressedEvent extends EventObject {
    public KeyPressedEvent(Keyboard source) {
        super(source);
    }
}
interface KeyPressedListener extends EventListener {
    void keyWasPressed(KeyPressedEvent evt);
}

public class Keyboard extends GridPane {
    private static ArrayList<Character> KEYS = new ArrayList<>(Arrays.asList(
        'Q','W','E','R','T','Y','U','I','O','P','\n',
        'A','S','D','F','G','H','J','K','L','\n',
        'Z','X','C','V','B','N','M'
    ));
    private static HashMap<Character, Node> keyboardKeys = new HashMap<>();
    private List<KeyPressedListener> listeners = new ArrayList<>();
    private final boolean enableGuessing;
    private char lastGuessedLetter = ' ';


    public void addKeyPressedListener(KeyPressedListener listener) {
        listeners.add(listener); // KeyPressedListener.class,
    }
    public void removeMyEventListener(KeyPressedListener listener) {
        listeners.remove(listener);
    }
    void fireMyEvent(KeyPressedEvent evt) {
        for (KeyPressedListener listener : listeners) {
            listener.keyWasPressed(evt);
        }
    }

    public Keyboard() { // makes it not remove keys when you press them by default
        this(false);
    }

    public Keyboard(boolean enableGuessing) {
        super();
        this.enableGuessing = enableGuessing;
        for (int i = 0; i < 10; i++) { // Adds columns to the gridpane
            this.getColumnConstraints().add(new ColumnConstraints(60));
        }
        for (int i = 0; i < 3; i++) { // Adds rows to the gridpane
            this.getRowConstraints().add(new RowConstraints(75));
        }
        //this.setGridLinesVisible(true);

        int row = 0;
        int column = 0;
        for (Character letter : KEYS) { // Adds all the keys to the keyboard
            if (letter == '\n'){
                row++;
                column = 0;
            } else {
                Button key = new Button();
                key.setText(letter.toString());
                key.setMinSize(40,40);
                this.add(key,column,row);
                GridPane.setHalignment(key, HPos.LEFT);
                // if it's on row 3, shift it over by one more to match a standard keyboard
                int leftMargin = row == 2 ? 3 * 15 : row * 15;
                GridPane.setMargin(key, new Insets(0, 0, 0, leftMargin));
                column++;

                keyboardKeys.put(letter,key);
                key.addEventHandler(MouseEvent.MOUSE_CLICKED, mouseEvent -> {
                    if (enableGuessing) {
                        removeKey(letter);
                    }
                    lastGuessedLetter = letter;
                    for (KeyPressedListener listener : listeners) {
                        KeyPressedEvent evt = new KeyPressedEvent(this);
                        listener.keyWasPressed(evt);
                    }
                });
            }
        }
    }

    public char getLastGuessedLetter(){
        return lastGuessedLetter;
    }

    public void removeKey(char key){
        Character keyToRemove = key;
        Node button = keyboardKeys.get(keyToRemove);
        this.getChildren().remove(button);
    }

}
