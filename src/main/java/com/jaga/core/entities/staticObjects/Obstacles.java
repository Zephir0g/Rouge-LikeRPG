package com.jaga.core.entities.staticObjects;
import com.jaga.config.ConfigCore;
import com.jaga.config.ConfigEntity;
import com.jaga.core.Game;
import com.jaga.core.entities.BasicEntity;
import com.jaga.core.entities.render.EntityRenderer;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Random;
import java.util.logging.Level;

import static com.jaga.config.ConfigCore.height;
import static com.jaga.config.ConfigCore.width;

public class Obstacles extends BasicEntity{

    private static EntityRenderer renderer;
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
