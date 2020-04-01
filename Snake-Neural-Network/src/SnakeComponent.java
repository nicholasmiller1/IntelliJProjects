import javafx.scene.canvas.*;
import javafx.scene.paint.Color;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class SnakeComponent {

    private List<Integer> changes = new ArrayList<Integer>();
    private int direction;
    private int coordinateX;
    private int coordinateY;
    private Canvas canvas;

    public SnakeComponent() {
        coordinateX = (int) (Math.random() * 39) + 5;
        coordinateY = (int) (Math.random() * 18) + 5;
        this.direction = (int) (Math.random() * 4);
        canvas = new Canvas(30, 30);
        GraphicsContext gc = canvas.getGraphicsContext2D();
        gc.setFill(Color.WHITE);
        gc.fillRect(0, 0, 30, 30);
    }

    public SnakeComponent(int x, int y, int direction) {
        coordinateX = x;
        coordinateY = y;
        this.direction = direction;
        canvas = new Canvas(30, 30);
        GraphicsContext gc = canvas.getGraphicsContext2D();
        gc.setFill(Color.WHITE);
        gc.fillRect(0, 0, 30, 30);
    }

    public Canvas getCanvas() {
        return canvas;
    }

    public int getCoordinateY() {
        return coordinateY;
    }

    public void setCoordinateY(int coordinateY) {
        this.coordinateY = coordinateY;
    }

    public int getCoordinateX() {
        return coordinateX;
    }

    public void setCoordinateX(int coordinateX) {
        this.coordinateX = coordinateX;
    }

    public int getDirection() {
        return direction;
    }

    public List<Integer> getChanges() {
        return changes;
    }

    public void setChanges(List<Integer> changes) {
        this.changes = changes;
    }

    public String getStringDirection() {
        if (direction == 0) {
            return "UP";
        }
        else if (direction == 1) {
            return "DOWN";
        }
        else if (direction == 2) {
            return "LEFT";
        }
        else if (direction == 3) {
            return "RIGHT";
        }
        return null;
    }

    public String getOppositeDirection() {
        if (direction == 0) {
            return "DOWN";
        }
        else if (direction == 1) {
            return "UP";
        }
        else if (direction == 2) {
            return "RIGHT";
        }
        else if (direction == 3) {
            return "LEFT";
        }
        return null;
    }

    public void setDirection(int direction) {
        this.direction = direction;
    }

    public void display() {
        Main.addGridConstraints(canvas, coordinateX, coordinateY);
    }

    public void moveComponent() {
        if (direction == 0 && coordinateY != 0) {
            coordinateY -= 1;
        } else if (direction == 1 && coordinateY != 28) {
            coordinateY += 1;
        } else if (direction == 2 && coordinateX != 0) {
            coordinateX -= 1;
        } else if (direction == 3 && coordinateX != 49) {
            coordinateX += 1;
        }
    }

    public void changeComponentDirection(int direction, int delay) {
        changes.add((direction * 10000) + delay );
    }

    public void incrementChanges() {
        if (!changes.isEmpty()) {
            if (changes.get(0) % 10000 == 0) {
                direction = changes.get(0) / 10000;
                changes.remove(0);
            }
            changes = changes.stream().map(e -> e - 1).collect(Collectors.toList());
        }
    }
}
