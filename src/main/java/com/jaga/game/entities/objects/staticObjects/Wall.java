package com.jaga.game.entities.objects.staticObjects;

import com.jaga.core.config.ConfigEntity;
import com.jaga.core.entities.staticObjects.StaticEntity;
import com.jaga.game.Game;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;
import java.util.Objects;
import java.util.logging.Level;

public class Wall extends StaticEntity {
    private Image texture;


    public Wall(int x, int y, int width, int height) {
        super(x, y, width, height);
        getTexture(ConfigEntity.defaultWallTexture);
    }

    public Wall(int x, int y, int width, int height, String texturePath) {
        super(x, y, width, height);
        getTexture(texturePath);
    }

    private void getTexture(String texturePath){
        try{
            texture = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream(texturePath)));
        } catch (IOException e) {
            Game.log.log(Level.WARNING, "Player texture not found");
        }
    }

    @Override
    public void draw(Graphics g) {
        g.drawImage(texture, x, y, width, height, null);

        int textureWidth = texture.getWidth(null);
        int textureHeight = texture.getHeight(null);

        // Draw texture with repetition inside the wall rectangle
        for (int tx = 0; tx < width; tx += textureWidth) {
            for (int ty = 0; ty < height; ty += textureHeight) {
                int drawWidth = Math.min(textureWidth, width - tx);
                int drawHeight = Math.min(textureHeight, height - ty);
                g.drawImage(texture, x + tx, y + ty, x + tx + drawWidth, y + ty + drawHeight,
                        0, 0, drawWidth, drawHeight, null);
            }
        }
    }

    @Override
    public void setX(int x) {
        super.setX(x);
    }

    @Override
    public void setY(int y) {
        super.setY(y);
    }

    @Override
    public void setWidth(int width) {
        super.setWidth(width);
    }

    @Override
    public void setHeight(int height) {
        super.setHeight(height);
    }
}
