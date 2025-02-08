package com.jaga.game.entities.objects.staticObjects;
import com.jaga.core.config.ConfigEntity;
import com.jaga.core.entities.staticObjects.StaticEntity;
import com.jaga.game.Game;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;
import java.util.Objects;
import java.util.logging.Level;

public class Obstacles extends StaticEntity {


    private Image texture;

    public Obstacles (int x, int y, int width, int height) { // constructor for obstacles
        super(x, y, width, height);
        getStoneTexture(ConfigEntity.obstacleStoneTexture);
    }

    private void getStoneTexture(String texturePath){
        try{
            texture = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream(texturePath))); //set stone texture
        } catch (IOException e) {
            Game.log.log(Level.WARNING, "Obstacle_Stone texture not found");
        }
    }

    @Override
    public void draw(Graphics g) {
        g.drawImage(texture, x, y, width, height, null);

        int textureWidth = texture.getWidth(null);
        int textureHeight = texture.getHeight(null);

    }

}
