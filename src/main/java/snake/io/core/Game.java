package snake.io.core;

import snake.io.midia.AudioPlayer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferStrategy;


public class Game extends Canvas implements Runnable, KeyListener {

    public static final int DOT_SIZE = 10;
    private final int gameWidth = 800;
    private final int gameHeight = 600;
    private Snake snake;
    private Coordinate gold;
    private KeyEvent lastKeyEvent;
    private int score = 0;
    private AudioPlayer player = new AudioPlayer();

    public Game() {
        this.setPreferredSize(new Dimension(getGameWidth(), getGameHeight()));
        snake = Snake.fromStartBody();
        gold = Coordinate.fromRandom(getGameWidth(), getGameHeight());
        player.playGameMusic();
        this.addKeyListener(this);
    }

    public static void main(String[] args) {
        Game game = new Game();
        JFrame frame = new JFrame("Snake IO (FernandoBontorin)");
        frame.add(game);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        new Thread(game).start();
    }

    private int getGameHeight() {
        return gameHeight;
    }

    private int getGameWidth() {
        return gameWidth;
    }

    public void tick() {
        collision();
        command();
    }

    private void command() {
        if (lastKeyEvent != null) {
            if (lastKeyEvent.getKeyCode() == KeyEvent.VK_UP) {
                snake.toUp(velocity());
            } else if (lastKeyEvent.getKeyCode() == KeyEvent.VK_DOWN) {
                snake.toDown(velocity());
            } else if (lastKeyEvent.getKeyCode() == KeyEvent.VK_LEFT) {
                snake.toLeft(velocity());
            } else if (lastKeyEvent.getKeyCode() == KeyEvent.VK_RIGHT) {
                snake.toRight(velocity());
            } else if (lastKeyEvent.getKeyCode() == KeyEvent.VK_ESCAPE) {
                System.exit(0);
            }
        }
    }

    private int velocity() {
        return (int) (DOT_SIZE + score * 0.2);
    }

    public void collision() {
        if (snake.head().toRectangle().intersects(gold.toRectangle())) {
            collectGold();
        }
        if (snake.tail().stream().anyMatch(
                c -> snake.head().toRectangle().intersects(c.toRectangle())
        ) || crossBounds(snake.head())) {
            gameOver();
        }
    }

    private boolean crossBounds(Coordinate coordinate) {
        return !(coordinate.getX() >= 0 && coordinate.getX() < getGameWidth() && coordinate.getY() >= 0 && coordinate.getY() < getGameHeight());
    }

    private void gameOver() {
        player.play("sounds/LOSE.WAV");
        score = 0;
        snake = Snake.fromStartBody();
        lastKeyEvent = null;
    }

    private void collectGold() {
        score++;
        if (score % 10 == 0) {
            player.play("sounds/ACHIEVEMENT.WAV");
        }
        player.play("sounds/COIN.WAV");

        gold = Coordinate.fromRandom(getGameWidth(), getGameHeight());
        snake.grow();
    }

    public void render() {
        BufferStrategy bs = this.getBufferStrategy();
        if (bs == null) {
            this.createBufferStrategy(3);
            return;
        }
        Graphics g = bs.getDrawGraphics();
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, getGameWidth(), getGameHeight());
        g.setColor(Color.CYAN);
        g.fillRect(snake.head().getX(), snake.head().getY(), DOT_SIZE, DOT_SIZE);
        for (int i = 0; i < snake.tail().size(); i++) {
            g.setColor(Color.BLUE);
            g.fillRect(snake.tail().get(i).getX(), snake.tail().get(i).getY(), DOT_SIZE, DOT_SIZE);
        }

        g.setColor(Color.ORANGE);
        g.fillRect(gold.getX(), gold.getY(), DOT_SIZE, DOT_SIZE);
        g.setColor(Color.GREEN);
        g.setFont(new Font(Font.DIALOG, Font.BOLD, 16));

        g.drawString("" + score, 16, getGameHeight() - 16);
        g.dispose();
        bs.show();
    }

    @Override
    public void run() {

        //noinspection InfiniteLoopStatement
        while (true) {
            tick();
            render();
            try {
                Thread.sleep(1000 / 30);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        lastKeyEvent = e;

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }


}
