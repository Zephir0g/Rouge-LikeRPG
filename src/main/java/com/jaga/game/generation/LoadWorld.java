package com.jaga.game.generation;

import com.jaga.game.GamePanel;
import com.jaga.game.tile.Tile;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class LoadWorld {

    private World world;

    public LoadWorld() {

    }

    public Tile[][] loadWorld(File saveWorldFile) {
        try (BufferedReader reader = new BufferedReader(new FileReader(saveWorldFile))) {
            List<Tile> tiles = new ArrayList<>();
            String line;

            while ((line = reader.readLine()) != null) {
                String[] properties = line.split(", ");
                int x = Integer.parseInt(properties[0].substring(properties[0].indexOf('=') + 1));
                int y = Integer.parseInt(properties[1].substring(properties[1].indexOf('=') + 1));
                String tileType = properties[2].substring(properties[2].indexOf("=") + 1, properties[2].indexOf('\''));

                Tile tile = new Tile();
                tile.setX(x);
                tile.setY(y);
                tile.setTileType(tileType);

                tiles.add(tile);
            }

            int maxColumn = 0;

            // find max value of column
            for (Tile tile : tiles) {
                int x = tile.getX();
                if (x > maxColumn) {
                    maxColumn = x;
                }
            }

            int tileMapRow = tiles.size();
            int tileMapColumn = maxColumn + 1; // +1 because we start from 0

            Tile[][] tileMap = new Tile[tileMapRow][tileMapColumn];

            // Fill tileMap
            for (Tile tile : tiles) {
                int x = tile.getX();
                int y = tile.getY();
                tileMap[y][x] = tile;
            }

            return tileMap;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

}
