package com.jaga.core.entities.render;

import com.jaga.game.Game;
import com.jaga.core.entities.BasicEntity;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.concurrent.ConcurrentHashMap;
import java.util.logging.Level;

public class EntityRenderer extends JPanel {
    private static ConcurrentHashMap<String, BasicEntity> entitiesMap;

    public EntityRenderer() {
        entitiesMap = new ConcurrentHashMap<>();
        Game.log.log(Level.INFO, "EntityRenderer created");
    }

    public void addEntity(BasicEntity entity) {
        entitiesMap.put(entity.getHashName(), entity);
    }

    public void removeEntity(BasicEntity entity) {
        entitiesMap.remove(entity.getHashName());
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        for (BasicEntity entity : entitiesMap.values()) {
            synchronized (entity) {
                entity.draw(g);
            }
        }
    }

    public static String getEntitiesString() {
        StringBuilder stringBuilder = new StringBuilder();
        for (String key : entitiesMap.keySet()) {
            stringBuilder.append(key).append("\n");
        }
        return stringBuilder.toString();
    }

    public static ConcurrentHashMap<String, BasicEntity> getEntitiesMap() {
        return entitiesMap;
    }
}
