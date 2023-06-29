package com.jaga.game.entity;

import com.jaga.config.Config;
import com.jaga.game.GamePanel;
import com.jaga.game.keyListner.KeyHandler;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

public class Player extends Entity {
    private GamePanel gamePanel;
    private KeyHandler keyHandler;


    public Player(GamePanel gamePanel, KeyHandler keyHandler) {
        this.gamePanel = gamePanel;
        this.keyHandler = keyHandler;

        Config.SCREEN_X = gamePanel.getWidth() / 2 - (Config.TILE_SIZE / 2);
        Config.SCREEN_Y = gamePanel.getHeight() / 2 - (Config.TILE_SIZE / 2);

        getPlayerImage();
        direction = "down";
    }

    private void getPlayerImage() {
        try {

            up1 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/assets/entity/animation/player/boy_up_1.png")));
            up2 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/assets/entity/animation/player/boy_up_2.png")));
            down1 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/assets/entity/animation/player/boy_down_1.png")));
            down2 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/assets/entity/animation/player/boy_down_2.png")));
            left1 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/assets/entity/animation/player/boy_left_1.png")));
            left2 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/assets/entity/animation/player/boy_left_2.png")));
            right1 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/assets/entity/animation/player/boy_right_1.png")));
            right2 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/assets/entity/animation/player/boy_right_2.png")));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void update() {

        if (keyHandler.up || keyHandler.down || keyHandler.left || keyHandler.right) {
            if (keyHandler.up) {
                direction = "up";
                Config.PLAYER_Y -= Config.PLAYER_SPEED;
            }
            if (keyHandler.down) {
                direction = "down";
                Config.PLAYER_Y += Config.PLAYER_SPEED;
            }
            if (keyHandler.left) {
                direction = "left";
                Config.PLAYER_X -= Config.PLAYER_SPEED;
            }
            if (keyHandler.right) {
                direction = "right";
                Config.PLAYER_X += Config.PLAYER_SPEED;
            }

            animation++;
            if (animation > 10) {
                if (animationNum == 1)
                    animationNum = 2;
                else if (animationNum == 2)
                    animationNum = 1;
                animation = 0;
            }
        }
    }

    @Override
    public void draw(Graphics g) {

    }


    @Override
    public void draw(Graphics2D g2) {

        BufferedImage image = null;

        switch (direction) {
            case "up" -> {
                if (animationNum == 1)
                    image = up1;
                else
                    image = up2;
            }
            case "down" -> {
                if (animationNum == 1)
                    image = down1;
                else
                    image = down2;
            }
            case "left" -> {
                if (animationNum == 1)
                    image = left1;
                else
                    image = left2;
            }
            case "right" -> {
                if (animationNum == 1)
                    image = right1;
                else
                    image = right2;
            }
        }

        g2.drawImage(image, (int) Config.SCREEN_X, (int) Config.SCREEN_Y, Config.TILE_SIZE, Config.TILE_SIZE, null);

    }
}
