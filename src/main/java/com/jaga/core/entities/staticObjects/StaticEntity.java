package com.jaga.core.entities.staticObjects;

import com.jaga.core.entities.BasicEntity;

import java.awt.*;

public class StaticEntity extends BasicEntity {
    public StaticEntity(int x, int y, int width, int height) {
        super(x, y, width, height);
    }

    @Override
    public void draw(Graphics g) {
        // TODO Реализация отрисовки объектов
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