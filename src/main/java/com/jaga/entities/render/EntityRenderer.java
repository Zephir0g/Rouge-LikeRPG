package com.jaga.entities.render;

import com.jaga.core.Game;
import com.jaga.entities.BasicEntity;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class EntityRenderer extends JPanel {
    private List<BasicEntity> entities;

    public EntityRenderer() {
        entities = new ArrayList<>();
        Game.log.log(Level.INFO, "EntityRenderer created");
    }

    public void addEntity(BasicEntity entity) {
        entities.add(entity);
        Game.log.log(Level.INFO, "EntityRenderer added entity: " + entity);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        for (BasicEntity entity : entities) {
            entity.draw(g);
        }
    }
}
