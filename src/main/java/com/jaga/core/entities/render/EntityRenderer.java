package com.jaga.core.entities.render;

import com.jaga.game.Game;
import com.jaga.core.entities.BasicEntity;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.logging.Level;

public class EntityRenderer extends JPanel {
    private static HashMap<String, BasicEntity> entitiesMap;

    public EntityRenderer() {
        entitiesMap = new HashMap<>();
        Game.log.log(Level.INFO, "EntityRenderer created");
    }

    public void addEntity(BasicEntity entity) {
        entitiesMap.put(entity.getHashName(), entity);
        Game.log.log(Level.INFO, "EntityRenderer added entity: " + entity.getHashName());
    }

    public void removeEntity(BasicEntity entity) {
        entitiesMap.remove(entity.getHashName());
        Game.log.log(Level.INFO, "EntityRenderer removed entity: " + entity.getHashName());
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        //write code for drawing entities from entitiesMap

        for (BasicEntity entity : entitiesMap.values()) {
            entity.draw(g);
        }
    }

    public static String getEntitiesString() {
        StringBuilder stringBuilder = new StringBuilder();
        for(String key : entitiesMap.keySet()) {
            stringBuilder.append(key).append("\n");
        }
        return stringBuilder.toString();
    }

    public static HashMap<String, BasicEntity> getEntitiesMap() {
        return entitiesMap;
    }
}
