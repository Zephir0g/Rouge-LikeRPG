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
        //TODO change this to load the world from a fil
        try (BufferedReader reader = new BufferedReader(new FileReader(saveWorldFile))) {

            List<Tile> tiles = new ArrayList<>();
            String line;

            while ((line = reader.readLine()) != null) {

                String[] properties = line.split(", ");

                int x = Integer.parseInt(properties[0].substring(properties[0].indexOf('=') + 1));
                int y = Integer.parseInt(properties[1].substring(properties[1].indexOf('=') + 1));
                String tileType = properties[2].substring(properties[2].indexOf("=") + 1, properties[2].indexOf('\''));

                Tile tile = new Tile();

                tiles.add(tile);

            }

            Tile[][] tileMap = new Tile[tiles.size()][1];

            for (int i = 0; i < tiles.size(); i++) {
                tileMap[i][0] = tiles.get(i);
            }

            return tileMap;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }


}
