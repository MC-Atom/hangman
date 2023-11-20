package oh.devyn.hangman;

import javafx.scene.image.Image;
import javafx.scene.text.Font;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;

public class ResourceManager {
    public static ArrayList<String> randWords = new ArrayList<>(Arrays.asList(
            "jazz","computer","Aidan","chair","hat","car","Elmo","box","Minecraft","random",
            "Cassie","monitor","jacket","mask","coat","egg","potato","Undertale","Rick Astley","jeans",
            "java","class"
    ));
    public static Image gallowsImage0,gallowsImage1,gallowsImage2,gallowsImage3,gallowsImage4,gallowsImage5,gallowsImage6,gallowsImage7,gallowsImage8,gallowsImage9;
    public static ArrayList<Image> hangStages;
    public static Font myFont;

    static {
        try {
            gallowsImage0 = new Image(new FileInputStream("src/main/resources/oh/devyn/hangman/gallows0.png"));
            gallowsImage1 = new Image(new FileInputStream("src/main/resources/oh/devyn/hangman/gallows1.png"));
            gallowsImage2 = new Image(new FileInputStream("src/main/resources/oh/devyn/hangman/gallows2.png"));
            gallowsImage3 = new Image(new FileInputStream("src/main/resources/oh/devyn/hangman/gallows3.png"));
            gallowsImage4 = new Image(new FileInputStream("src/main/resources/oh/devyn/hangman/gallows4.png"));
            gallowsImage5 = new Image(new FileInputStream("src/main/resources/oh/devyn/hangman/gallows5.png"));
            gallowsImage6 = new Image(new FileInputStream("src/main/resources/oh/devyn/hangman/gallows6.png"));
            gallowsImage7 = new Image(new FileInputStream("src/main/resources/oh/devyn/hangman/gallows7.png"));
            gallowsImage8 = new Image(new FileInputStream("src/main/resources/oh/devyn/hangman/gallows8.png"));
            gallowsImage9 = new Image(new FileInputStream("src/main/resources/oh/devyn/hangman/gallows9.png"));
            hangStages = new ArrayList<>(Arrays.asList(gallowsImage0,gallowsImage1,gallowsImage2,gallowsImage3,gallowsImage4,gallowsImage5,gallowsImage6,gallowsImage7,gallowsImage8,gallowsImage9));
            myFont = new Font("Comic Sans MS", 40);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
