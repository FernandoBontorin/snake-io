package snake.io.core;

import java.awt.*;
import java.util.Random;

import static snake.io.core.Game.DOT_SIZE;

public class Coordinate {
    private int x, y = 0;

    public Coordinate() {
    }

    public Coordinate(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public static Coordinate fromRandom(int width, int height) {
        return new Coordinate(
                new Random().nextInt(width - DOT_SIZE),
                new Random().nextInt(height - DOT_SIZE)
        );
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public Coordinate clone() {
        return new Coordinate(x, y);
    }

    @Override
    public String toString() {
        return String.format("(%d, %d)", x, y);
    }

    public Rectangle toRectangle() {
        return new Rectangle(x, y, DOT_SIZE, DOT_SIZE);
    }
}
