package com.jaga.core.entities.gameField;

import com.jaga.config.ConfigEntity;
<<<<<<< HEAD:src/main/java/com/jaga/core/entities/gameField/BasicField.java
import com.jaga.core.Game;
import com.jaga.core.entities.staticObjects.Wall;
=======
import com.jaga.entities.staticObj.Wall;
>>>>>>> origin/dev:src/main/java/com/jaga/entities/gameField/BasicField.java

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class BasicField {

    private static final Logger log = Logger.getLogger(BasicField.class.getName());

    public BasicField() {
        log.log(Level.INFO, "Field Created");
    }

    // red color of this log
    //INFO: Field Created
    //TODO fix Field Created log


    public List<Wall> creatGameFieldWalls(int screenWidth, int screenHeight) {

        int backgroundWidth = (int) (screenWidth * 0.15);

        List<Wall> walls = new ArrayList<>();
        int heightCountWalls = (screenHeight / ConfigEntity.wallHeight);
        int widthCountWalls = ((screenWidth - (backgroundWidth *2 )) / ConfigEntity.wallWidth) + 1;

        System.out.println("Width: " + screenWidth + " Height: " + screenHeight);
        System.out.println("WidthCount: " + widthCountWalls + " HeightCount: " + heightCountWalls);


        Wall wallLeft = new Wall(backgroundWidth, ConfigEntity.wallHeight, ConfigEntity.wallWidth, screenHeight - ConfigEntity.wallHeight * 2);
        Wall wallRight = new Wall(screenWidth - backgroundWidth, 0, ConfigEntity.wallWidth, screenHeight - ConfigEntity.wallHeight );
        Wall wallTop = new Wall(backgroundWidth, 0,  (screenWidth - backgroundWidth * 2), ConfigEntity.wallHeight);
        Wall wallBottom = new Wall(backgroundWidth, screenHeight-ConfigEntity.wallHeight,screenWidth - backgroundWidth * 2 + ConfigEntity.wallWidth, ConfigEntity.wallHeight);



       /* 
       List<Wall> background = new ArrayList<>();

        int innerX = backgroundWidth + ConfigEntity.wallWidth;
        int innerY = ConfigEntity.wallHeight;
        int innerWidth = screenWidth - (backgroundWidth * 2) - ConfigEntity.wallWidth * 2;
        int innerHeight = screenHeight - ConfigEntity.wallHeight * 2;

        Wall innerWall = new Wall(innerX, innerY, innerWidth, innerHeight);
        innerWall.setTexture(createColorTexture(innerWidth, innerHeight, Color.YELLOW));

        walls.add(innerWall);
        
        //я попытался сделать задник но у меня не получилось)
        // вернее получилось, но из-за этого персонаж не двигается :\
        // поэтому я закомментировал этот код
        */



        walls.add(wallLeft);
        walls.add(wallRight);
        walls.add(wallTop);
        walls.add(wallBottom);
        return walls;
    }

    /*
    private BufferedImage createColorTexture(int width, int height, Color color) {
        BufferedImage texture = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = texture.createGraphics();
        g2d.setColor(color);
        g2d.fillRect(0, 0, width, height);
        g2d.dispose();
        return texture;
    }
    */

}
