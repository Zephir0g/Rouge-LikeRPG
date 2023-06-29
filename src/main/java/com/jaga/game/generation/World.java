package com.jaga.game.generation;

import com.jaga.config.Config;
import com.jaga.game.GamePanel;
import com.jaga.game.tile.Tile;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class World {

    private Tile[][] tileMap;
    private ArrayList<Tile> tileList = new ArrayList<>();
    private String worldName;
    private GamePanel gamePanel;

    public World(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
        tileMap = new Tile[Config.MAX_WORLD_ROW][Config.MAX_WORLD_COLUMN];
        getTileImage();
    }

    private void getTileImage() {
        try {
            for (String tileName : Config.WORLD_MAP_TILE_NAME) {
                Tile tile = new Tile();
                tile.image = ImageIO.read(getClass().getResource(Config.WORLD_MAP_TILE_PATH + tileName));
                tile.tileType = tileName.substring(0, tileName.indexOf("."));
                tileList.add(tile);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void generateWorld() {
        WorldGeneration worldGeneration = new WorldGeneration(gamePanel);
        worldGeneration.generateWorld();
    }

    public void loadWorld(File saveWorldDirectory) {
        try {
            FileReader fileReader = new FileReader("saves/world-2023-06-29-23-30-53/world.map");
            File saveWorldFile = new File(saveWorldDirectory, "world.map");
            LoadWorld loadWorld = new LoadWorld(gamePanel);
            tileMap = loadWorld.loadWorld(saveWorldFile);
        } catch (FileNotFoundException e) {
            System.err.println("The 'world.map' file could not be found.");
            e.printStackTrace();
            System.out.println("Shit happens :)");
        }


    }


    public void drawTile(Graphics2D g2) {
        int offsetX = getWorldOffsetX();
        int offsetY = getWorldOffsetY();

        if (tileMap != null) {
            for (int row = 0; row < tileMap.length; row++) {
                for (int col = 0; col < tileMap[row].length; col++) {
                    int tileIndex = getTileIndex(tileMap[row][col]);
                    int x = col * Config.TILE_SIZE + offsetX;
                    int y = row * Config.TILE_SIZE + offsetY;

                    if (tileIndex >= 0 && tileIndex < tileList.size()) {
                        g2.drawImage(tileList.get(tileIndex).image, x, y, Config.TILE_SIZE, Config.TILE_SIZE, null);
                    }
                }
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

    private int getWorldOffsetX() {
        return -(int) (Config.PLAYER_X - Config.SCREEN_X);
    }

    private int getWorldOffsetY() {
        return -(int) (Config.PLAYER_Y - Config.SCREEN_Y);
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
