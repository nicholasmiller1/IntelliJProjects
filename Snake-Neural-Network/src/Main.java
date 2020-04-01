import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class Main extends Application {

    Stage window;
    Scene scene;
    GridPane grid;
    double currentTime = 0;
    String lastInput;
    Food currentFood = null;

    public static void main(String[] args) {
        launch(args);
    }

    public Scene getScene() {
        return scene;
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        window = primaryStage;
        window.setTitle("Snake Game");

        grid = new GridPane();
        grid.setPrefSize(1745, 1010);
        grid.setHgap(5);
        grid.setVgap(5);
        grid.setAlignment(Pos.CENTER);
        final int numCols = 50;
        final int numRows = 29;
        for (int i = 0; i < numCols; i++) {
            ColumnConstraints colConst = new ColumnConstraints();
            colConst.setPercentWidth(100.0 / numCols);
            grid.getColumnConstraints().add(colConst);
        }
        for (int i = 0; i < numRows; i++) {
            RowConstraints rowConst = new RowConstraints();
            rowConst.setPercentHeight(100.0 / numRows);
            grid.getRowConstraints().add(rowConst);
        }

        Snake snake = new Snake(new SnakeComponent());
        snake.displayAll();
        grid.getChildren().addAll(snake.getAllNodes());

        scene = new Scene(grid, 1745, 1010, Color.BLACK);
        scene.setOnKeyPressed(e -> lastInput = e.getCode().toString());
        window.setScene(scene);

        final long startNanoTime = System.nanoTime();

        new AnimationTimer() {
            @Override
            public void handle(long now) {
                double t = ((now - startNanoTime) / 1000000000.0);
                t = Math.floor(t * 10) / 10;

                if (currentTime != t) {
                    snake.changeDirection(lastInput);
                    snake.incrementAllChanges();


                    if (snake.stopCondition()) {
                        this.stop();
                    }

                    snake.move();
                    snake.displayAll();

                    if (currentFood == null) {
                        Food food = new Food();
                        while (food.isEaten(snake)) {
                            food = new Food();
                        }
                        currentFood = food;
                        food.display();
                        grid.getChildren().add(food.getCanvas());
                    }
                    else if (currentFood.isEaten(snake)) {
                        grid.getChildren().remove(currentFood.getCanvas());
                        grid.getChildren().add(snake.addComponent());
                        currentFood = null;
                        Food.incrementScore();
                    }

                    currentTime = t;
                }
            }
        }.start();

        window.show();
    }

    public static void addGridConstraints(Node n, int x, int y) {
        GridPane.setConstraints(n, x, y);
    }
}
