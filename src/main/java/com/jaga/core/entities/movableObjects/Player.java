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
import java.util.Objects;
import java.util.List;
import java.util.logging.Level;

public class Player extends MovableEntity implements KeyListener {
    private Image texture;

    private boolean upPressed = false;
    private boolean downPressed = false;
    private boolean leftPressed = false;
    private boolean rightPressed = false;

    public Player(int x, int y, int width, int height) {
        super(x, y, width, height);
        getTexture();
        Game.log.log(Level.INFO,"Player created");
    }

    @Override
    public void move(int dx, int dy) {
        checkCollisionWithWalls(dx, dy);
            x += dx;
            y += dy;
    }

    @Override
    public void draw(Graphics g) {
        g.drawImage(texture, x, y, width, height, null);
    }

    private void getTexture(){
        try{
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
        } else if (keyCode == KeyEvent.VK_S) {
            downPressed = true;
        } else if (keyCode == KeyEvent.VK_A) {
            leftPressed = true;
        } else if (keyCode == KeyEvent.VK_D) {
            rightPressed = true;
        }

        updateMovement();
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int keyCode = e.getKeyCode();

        if (keyCode == KeyEvent.VK_W) {
            upPressed = false;
        } else if (keyCode == KeyEvent.VK_S) {
            downPressed = false;
        } else if (keyCode == KeyEvent.VK_A) {
            leftPressed = false;
        } else if (keyCode == KeyEvent.VK_D) {
            rightPressed = false;
        }

        updateMovement();
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

        move(dx, dy);
    }
}
