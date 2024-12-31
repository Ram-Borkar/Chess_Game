package code.gui;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class BoardGUI {
    private static final int TILE_SIZE = 80;
    private static final int ROWS = 8;
    private static final int COLS = 8;
    private GridPane grid;

    public BoardGUI() {
        grid = new GridPane();
        initializeBoard();
        initializePieces();
    }

    private void initializeBoard() {
        for (int row = 0; row < ROWS; row++) {
            for (int col = 0; col < COLS; col++) {
                Rectangle tile = new Rectangle(TILE_SIZE, TILE_SIZE);
                tile.setFill((row + col) % 2 == 0 ? Color.BEIGE : Color.BROWN);
                grid.add(tile, col, row);
            }
        }
    }

    private void initializePieces() {
        // Place black pieces
        placePiece("Chess_rdt60.png", 0, 0);
        placePiece("Chess_ndt60.png", 1, 0);
        placePiece("Chess_bdt60.png", 2, 0);
        placePiece("Chess_qdt60.png", 3, 0);
        placePiece("Chess_kdt60.png", 4, 0);
        placePiece("Chess_bdt60.png", 5, 0);
        placePiece("Chess_ndt60.png", 6, 0);
        placePiece("Chess_rdt60.png", 7, 0);
        for (int col = 0; col < COLS; col++) {
            placePiece("Chess_pdt60.png", col, 1);
        }

        // Place white pieces
        placePiece("Chess_rlt60.png", 0, 7);
        placePiece("Chess_nlt60.png", 1, 7);
        placePiece("Chess_blt60.png", 2, 7);
        placePiece("Chess_qlt60.png", 3, 7);
        placePiece("Chess_klt60.png", 4, 7);
        placePiece("Chess_blt60.png", 5, 7);
        placePiece("Chess_nlt60.png", 6, 7);
        placePiece("Chess_rlt60.png", 7, 7);
        for (int col = 0; col < COLS; col++) {
            placePiece("Chess_plt60.png", col, 6);
        }
    }

    private void placePiece(String imageName, int col, int row) {
        Image pieceImage = new Image(getClass().getResourceAsStream("/" + imageName));
        ImageView pieceView = new ImageView(pieceImage);
        pieceView.setFitWidth(TILE_SIZE);
        pieceView.setFitHeight(TILE_SIZE);

        // Enable dragging
        pieceView.setOnMousePressed(event -> {
            pieceView.toFront();
        });

        pieceView.setOnMousePressed(event -> {
            pieceView.toFront();
            pieceView.setUserData(new double[]{event.getSceneX(), event.getSceneY()});
        });

        pieceView.setOnMouseDragged(event -> {
            double[] startPos = (double[]) pieceView.getUserData();
            double offsetX = event.getSceneX() - startPos[0];
            double offsetY = event.getSceneY() - startPos[1];

            pieceView.setTranslateX(offsetX);
            pieceView.setTranslateY(offsetY);
        });

        pieceView.setOnMouseReleased(event -> {
            int newCol = (int) ((event.getSceneX() - grid.getLayoutX()) / TILE_SIZE);
            int newRow = (int) ((event.getSceneY() - grid.getLayoutY()) / TILE_SIZE);

            if (newCol >= 0 && newCol < COLS && newRow >= 0 && newRow < ROWS) {
                GridPane.setColumnIndex(pieceView, newCol);
                GridPane.setRowIndex(pieceView, newRow);
            }

            pieceView.setTranslateX(0);
            pieceView.setTranslateY(0);
        });
        grid.add(pieceView, col, row);
    }

    public GridPane getGrid() {
        return grid;
    }
}
