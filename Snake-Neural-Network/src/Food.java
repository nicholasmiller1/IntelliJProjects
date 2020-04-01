import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Food {

    private static int score = 0;
    private int coordinateX;
    private int coordinateY;
    private Canvas canvas;

    public Food() {
        coordinateX = (int)(Math.random() * 50);
        coordinateY = (int)(Math.random() * 29);
        canvas = new Canvas(30, 30);
        GraphicsContext gc = canvas.getGraphicsContext2D();
        gc.setFill(Color.GREENYELLOW);
        gc.fillRect(0, 0, 30, 30);
    }

    public Food(int x, int y) {
        coordinateX = x;
        coordinateY = y;
        canvas = new Canvas(30, 30);
        GraphicsContext gc = canvas.getGraphicsContext2D();
        gc.setFill(Color.GREENYELLOW);
        gc.fillRect(0, 0, 30, 30);
    }

    public static int getScore() {
        return score;
    }

    public static void setScore(int score) {
        Food.score = score;
    }

    public int getCoordinateX() {
        return coordinateX;
    }

    public int getCoordinateY() {
        return coordinateY;
    }

    public Canvas getCanvas() {
        return canvas;
    }

    public void display() {
        Main.addGridConstraints(canvas, coordinateX, coordinateY);
    }

    public static void incrementScore() {
        score++;
    }

    public boolean isEaten(Snake snake) {
        String coordinate = "(" + coordinateX + ", " + coordinateY + ")";
        return snake.getAllCoordinates().contains(coordinate);
    }
}
