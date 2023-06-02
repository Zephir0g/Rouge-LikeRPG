package com.jaga.core.entities;

import java.awt.*;

public abstract class BasicEntity {
    protected int x;
    protected int y;
    protected int width;
    protected int height;

    protected String hashName;


    public BasicEntity(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        createHashName();
    }

    public abstract void draw(Graphics g); // Method to draw the entity on the screen

    public void createHashName(){
        hashName = this.getClass().getSimpleName() + "_" + this.hashCode();
    };

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public String getHashName() {
        return hashName;
    }
}
