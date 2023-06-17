package com.jaga.config;

public class Config {

    public static final String GAME_NAME = "DEFROZED";

    //GAME SETTINGS
    public static final int MAX_FPS = 60;

    //SCREEN SETTINGS
    public static final int ORIGINAL_TILE_SIZE = 16;
    public static final int TILE_SCALE = 3;
    public static final int TILE_SIZE = ORIGINAL_TILE_SIZE * TILE_SCALE;

    public static final int MAX_SCREEN_COLUMN = 60;
    public static final int MAX_SCREEN_ROW = 60;
    public static final int SCREEN_WIDTH = 720;
    public static final int SCREEN_HEIGHT = 480;


    //PLAYER SETTINGS
    public static double PLAYER_X = 100;
    public static double PLAYER_Y = 100;
    public static double PLAYER_SPEED = 3;

}
