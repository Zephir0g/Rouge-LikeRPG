package com.jaga.game.generation;

import com.jaga.game.GamePanel;
import com.jaga.game.GameWindow;
import com.jaga.game.tile.Tile;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
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
        Tile[][] tileMap = world.getTileMap();
        ArrayList<Tile> tiles = new ArrayList<>();
        ArrayList<String> tileList = new ArrayList<>();

        if (!saveWorldFile.exists()) {
            System.err.println("The save file does not exist.");
            return;
        }

        try(BufferedReader reader = new BufferedReader(new FileReader(saveWorldFile))){

            while (reader.ready()){

                //Write to String[] properties  split(", ") and replace "[" with " " and "]" with " "
                String[] properties = reader.readLine().replace("[", "").replace("]", "").split(", ");


                HashMap<String, String> tileProperties = new HashMap<>();
                for (String property : properties) {
                    String[] keyValue = property.split("=");
                    tileProperties.put(keyValue[0], keyValue[1].replace("'", ""));
                }

                Tile tile = new Tile();
                tile.setX(Integer.parseInt(tileProperties.get("x")));
                tile.setY(Integer.parseInt(tileProperties.get("y")));
                tile.setTileType(tileProperties.get("tileType"));



                tiles.add(tile);
            }

            for(Tile tile : tiles){
                tileMap[tile.getX()][tile.getY()] = tile;
            }

            world.setTileMap(tileMap);
        } catch (Exception e){
            e.getStackTrace();
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
