package com.jaga.game.generation;

import com.jaga.game.GamePanel;
import com.jaga.game.tile.Tile;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class LoadWorld {

    private World world;
    private GamePanel gamePanel;
    public LoadWorld(GamePanel gamePanel) {
        try {
            this.gamePanel = gamePanel;

            //loadWorld(new File(world.getWorldName()));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Tile[][] loadWorld(File saveWorldFile) {
        //TODO change this to load the world from a file


        try (BufferedReader reader = new BufferedReader(new FileReader(saveWorldFile))) {
            Tile[][] tileMap = world.getTileMap();

            String line;
            int row = 0;
            while ((line = reader.readLine()) != null) {
                String[] tileData = line.split(",");
                for (int col = 0; col < tileData.length; col++) {
                    int tileIndex = Integer.parseInt(tileData[col]);
                    if (tileIndex >= 0 && tileIndex < world.getTileList().size()) {
                        Tile tile = world.getTileList().get(tileIndex);
                        tile.setX(col);
                        tile.setY(row);
                        tileMap[row][col] = tile;
                    }
                }
                row++;
            }
            world.setTileMap(tileMap);
            return tileMap;
        } catch (IOException e) {
            e.printStackTrace();
         }
        return null;
    }


}
