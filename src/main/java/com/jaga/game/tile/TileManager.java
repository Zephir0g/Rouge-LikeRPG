package com.jaga.game.tile;

import com.jaga.config.Config;
import com.jaga.game.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;
import java.util.Objects;

public class TileManager {

    private GamePanel gamePanel;
    private Tile[] tile;

    public TileManager(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
        tile = new Tile[10];
        getTileImage();
    }

    public void getTileImage() {
        try {
            tile[0] = new Tile();
            tile[0].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/assets/entity/tile/grass.png")));
            tile[1] = new Tile();

            tile[1].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/assets/entity/tile/water.png")));
            tile[2] = new Tile();

            tile[2].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/assets/entity/tile/wall.png")));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void draw(Graphics2D g2) {
        int col = 0;
        int row = 0;
        int x = 0;
        int y = 0;

        while (col < Config.MAX_SCREEN_COLUMN && row < Config.MAX_SCREEN_ROW) {
            g2.drawImage(tile[0].image, x, y, Config.TILE_SIZE, Config.TILE_SIZE, null);
            col++;
            x += Config.TILE_SIZE;
            if (col == Config.MAX_SCREEN_COLUMN) {
                col = 0;
                row++;
                x = 0;
                y += Config.TILE_SIZE;
            }
        }
    }
}
