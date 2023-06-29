package com.jaga.config;

public class Config {

    public static final String GAME_NAME = "DEFROZED";
    //GAME SETTINGS
    public static final int MAX_FPS = 60;

    //SCREEN SETTINGS
    public static final int ORIGINAL_TILE_SIZE = 16;
    public static final int TILE_SCALE = 3;
    public static final int TILE_SIZE = ORIGINAL_TILE_SIZE * TILE_SCALE; //48

    public static final int MAX_WORLD_COLUMN = 16;
    public static final int MAX_WORLD_ROW = 12;

    public static final int MAX_SCREEN_COLUMN = 16;
    public static final int MAX_SCREEN_ROW = 12;
    public static final int SCREEN_WIDTH = TILE_SIZE * MAX_SCREEN_COLUMN;
    public static final int SCREEN_HEIGHT = TILE_SIZE * MAX_SCREEN_ROW;

    public static int SCREEN_X = 720;
    public static int SCREEN_Y = 480;


    //PLAYER SETTINGS
    public static double PLAYER_X = 100;
    public static double PLAYER_Y = 100;
    public static double PLAYER_SPEED = 3;

    //WORLD SETTINGS
    public static final String[] WORLD_MAP_TILE_NAME = {"grass.png", "water.png", "wall.png"};
    public static final String WORLD_MAP_TILE_PATH = "/assets/entity/tile/";

}
