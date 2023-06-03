package com.jaga.core.entities.staticObjects;
import com.jaga.config.ConfigEntity;
import com.jaga.core.Game;
import com.jaga.core.entities.BasicEntity;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;
import java.util.Objects;
import java.util.logging.Level;

public class Obstacles extends BasicEntity{

    private Image texture;

    public Obstacles (int x, int y, int width, int height) {
        super(x, y, width, height);
        getStoneTexture(ConfigEntity.obstacleStoneTexture);
    }

    private void getStoneTexture(String texturePath){
        try{
            texture = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream(texturePath)));
        } catch (IOException e) {
            Game.log.log(Level.WARNING, "Obstacle_Stone texture not found");
        }
    }

    @Override
    public void draw(Graphics g) {
//        g.setColor(Color.BLACK); //temp, then we will make textures
//        g.fillRect(getX(), getY(), getWidth(), getHeight());

        g.drawImage(texture, x, y, width, height, null);

        int textureWidth = texture.getWidth(null);
        int textureHeight = texture.getHeight(null);

    }

}
