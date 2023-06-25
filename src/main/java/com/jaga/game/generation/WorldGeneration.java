package com.jaga.game.generation;

import com.jaga.config.Config;
import com.jaga.game.GamePanel;
import com.jaga.game.tile.Tile;

import java.awt.*;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class WorldGeneration {

    private GamePanel gamePanel;
    private Tile[][] tileMap;
    private ArrayList<Tile> tile = new ArrayList<>();

    private WorldMap worldMap;

    public WorldGeneration(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
        worldMap = new WorldMap();
    }

    public void generateWorld() {
        worldMap.generateWorld();
        tileMap = worldMap.getTileMap();
        tile = worldMap.getTileList();
    }

    public void saveWorld() {
        //TODO write saving world
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
                g2.drawImage(tile.get(tileIndex).image, x, y, Config.TILE_SIZE, Config.TILE_SIZE, null);
            }
        }
    }

    private int getTileIndex(Tile tile) {
        for (int i = 0; i < this.tile.size(); i++) {
            if (this.tile.get(i) == tile)
                return i;
        }
        return -1; // Tile not found
    }


}
