package com.jaga.core.entities.movableObjects;

import com.jaga.core.entities.BasicEntity;

import java.awt.*;

public abstract class MovableEntity extends BasicEntity {
    public MovableEntity(int x, int y, int width, int height) {
        super(x, y, width, height);
    }

    public void move(int dx, int dy) {
        // Realization of moving movable entity
        x += dx;
        y += dy;
    }

    @Override
    public void draw(Graphics g) {
    }
}
