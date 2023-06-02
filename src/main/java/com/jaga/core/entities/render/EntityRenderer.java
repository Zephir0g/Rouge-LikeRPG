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
        Game.log.log(Level.INFO, "EntityRenderer added entity: " + entity);
    }

    public void removeEntity(BasicEntity entity) {
        entities.remove(entity);
        Game.log.log(Level.INFO, "EntityRenderer removed entity: " + entity);
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
}
