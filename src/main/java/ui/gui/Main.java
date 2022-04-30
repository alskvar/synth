package ui.gui;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Main extends Application{

    public static void debugBorder(Region region){
        region.setBorder(new Border(new BorderStroke(Color.GREY,
            BorderStrokeStyle.DASHED, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
    }

    public static void main(String[] args){
        launch(args);
    }

    @Override
    public void start(Stage stage) {
        stage.setTitle("Synth");
        GridPane root = new GridPane();
        ColumnConstraints col1 = new ColumnConstraints();
        col1.setPercentWidth(30);
        ColumnConstraints col2 = new ColumnConstraints();
        col2.setPercentWidth(70);
        root.getColumnConstraints().addAll(col1, col2);
        RowConstraints row1 = new RowConstraints();
        row1.setPercentHeight(70);
        RowConstraints row2 = new RowConstraints();
        row2.setPercentHeight(30);
        root.getRowConstraints().addAll(row1, row2);
        VBox box1 = new VBox(new Text("box 1"));
        VBox box2 = new VBox(new Text("box 2"));
        VBox box3 = new VBox(new Text("box 3"));
        debugBorder(box1);
        debugBorder(box2);
        debugBorder(box3);
        root.add(box1, 0, 0);
        root.add(box2, 1, 0);
        root.add(box3, 0, 1, 2, 1);
        stage.setScene(new Scene(root, 960, 720));
        stage.show();
    }
}
