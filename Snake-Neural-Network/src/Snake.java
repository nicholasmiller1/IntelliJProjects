import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javafx.scene.*;

public class Snake {

    private SnakeComponent head;
    private List<SnakeComponent> components = new ArrayList<>();

    public Snake(SnakeComponent head) {
        this.head = head;
        components.add(head);
        for (int i = 1; i < 3; i++) {
            if (head.getDirection() == 0) {
                SnakeComponent body = new SnakeComponent(head.getCoordinateX(), head.getCoordinateY()+i, 0);
                components.add(body);
            }
            else if (head.getDirection() == 1) {
                SnakeComponent body = new SnakeComponent(head.getCoordinateX(), head.getCoordinateY()-i, 1);
                components.add(body);
            }
            else if (head.getDirection() == 2) {
                SnakeComponent body = new SnakeComponent(head.getCoordinateX()+i, head.getCoordinateY(), 2);
                components.add(body);
            }
            else if (head.getDirection() == 3) {
                SnakeComponent body = new SnakeComponent(head.getCoordinateX()-i, head.getCoordinateY(), 3);
                components.add(body);
            }
        }
    }

    public Snake(SnakeComponent head, int direction, int length) {
        this.head = head;
        components.add(head);
        head.setDirection(direction);
        for (int i = 1; i < length; i++) {
            if (direction == 0) {
                SnakeComponent body = new SnakeComponent(head.getCoordinateX(), head.getCoordinateY()+i, 0);
                components.add(body);
            }
            else if (direction == 1) {
                SnakeComponent body = new SnakeComponent(head.getCoordinateX(), head.getCoordinateY()-i, 1);
                components.add(body);
            }
            else if (direction == 2) {
                SnakeComponent body = new SnakeComponent(head.getCoordinateX()+i, head.getCoordinateY(), 2);
                components.add(body);
            }
            else if (direction == 3) {
                SnakeComponent body = new SnakeComponent(head.getCoordinateX()-i, head.getCoordinateY(), 3);
                components.add(body);
            }
        }
    }

    public void changeDirection(String newDirection) {
        if (newDirection != null && !newDirection.equals(components.get(0).getStringDirection()) && !newDirection.equals(components.get(0).getOppositeDirection())) {
            if (newDirection.equals("UP")) {
                for (int i = 0; i < components.size(); i++) {
                    SnakeComponent c = components.get(i);
                    c.changeComponentDirection(0, i);
                }
            } else if (newDirection.equals("DOWN")) {
                for (int i = 0; i < components.size(); i++) {
                    SnakeComponent c = components.get(i);
                    c.changeComponentDirection(1, i);
                }
            } else if (newDirection.equals("LEFT")) {
                for (int i = 0; i < components.size(); i++) {
                    SnakeComponent c = components.get(i);
                    c.changeComponentDirection(2, i);
                }
            } else if (newDirection.equals("RIGHT")) {
                for (int i = 0; i < components.size(); i++) {
                    SnakeComponent c = components.get(i);
                    c.changeComponentDirection(3, i);
                }
            }
        }
    }

    public void incrementAllChanges() {
        for (int i = 0; i < components.size(); i++) {
            components.get(i).incrementChanges();
        }
    }

    public List<SnakeComponent> getComponents() {
        return components;
    }
    public void addComponent(SnakeComponent body) {
        components.add(body);
    }

    public List<Node> getAllNodes() {
        List<Node> nodes = new ArrayList<>();
        for (int i = 0; i < components.size(); i++) {
            nodes.add(components.get(i).getCanvas());
        }
        return nodes;
    }

    public List<String> getAllCoordinates() {
        List<String> coordinates = new ArrayList<>();
        for (SnakeComponent s : components) {
            String coordinate = "(" + s.getCoordinateX() + ", " + s.getCoordinateY() + ")";
            coordinates.add(coordinate);
        }
        return coordinates;
    }

    public void displayAll() {
        for (int i = 0; i < components.size(); i++) {
            SnakeComponent s = components.get(i);
            s.display();
        }
    }

    public void move() {
        for (int i = 0; i < components.size(); i++) {
            components.get(i).moveComponent();
        }
    }

    public boolean stopCondition() {
        int predictedX = head.getCoordinateX();
        int predictedY = head.getCoordinateY();
        int currentDirection = head.getDirection();
        if (currentDirection == 0) {
            predictedY -= 1;
        }
        else if (currentDirection == 1) {
            predictedY += 1;
        }
        else if (currentDirection == 2) {
            predictedX -= 1;
        }
        else if (currentDirection == 3) {
            predictedX += 1;
        }
        if ((predictedX == -1 && currentDirection == 2)
                || (predictedX == 50 && currentDirection == 3)
                || (predictedY == -1 && currentDirection == 0)
                || (predictedY == 29 && currentDirection == 1)) {
            return true;
        }
        for (int i = 1; i < components.size(); i++) {
            SnakeComponent s = components.get(i);
            if (s.getCoordinateX() == predictedX && s.getCoordinateY() == predictedY) {
                return true;
            }
        }
        return false;
    }

    public Node addComponent() {
        SnakeComponent lastComponent = components.get(components.size() - 1);
        int lastDirection = lastComponent.getDirection();
        SnakeComponent s;
        if (lastDirection == 0) {
            s = new SnakeComponent(lastComponent.getCoordinateX(), lastComponent.getCoordinateY() + 1, lastDirection);
        }
        else if (lastDirection == 1) {
            s = new SnakeComponent(lastComponent.getCoordinateX(), lastComponent.getCoordinateY() - 1, lastDirection);
        }
        else if (lastDirection == 2) {
            s = new SnakeComponent(lastComponent.getCoordinateX() + 1, lastComponent.getCoordinateY(), lastDirection);
        }
        else {
            s = new SnakeComponent(lastComponent.getCoordinateX() - 1, lastComponent.getCoordinateY(), lastDirection);
        }
        s.setChanges(lastComponent.getChanges().stream().map(e -> e + 1).collect(Collectors.toList()));
        components.add(s);
        s.display();
        return s.getCanvas();
    }
}
