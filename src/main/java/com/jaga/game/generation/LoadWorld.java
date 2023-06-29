package com.jaga.game.generation;

import com.jaga.game.GamePanel;
import com.jaga.game.GameWindow;
import com.jaga.game.tile.Tile;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class LoadWorld {

    private World world;
    private GamePanel gamePanel;

    public LoadWorld(GamePanel gamePane) {
        this.gamePanel = gamePanel;
        world = GameWindow.getWorldStatic();
    }


    public void loadWorld(File saveWorldFile) {
        System.out.println("Loading world..");
        try (BufferedReader reader = new BufferedReader(new FileReader(saveWorldFile))) {
            List<Tile> tiles = new ArrayList<>();
            String line;

            while ((line = reader.readLine()) != null) {
                String[] properties = line.split(", ");
                int x = Integer.parseInt(properties[0].substring(properties[0].indexOf('=') + 1));
                int y = Integer.parseInt(properties[1].substring(properties[1].indexOf('=') + 1));
                // I have tileType='grass' and I want to get only grass
                String tileType = properties[2].substring(properties[2].indexOf('=') + 2, properties[2].length() - 2);
//                System.out.println( tileType);

                Tile tile = new Tile();
                tile.setX(x);
                tile.setY(y);
                tile.setTileType(tileType);

                tiles.add(tile);
            }

            int maxColumn = 0;

            // find max value of column
            for (Tile tile : tiles) {
                int x = tile.getY();
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
                tileMap[x][y] = tile;
            }

            world.setTileMap(tileMap);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public World getWorld() {
        return world;
    }

    public void setWorld(World world) {
        this.world = world;
    }

    public GamePanel getGamePanel() {
        return gamePanel;
    }

    public void setGamePanel(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
    }
}
