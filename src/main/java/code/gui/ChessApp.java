package code.gui;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import code.gui.BoardGUI;

public class ChessApp extends Application {
    @Override
    public void start(Stage primaryStage) {
        BoardGUI boardGUI = new BoardGUI();
        StackPane root = new StackPane(boardGUI.getGrid());
        Scene scene = new Scene(root, 640, 640);

        primaryStage.setTitle("Chess Game");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
