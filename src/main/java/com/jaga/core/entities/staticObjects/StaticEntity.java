package com.jaga.core.entities.staticObjects;

import com.jaga.core.entities.BasicEntity;
import com.jaga.core.entities.movableObjects.Player;

import java.awt.*;

public abstract class StaticEntity extends BasicEntity {
    public StaticEntity(int x, int y, int width, int height) {
        super(x, y, width, height);
    }

    @Override
    public void draw(Graphics g) {
    }

    public boolean isCollidingWith(Player player) {
        // Объекты пересекаются, есть столкновение
        return player.getX() < getX() + getWidth() &&
                player.getX() + player.getWidth() > getX() &&
                player.getY() < getY() + getHeight() &&
                player.getY() + player.getHeight() > getY();
    }

    @Override
    public void setX(int x) {
        super.setX(x);
    }


    @Override
    public void setY(int y) {
        super.setY(y);
    }

    @Override
    public void setWidth(int width) {
        super.setWidth(width);
    }

    @Override
    public void setHeight(int height) {
        super.setHeight(height);
    }
}