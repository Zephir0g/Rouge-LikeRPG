package com.jaga.core.config;

import com.jaga.game.entities.objects.staticObjects.Obstacles;
import com.jaga.core.entities.staticObjects.StaticEntity;
import com.jaga.game.entities.objects.staticObjects.Wall;

import java.util.ArrayList;
import java.util.List;

public class ConfigCore {

    public static List<Wall> walls; // List of walls in the game

    public static int width, height;
    public static List<Obstacles> obstacles; // List of obstacles in the game

    public static List<StaticEntity> staticEntities = new ArrayList<>(); // List of all static entities in the game

}
