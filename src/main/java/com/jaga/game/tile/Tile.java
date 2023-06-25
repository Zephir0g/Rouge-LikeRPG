package com.jaga.game.tile;

import java.awt.image.BufferedImage;

public class Tile {
    public BufferedImage image;
    public String tileType;
    public boolean collision = false;


    public String getTileType() {
        return tileType;
    }
}
