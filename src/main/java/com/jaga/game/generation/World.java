package com.jaga.game.generation;

import com.jaga.config.Config;
import com.jaga.game.GamePanel;
import com.jaga.game.tile.Tile;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;

public class World {

    private Tile[][] tileMap;
    private ArrayList<Tile> tileList = new ArrayList<>();
    private String worldName;
    private GamePanel gamePanel;

    public World(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
        tileMap = new Tile[Config.MAX_SCREEN_ROW][Config.MAX_SCREEN_COLUMN];
        getTileImage();
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
        WorldGeneration worldGeneration = new WorldGeneration(gamePanel);
        worldGeneration.generateWorld();
    }

    public void loadWorld(File saveWorldFile) {
        LoadWorld loadWorld = new LoadWorld(gamePanel);
//        tileMap = loadWorld.loadWorld(saveWorldFile);
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

    protected int getTileIndex(Tile tile) {
        for (int i = 0; i < tileList.size(); i++) {
            if (tileList.get(i) == tile)
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

    public void setTileList(ArrayList<Tile> tileList) {
        this.tileList = tileList;
    }

    public void addTile(Tile tile) {
        tileList.add(tile);
    }


    public String getWorldName() {
        return worldName;
    }

    public void setWorldName(String worldName) {
        this.worldName = worldName;
    }

    @Override
    public String toString() {
        return "World{" +
                "tileMap=" + Arrays.toString(tileMap) +
                ", tileList=" + tileList +
                ", worldName='" + worldName + '\'' +
                ", gamePanel=" + gamePanel +
                '}';
    }
}
