package com.jaga.game.generation;

import com.jaga.config.Config;
import com.jaga.game.tile.Tile;

import javax.imageio.ImageIO;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Random;

public class WorldMap {
    private Tile[][] tileMap;
    private final ArrayList<Tile> tileList = new ArrayList<>();

    private boolean worldGenerated, worldSaved;

    protected WorldMap() {
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

    protected void generateWorld() {
        if (worldGenerated) {
            Random random = new Random();

            int tileMapRow = tileMap.length;
            int tileMapColumn = tileMap[0].length;

            for (int row = 0; row < tileMapRow; row++) {
                for (int col = 0; col < tileMapColumn; col++) {
                    int tileIndex = random.nextInt(tileList.size());
                    tileMap[row][col] = tileList.get(tileIndex);
                }
            }
            worldGenerated = false;
        }
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
