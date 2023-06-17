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
    private Tile[][] tileMap;
    private boolean worldGenerated;

    public WorldGeneration(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
        tile = new Tile[3];
        tileMap = new Tile[Config.MAX_SCREEN_ROW][Config.MAX_SCREEN_COLUMN];
        getTileImage();

        worldGenerated = true;
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
        if (worldGenerated) {
            Random random = new Random();

            int tileMapRow = tileMap.length;
            int tileMapColumn = tileMap[0].length;

            for (int row = 0; row < tileMapRow; row++) {
                for (int col = 0; col < tileMapColumn; col++) {
                    int tileIndex = random.nextInt(tile.length);
                    tileMap[row][col] = tile[tileIndex];
                }
            }
            worldGenerated = false;
        }
    }

    public void drawTile(Graphics2D g2) {
        for (int row = 0; row < tileMap.length; row++) {
            for (int col = 0; col < tileMap[row].length; col++) {
                int tileIndex = getTileIndex(tileMap[row][col]);
                int x = col * Config.TILE_SIZE;
                int y = row * Config.TILE_SIZE;
                g2.drawImage(tile[tileIndex].image, x, y, Config.TILE_SIZE, Config.TILE_SIZE, null);
            }
        }
    }

    private int getTileIndex(Tile tile) {
        for (int i = 0; i < this.tile.length; i++) {
            if (this.tile[i] == tile) {
                return i;
            }
        }
        return -1; // Tile not found
    }
}
