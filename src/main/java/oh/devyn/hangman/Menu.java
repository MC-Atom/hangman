package oh.devyn.hangman;

import javafx.geometry.HPos;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import static oh.devyn.hangman.ResourceManager.myFont;

public class Menu {

    public Menu(Stage stage){ this(stage,-1); }
    public Menu(Stage stage, int didWin){
        GridPane grid = new GridPane();
        grid.getColumnConstraints().add(new ColumnConstraints(1000));

        grid.getRowConstraints().add(new RowConstraints(300));
        grid.getRowConstraints().add(new RowConstraints(150));
        grid.getRowConstraints().add(new RowConstraints(150));
        grid.getRowConstraints().add(new RowConstraints(300));

        Scene menu = new Scene(grid);
        stage.setTitle("Hangman");
        stage.setScene(menu);

        Canvas start = new Canvas(300,100);
        start.getGraphicsContext2D().setFill(Color.valueOf("aaaaff"));
        start.getGraphicsContext2D().fillRect(0,0,300,100);
        start.getGraphicsContext2D().setFont(myFont);
        start.getGraphicsContext2D().setFill(Color.valueOf("000000"));
        start.getGraphicsContext2D().fillText("START GAME",15,45);

        start.addEventHandler(MouseEvent.MOUSE_CLICKED,mouseEvent -> {
            Game game = new Game(stage);
        });
        start.addEventHandler(MouseEvent.MOUSE_ENTERED,mouseEvent -> {
            start.getGraphicsContext2D().setFill(Color.valueOf("8888ee"));
            start.getGraphicsContext2D().fillRect(0,0,300,100);
            start.getGraphicsContext2D().setFill(Color.valueOf("000000"));
            start.getGraphicsContext2D().fillText("START GAME",10,45);
        });
        start.addEventHandler(MouseEvent.MOUSE_EXITED,mouseEvent -> {
            start.getGraphicsContext2D().setFill(Color.valueOf("aaaaff"));
            start.getGraphicsContext2D().fillRect(0,0,300,100);
            start.getGraphicsContext2D().setFill(Color.valueOf("000000"));
            start.getGraphicsContext2D().fillText("START GAME",15,45);
        });

        grid.add(start,0,1);
        GridPane.setHalignment(start, HPos.CENTER);
    }
}
