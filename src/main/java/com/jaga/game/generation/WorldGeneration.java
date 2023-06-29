package com.jaga.game.generation;

import com.jaga.config.Config;
import com.jaga.game.GamePanel;
import com.jaga.game.GameWindow;
import com.jaga.game.tile.Tile;

import javax.imageio.ImageIO;
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

    private final World world;
    private boolean worldGenerated, worldSaved;

    public WorldGeneration(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
        world = GameWindow.getWorldStatic();
//        getTileImage();
        worldGenerated = true;
        worldSaved = false;
    }

    private void getTileImage() {
        try {
            ArrayList<Tile> tileList = world.getTileList();
            for (String tileName : Config.WORLD_MAP_TILE_NAME) {
                Tile tile = new Tile();
                tile.image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream(Config.WORLD_MAP_TILE_PATH + tileName)));
                tile.tileType = tileName.substring(0, tileName.indexOf("."));

            }
            world.setTileList(tileList);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void generateWorld() {
        //TODO create world generation algorithm

        Tile[][] tileMap = world.getTileMap();
        ArrayList<Tile> tileList = world.getTileList();
        if (!worldSaved) {

            File savesFolder = new File("saves");
            File worldFolder = new File("saves/world-" + getCurrentDate());
            savesFolder.mkdirs();
            worldFolder.mkdirs();

            world.setWorldName("saves/" + worldFolder.getName() + "/world.map");
        }

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
            world.setTileMap(tileMap);
            worldGenerated = false;
        }
        if (!isWorldSaved()) {
            setWorldSaved(true);
        }
    }

    public void saveWorld(Tile tile) {
        //Save world map to file .map lite text
        //Java write and by symbols:

        if (world.getTileMap() != null && !isWorldSaved()) {
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(world.getWorldName(), true))) {
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
