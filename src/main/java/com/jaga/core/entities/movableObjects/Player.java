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
        // TODO Реализация отрисовки игрока (ассетами)

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
        int keyCode = e.getKeyCode();

        int dx = 0, dy = 0;


        // TODO  Create config file for controls
        switch (keyCode){
            case KeyEvent.VK_W -> dy = -50; // up
            case KeyEvent.VK_S -> dy = 50; // down
            case KeyEvent.VK_A -> dx = -50; // left
            case KeyEvent.VK_D -> dx = 50; // right
        }


        if (checkCollisionWithWalls(dx, dy)) {
            dx = 0;
            dy = 0;
        }

        move(dx, dy);
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int keyCode = e.getKeyCode();
        // Обработка отпускания клавиши управления игроком
    }

    @Override
    public void keyTyped(KeyEvent e) {
        // Не используется, оставляем пустым
    }
}
