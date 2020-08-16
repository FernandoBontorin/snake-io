package snake.io.core;

import java.util.LinkedList;
import java.util.List;

import static snake.io.core.Game.DOT_SIZE;


public class Snake {
    private List<Coordinate> body;
    private List<Coordinate> lastBody;

    public Snake() {
        body = new LinkedList<>();
        this.lastBody = this.body;
    }

    public Snake(List<Coordinate> body) {
        this.body = body;
        this.lastBody = this.body;
    }

    public static Snake fromStartBody() {
        int size = 5;
        LinkedList<Coordinate> coordinates = new LinkedList<>();
        for (int i = size - 1; i >= 0; i--) {
            coordinates.add(new Coordinate(i * DOT_SIZE, 0));
        }
        return new Snake(coordinates);
    }

    public Coordinate head() {
        return body.get(0);
    }

    public List<Coordinate> tail() {
        List<Coordinate> coordinates = new LinkedList<>();
        for (int i = 1; i < body.size(); i++) {
            coordinates.add(body.get(i));
        }
        return coordinates;
    }

    private void refreshTail() {
        for (int i = body.size() - 1; i > 0; i--) {
            body.set(i, body.get(i - 1).clone());
        }
    }

    public void toUp(int pixels) {
        refreshTail();
        body.get(0).setY(body.get(0).getY() - pixels);
    }

    public void toDown(int pixels) {
        refreshTail();
        body.get(0).setY(body.get(0).getY() + pixels);
    }

    public void toLeft(int pixels) {
        refreshTail();
        body.get(0).setX(body.get(0).getX() - pixels);
    }

    public void toRight(int pixels) {
        refreshTail();
        body.get(0).setX(body.get(0).getX() + pixels);
    }

    public void grow() {
        body.add(lastBody.get(lastBody.size() - 1).clone());
        lastBody = body;
    }
}
