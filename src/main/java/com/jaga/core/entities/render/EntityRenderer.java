package com.jaga.core.entities.render;

import com.jaga.core.Game;
import com.jaga.core.entities.BasicEntity;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;

public class EntityRenderer extends JPanel {
    private static List<BasicEntity> entities;

    public EntityRenderer() {
        entities = new ArrayList<>();
        Game.log.log(Level.INFO, "EntityRenderer created");
    }

    public void addEntity(BasicEntity entity) {
        entities.add(entity);
        Game.log.log(Level.INFO, "EntityRenderer added entity: " + entity.getHashName());
    }

    public void removeEntity(BasicEntity entity) {
        entities.remove(entity);
        Game.log.log(Level.INFO, "EntityRenderer removed entity: " + entity.getHashName());
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        for (BasicEntity entity : entities) {
            entity.draw(g);
        }
    }

    public static List<BasicEntity> getEntities() {
        return entities;
    }

    public static String getEntitiesString() {
        StringBuilder stringBuilder = new StringBuilder();
        for (BasicEntity entity : entities) {
            stringBuilder.append(entity.getHashName()).append("\n");
        }
        return stringBuilder.toString();
    }
}
