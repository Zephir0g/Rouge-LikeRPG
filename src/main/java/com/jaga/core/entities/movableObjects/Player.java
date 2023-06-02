package com.jaga.core.entities.movableObjects;

import com.jaga.config.ConfigCore;
import com.jaga.config.ConfigEntity;
import com.jaga.core.Game;
import com.jaga.core.entities.staticObjects.Wall;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;
import java.util.List;
import java.util.Objects;
import java.util.logging.Level;

public class Player extends MovableEntity implements KeyListener {
    private Image texture;

    private int velocityX = 0;
    private int velocityY = 0;
    private boolean upPressed = false;
    private boolean downPressed = false;
    private boolean leftPressed = false;
    private boolean rightPressed = false;

    public Player(int x, int y, int width, int height) {
        super(x, y, width, height);
        getTexture();
        Game.log.log(Level.INFO, "Player created");
    }

    @Override
    public void move(int dx, int dy) {
        checkCollisionWithWalls(dx, dy);

        x += dx;
        y += dy;

        System.out.println("Player x: " + x + " y: " + y);
    }

    @Override
    public void draw(Graphics g) {
        g.drawImage(texture, x, y, width, height, null);
    }

    public void tick(){
        if (!Game.isPaused()) {
            move(getVelocityX(), getVelocityY());
        }
    }


    private void getTexture() {
        try {
            texture = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream(ConfigEntity.defaultPlayerTexture)));
        } catch (IOException e) {
            Game.log.log(Level.WARNING, "Player texture not found");
        }
    }

    public boolean checkCollisionWithWalls(int dx, int dy) {
        List<Wall> walls = ConfigCore.walls;
        // Create a rectangle representing the player's position after applying the specified changes.
        Rectangle playerRect = new Rectangle(x + dx, y + dy, width, height);

        for (Wall wall : walls) {
            // Create a rectangle representing the current wall.
            Rectangle wallRect = new Rectangle(wall.getX(), wall.getY(), wall.getWidth(), wall.getHeight());

            // Check if the player's rectangle intersects with the wall's rectangle.
            if (playerRect.intersects(wallRect)) {
                return true;
            }
        }

        return false;
    }


    @Override
    public void keyPressed(KeyEvent e) {
        //TODO Make a normal movement
        int keyCode = e.getKeyCode();

        if (keyCode == KeyEvent.VK_W) {
            upPressed = true;
            setVelocityY(-5);
        } else if (keyCode == KeyEvent.VK_S) {
            downPressed = true;
            setVelocityY(5);
        } else if (keyCode == KeyEvent.VK_A) {
            leftPressed = true;
            setVelocityX(-5);
        } else if (keyCode == KeyEvent.VK_D) {
            rightPressed = true;
            setVelocityX(5);
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int keyCode = e.getKeyCode();

        if (keyCode == KeyEvent.VK_W) {
            upPressed = true;
            setVelocityY(0);
        } else if (keyCode == KeyEvent.VK_S) {
            downPressed = true;
            setVelocityY(0);
        } else if (keyCode == KeyEvent.VK_A) {
            leftPressed = true;
            setVelocityX(0);
        } else if (keyCode == KeyEvent.VK_D) {
            rightPressed = true;
            setVelocityX(0);
        }

    }

    @Override
    public void keyTyped(KeyEvent e) {
        // Не используется, оставляем пустым
    }

    private void updateMovement() {
        int dx = 0;
        int dy = 0;

        if (upPressed) {
            dy -= 10;
        }
        if (downPressed) {
            dy += 10;
        }
        if (leftPressed) {
            dx -= 10;
        }
        if (rightPressed) {
            dx += 10;
        }

        // Проверяем, нажаты ли две клавиши одновременно для диагонального движения
        if ((upPressed || downPressed) && (leftPressed || rightPressed)) {
            dx = (int) (dx * 0.7);
            dy = (int) (dy * 0.7);
        }

        if (checkCollisionWithWalls(dx, dy)) {
            dx = 0;
            dy = 0;
        }

    }

    public int getVelocityX() {
        return velocityX;
    }

    public void setVelocityX(int velocityX) {
        this.velocityX = velocityX;
    }

    public int getVelocityY() {
        return velocityY;
    }

    public void setVelocityY(int velocityY) {
        this.velocityY = velocityY;
    }
}
