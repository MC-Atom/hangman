package oh.devyn.hangman;

import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.VPos;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;

import java.util.ArrayList;

import static oh.devyn.hangman.ResourceManager.*;

public class Hangman extends GridPane {

    private static final int hangsToLoose = 7; // the amount of wrong guesses before you lose
    private String word;
    private int amountHanged = 0;
    private GridPane displayLetters;
    private Canvas gallows;
    private ArrayList<Canvas> letterSpaces = new ArrayList<>();
    private GridPane guessedLetters;

    public Hangman() {
        this(getRandWord());
    }

    public Hangman(String word){
        super();
        gallows = new Canvas(gallowsImage0.getWidth(),gallowsImage0.getHeight());
        this.word = word;
        System.out.println(word);

        getRowConstraints().add(new RowConstraints(gallowsImage0.getHeight()-150));
        getRowConstraints().add(new RowConstraints(70));
        getColumnConstraints().add(new ColumnConstraints(gallowsImage0.getWidth()));
//        setGridLinesVisible(true);

        add(gallows,0,0);
        GridPane.setHalignment(gallows, HPos.LEFT);
        GridPane.setValignment(gallows, VPos.TOP);

        setHangedStage(0,gallows.getGraphicsContext2D());

        displayLetters = new GridPane();
        displayLetters.getRowConstraints().add(new RowConstraints(50));

        for (int i = 0; i < word.length(); i++) {
            displayLetters.getColumnConstraints().add(new ColumnConstraints(50));

            Canvas letterSpace = new Canvas(50, 50);
            if (word.charAt(i) != ' ') {
                letterSpace.getGraphicsContext2D().fillRect(5, 42, 40, 45);
                displayLetters.add(letterSpace,i,0);
            }
            letterSpaces.add(letterSpace);

        }

        add(displayLetters,0,1);
        GridPane.setHalignment(displayLetters, HPos.CENTER);
        GridPane.setValignment(displayLetters, VPos.CENTER);
        displayLetters.setPadding(new Insets(0, 0, 0, 100));
//        displayLetters.setGridLinesVisible(true);

        guessedLetters = new GridPane();
        int rows = 5;
        int columns = 5;
        for (int i = 0; i < columns; i++) {
            guessedLetters.getColumnConstraints().add(new ColumnConstraints(50));

        }
        for (int i = 0; i < rows; i++) {
            guessedLetters.getRowConstraints().add(new RowConstraints(60));

        }

        Canvas boxOutline = new Canvas(columns*50,rows*60);
        boxOutline.getGraphicsContext2D().fillRect(0,0,columns*50,rows*60);
        int rectWidth = 6;
        boxOutline.getGraphicsContext2D().clearRect(rectWidth,rectWidth,columns*50 - 2*rectWidth,rows*60 - 2*rectWidth);
        guessedLetters.add(boxOutline,0,0);
        GridPane.setHalignment(boxOutline,HPos.LEFT);
        GridPane.setValignment(boxOutline,VPos.TOP);

        add(guessedLetters,1,0);
        GridPane.setHalignment(guessedLetters,HPos.LEFT);
        GridPane.setValignment(guessedLetters,VPos.TOP);
        guessedLetters.setPadding(new Insets(80, 0, 0, 50));

//        guessedLetters.setGridLinesVisible(true);
    }

    public void setHangedStage(int stage, GraphicsContext graphicsContext){
        if (stage >= hangStages.size()) {
            stage = hangStages.size() - 1;
        } else if (stage < 0) {
            stage = 0;
        }

        Image gallowsImage = hangStages.get(stage);
        graphicsContext.clearRect(0,0,gallowsImage.getWidth(),gallowsImage.getHeight());
        graphicsContext.drawImage(gallowsImage, 0, -100);
    }

    public boolean guessLetter(char letter){
        int lettersInWord = 0;

        for (int i = 0; i < word.length(); i++) { // loops through every character in the word
            char character = word.charAt(i);
            if (Character.toUpperCase(character) == Character.toUpperCase(letter)) { // checks if the character in the word is the same as the letter guessed
                lettersInWord++;
                showLetter(i);
            }
        }
        System.out.println(word);

        if (lettersInWord == 0) { // if there are none of the guessed letter in the word
            addWrongLetter(letter);
            incrementHanged();
            return false;
        } else {
            return true;
        }
    }

    private void addWrongLetter(char letter){
        Canvas wrongLetter = new Canvas(55,60);
        wrongLetter.getGraphicsContext2D().setFont(myFont);
        wrongLetter.getGraphicsContext2D().fillText(String.valueOf(letter),10,45);

        guessedLetters.add(wrongLetter,amountHanged%5,amountHanged/5);
        GridPane.setHalignment(wrongLetter,HPos.LEFT);
        GridPane.setValignment(wrongLetter,VPos.TOP);
    }

    private void incrementHanged() {
        amountHanged++;
        setHangedStage(amountHanged,gallows.getGraphicsContext2D());
        if (amountHanged >= hangsToLoose){
            endGame(0);
        }
    }

    public void quitGame(){
        endGame(2);
    }

    public void endGame(int didWin){ // did win is if they lost (0), won (1), or quit (2)
    }

    private void showLetter(int index){
        GraphicsContext graphicsContext = letterSpaces.get(index).getGraphicsContext2D();
        graphicsContext.setFont(myFont);
        graphicsContext.fillText(String.valueOf(word.charAt(index)),10,35);
    }

    private static String getRandWord(){
        int randIndex = (int) (Math.random() * randWords.size());
        return randWords.get(randIndex);
    }
}
