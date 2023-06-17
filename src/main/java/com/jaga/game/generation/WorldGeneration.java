package com.jaga.game.generation;

import com.jaga.config.Config;
import com.jaga.game.GamePanel;
import com.jaga.game.tile.Tile;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;
import java.util.Objects;
import java.util.Random;

public class WorldGeneration {

    private GamePanel gamePanel;
    private Tile[] tile;

    public WorldGeneration(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
        tile = new Tile[3];
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

    public void generateWorld(Graphics2D g2) {
        Random random = new Random();

        for (int row = 0; row < Config.MAX_SCREEN_ROW; row++) {
            for (int col = 0; col < Config.MAX_SCREEN_COLUMN; col++) {
                int tileIndex = random.nextInt(tile.length);
                Tile selectedTile = tile[tileIndex];
                drawTile(g2, tileIndex, col, row);
            }
        }
    }

    private void drawTile(Graphics2D g2, int tileIndex, int col, int row) {
        int x = col * Config.TILE_SIZE;
        int y = row * Config.TILE_SIZE;
        g2.drawImage(tile[tileIndex].image, x, y, Config.TILE_SIZE, Config.TILE_SIZE, null);
    }
}
