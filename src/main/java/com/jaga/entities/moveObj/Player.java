package com.jaga.entities.moveObj;

import com.jaga.core.Game;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.io.IOException;
import java.util.Objects;
import java.util.logging.Level;

public class Player extends MovableEntity implements KeyListener {
    private Image texture;

    public Player(int x, int y, int width, int height) {
        super(x, y, width, height);
        getTexture();
        Game.log.log(Level.INFO,"Player created");
    }

    @Override
    public void draw(Graphics g) {
        // TODO Реализация отрисовки игрока (ассетами)

        g.drawImage(texture, x, y, width, height, null);
    }

    private void getTexture(){
        try{
            texture = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/assets/entity/player.png")));
        } catch (IOException e) {
            Game.log.log(Level.WARNING, "Player texture not found");
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int keyCode = e.getKeyCode();

        int dx = 0, dy = 0; // Смещение игрока по осям X и Y

        // Обработка нажатия клавиши управления игроком
        // TODO  Create config file for controls
        switch (keyCode){
            case KeyEvent.VK_W -> dy = -10; // up
            case KeyEvent.VK_S -> dy = 10; // down
            case KeyEvent.VK_A -> dx = -10; // left
            case KeyEvent.VK_D -> dx = 10; // right
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
