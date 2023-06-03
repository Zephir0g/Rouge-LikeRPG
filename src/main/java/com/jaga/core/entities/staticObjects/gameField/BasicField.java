package com.jaga.core.entities.staticObjects.gameField;

import com.jaga.config.ConfigCore;
import com.jaga.config.ConfigEntity;
import com.jaga.core.Game;
import com.jaga.core.entities.staticObjects.Wall;


import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class BasicField {

    public BasicField() {
        Game.log.log(Level.INFO, "Field Created");
    }

    // red color of this log
    //INFO: Field Created
    //TODO fix Field Created log


    public void creatGameFieldWalls(int screenWidth, int screenHeight) {

        int backgroundWidth = (int) (screenWidth * 0.15);

        List<Wall> walls = new ArrayList<>();
        walls.addAll(createMainWallFrame(backgroundWidth, screenWidth, screenHeight));
        walls.addAll(createSideLinesGround(backgroundWidth, screenWidth, screenHeight));
        ConfigCore.staticEntities.addAll(walls);

    }

    private List<Wall> createMainWallFrame(int backgroundWidth, int screenWidth, int screenHeight){
        List<Wall> walls = new ArrayList<>();
        int heightCountWalls = (screenHeight / ConfigEntity.wallHeight);
        int widthCountWalls = ((screenWidth - (backgroundWidth *2 )) / ConfigEntity.wallWidth) + 1;

        Wall wallLeft = new Wall(backgroundWidth, ConfigEntity.wallHeight, ConfigEntity.wallWidth, screenHeight - ConfigEntity.wallHeight * 2);
        Wall wallRight = new Wall(screenWidth - backgroundWidth - ConfigEntity.wallWidth, 0, ConfigEntity.wallWidth, screenHeight - ConfigEntity.wallHeight);
        Wall wallTop = new Wall(backgroundWidth, 0,  (screenWidth - backgroundWidth * 2), ConfigEntity.wallHeight);
        Wall wallBottom = new Wall(backgroundWidth, screenHeight-ConfigEntity.wallHeight,screenWidth - backgroundWidth * 2, ConfigEntity.wallHeight);

        walls.add(wallLeft);
        walls.add(wallRight);
        walls.add(wallTop);
        walls.add(wallBottom);
        return walls;
    }

    private List<Wall> createSideLinesGround(int backgroundWidth, int screenWidth, int screenHeight){
        List<Wall> sideLinesGround = new ArrayList<>();
        int heightCountWalls = (screenHeight / ConfigEntity.wallHeight);
        int widthCountWalls = (backgroundWidth / ConfigEntity.wallWidth);

        Wall wallLeft = new Wall(0, 0, backgroundWidth, screenHeight, ConfigEntity.sideWallTexture);
        Wall wallRight = new Wall(screenWidth - backgroundWidth, 0, backgroundWidth, screenHeight,ConfigEntity.sideWallTexture);

        sideLinesGround.add(wallLeft);
        sideLinesGround.add(wallRight);
        return sideLinesGround;
    }

}
