package com.jaga.game.generation;

import com.jaga.config.Config;
import com.jaga.game.GamePanel;
import com.jaga.game.tile.Tile;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Objects;
import java.util.Random;

public class WorldGeneration {
    private GamePanel gamePanel;
    private Tile[][] tileMap;
    private final ArrayList<Tile> tileList = new ArrayList<>();

    private boolean worldGenerated, worldSaved;

    public WorldGeneration(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
        getTileImage();
        tileMap = new Tile[Config.MAX_SCREEN_ROW][Config.MAX_SCREEN_COLUMN];
        worldGenerated = true;
        worldSaved = false;
    }

    private void getTileImage() {
        try {
            for (String tileName : Config.WORLD_MAP_TILE_NAME) {
                Tile tile = new Tile();
                tile.image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream(Config.WORLD_MAP_TILE_PATH + tileName)));
                tile.tileType = tileName.substring(0, tileName.indexOf("."));

                tileList.add(tile);

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void generateWorld() {
        int tileMapRow = tileMap.length;
        int tileMapColumn = tileMap[0].length;
        if (worldGenerated) {
            Random random = new Random();

            for (int row = 0; row < tileMapRow; row++) {
                for (int col = 0; col < tileMapColumn; col++) {
                    int tileIndex = random.nextInt(tileList.size());
                    Tile tile = tileList.get(tileIndex);
                    tile.setY(col);
                    tile.setX(row);
                    tileMap[row][col] = tile;
                    saveWorld(tileMap[row][col]);
                }
            }
            worldGenerated = false;
        }
        setWorldSaved(true);
    }

    public void saveWorld(Tile tile) {
        //Save world map to file .map lite text
        if (tileMap != null && !isWorldSaved()) {
            File worldMapFile = new File("saves/world-" + getCurrentDate() + ".map");
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(worldMapFile, true))) {
                writer.write(tile.toString() + "\n");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    private String getCurrentDate() {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss");
        Date date = new Date();
        return formatter.format(date);
    }

    public void drawTile(Graphics2D g2) {
        for (int row = 0; row < tileMap.length; row++) {
            for (int col = 0; col < tileMap[row].length; col++) {
                int tileIndex = getTileIndex(tileMap[row][col]);
                int x = col * Config.TILE_SIZE;
                int y = row * Config.TILE_SIZE;
                g2.drawImage(tileList.get(tileIndex).image, x, y, Config.TILE_SIZE, Config.TILE_SIZE, null);
            }
        }
    }

    private int getTileIndex(Tile tile) {
        for (int i = 0; i < this.tileList.size(); i++) {
            if (this.tileList.get(i) == tile)
                return i;
        }
        return -1; // Tile not found
    }

    public Tile[][] getTileMap() {
        return tileMap;
    }

    public void setTileMap(Tile[][] tileMap) {
        this.tileMap = tileMap;
    }

    public ArrayList<Tile> getTileList() {
        return tileList;
    }

    public boolean isWorldGenerated() {
        return worldGenerated;
    }

    public boolean isWorldSaved() {
        return worldSaved;
    }

    public void setWorldSaved(boolean worldSaved) {
        this.worldSaved = worldSaved;
    }
}
