package com.jaga.game.entities;

import com.jaga.core.config.ConfigCore;
import com.jaga.core.config.ConfigEntity;
import com.jaga.core.entities.movableObjects.MovableEntity;
import com.jaga.game.Game;
import com.jaga.core.entities.staticObjects.StaticEntity;

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

    private Image[] animationFrames;
    private int currentFrame;

    private int velocityX = 0;
    private int velocityY = 0;
    private boolean upPressed = false;
    private boolean downPressed = false;
    private boolean leftPressed = false;
    private boolean rightPressed = false;
    private int frameDelay = 100;
    private long lastFrameTime = System.currentTimeMillis();

    public Player(int x, int y, int width, int height) {
        super(x, y, width, height);
        loadAnimationFrames();
        currentFrame = 0;
        Game.log.log(Level.INFO, "Player created");
    }

    private void loadAnimationFrames() {
        animationFrames = new Image[3];
        try {
            // load animation frames from resources/assets/animations
            animationFrames[0] = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream(ConfigEntity.defaultPlayerTexture)));
            animationFrames[1] = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/assets/animations/testanim2.png")));
            animationFrames[2] = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/assets/animations/testanim3.png")));
        } catch (IOException e) {
            Game.log.log(Level.WARNING, "Player animation not found");
        }
    }

    @Override
    public void move(int dx, int dy) {
        x += dx;
        y += dy;
    }

    @Override
    public void draw(Graphics g) {
        g.drawImage(animationFrames[currentFrame], x, y, width, height, null);
    }

    public void tick() {
        if (!Game.isPaused()) {
            updateMovement();
        }
    }


    public boolean checkCollision(int dx, int dy) {
        List<StaticEntity> staticEntities = ConfigCore.staticEntities;
        Rectangle playerRect = new Rectangle(getX() + dx, getY() + dy, getWidth(), getHeight());

        for (StaticEntity staticEntity : staticEntities) {
            Rectangle wallRect = new Rectangle(staticEntity.getX(), staticEntity.getY(), staticEntity.getWidth(), staticEntity.getHeight());

            if (playerRect.intersects(wallRect)) {
                return true;
            }
        }

        return false;
    }


    @Override
    public void keyPressed(KeyEvent e) {
        int keyCode = e.getKeyCode();

        if (keyCode == KeyEvent.VK_W) {
            upPressed = true;
            setVelocityY(-1);
        } else if (keyCode == KeyEvent.VK_S) {
            downPressed = true;
            setVelocityY(1);
        } else if (keyCode == KeyEvent.VK_A) {
            leftPressed = true;
            setVelocityX(-1);
        } else if (keyCode == KeyEvent.VK_D) {
            rightPressed = true;
            setVelocityX(1);
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int keyCode = e.getKeyCode();

        if (keyCode == KeyEvent.VK_W) {
            upPressed = false;
            if (downPressed)
                setVelocityY(1);
            else
                setVelocityY(0);
        } else if (keyCode == KeyEvent.VK_S) {
            downPressed = false;
            if (upPressed)
                setVelocityY(-1);
            else
                setVelocityY(0);
        } else if (keyCode == KeyEvent.VK_A) {
            leftPressed = false;
            if (rightPressed)
                setVelocityX(1);
            else
                setVelocityX(0);
        } else if (keyCode == KeyEvent.VK_D) {
            rightPressed = false;
            if (leftPressed)
                setVelocityX(-1);
            else
                setVelocityX(0);
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
        // Not used
    }

    public void updateMovement() {
        int dx = 0;
        int dy = 0;

        if (upPressed) {
            dy -= ConfigEntity.playerSpeed;
        }
        if (downPressed) {
            dy += ConfigEntity.playerSpeed;
        }
        if (leftPressed) {
            dx -= ConfigEntity.playerSpeed;
        }
        if (rightPressed) {
            dx += ConfigEntity.playerSpeed;
        }

        if ((upPressed || downPressed) && (leftPressed || rightPressed)) {
            dx = (int) (dx * 0.7);
            dy = (int) (dy * 0.7);
        }

        if (!checkCollision(dx, dy)) {
            move(dx, dy);
            updateAnimationFrame();
        } else {
            // Handle collision here
            // For example, stop the movement of the player
            setVelocityX(0);
            setVelocityY(0);
        }
    }

    private void updateAnimationFrame() {
        long currentTime = System.currentTimeMillis();
        if (currentTime - lastFrameTime >= frameDelay) {
            if (upPressed || downPressed || leftPressed || rightPressed) {
                currentFrame++;
                if (currentFrame >= animationFrames.length) {
                    currentFrame = 0;
                }
            } else {
                currentFrame = 0;
            }
            lastFrameTime = currentTime;
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

    @Override
    public int getX() {
        return x;
    }

    @Override
    public int getY() {
        return y;
    }

}
