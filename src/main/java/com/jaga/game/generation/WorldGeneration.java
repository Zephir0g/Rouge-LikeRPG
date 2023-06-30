package com.jaga.game.generation;

import com.jaga.game.GamePanel;
import com.jaga.game.GameWindow;
import com.jaga.game.tile.Tile;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
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

    public void generateWorld() {
        //TODO create world generation algorithm
        Tile[][] tileMap = world.getTileMap();
        ArrayList<Tile> tileList = world.getTileList();
        int tileMapRow = tileMap.length;
        int tileMapColumn = tileMap[0].length;

        if (!worldSaved) {
            File worldFolder = new File("saves/world-" + getCurrentDate());
            worldFolder.mkdirs();

            world.setWorldName("saves/" + worldFolder.getName() + "/world.map");
        }

        if (worldGenerated) {
            Random random = new Random();

            for (int row = 0; row < tileMapRow; row++) {
                for (int col = 0; col < tileMapColumn; col++) {
                    int tileIndex = random.nextInt(tileList.size());
                    Tile tile = tileList.get(tileIndex);
                    tile.setY(col);
                    tile.setX(row);
                    tile.setTileType(tileList.get(tileIndex).getTileType());
                    tileMap[row][col] = tile;

                    saveWorld(tileMap[row][col]);
                }
            }
            world.setTileMap(tileMap);
            worldGenerated = false;
//            printMatrix(tileMap);
        }
        if (!isWorldSaved()) {
            setWorldSaved(true);
        }
    }

    private void printMatrix(Tile[][] matrix) {
        //TODO: Remove this method

        //print mateix use for
        for (int row = 0; row < matrix.length; row++) {
            for (int col = 0; col < matrix[0].length; col++)
                System.out.print(matrix[row][col] + " ");
            System.out.println();
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

    public void setWorldGenerated(boolean worldGenerated) {
        this.worldGenerated = worldGenerated;
    }

    public boolean isWorldSaved() {
        return worldSaved;
    }

    public void setWorldSaved(boolean worldSaved) {
        this.worldSaved = worldSaved;
    }
}
