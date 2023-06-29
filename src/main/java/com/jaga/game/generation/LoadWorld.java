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
        //TODO change this to load the world from a fil
        
        return null;
    }


}
