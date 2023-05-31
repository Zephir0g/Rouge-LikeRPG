package com.jaga.entities;

import java.awt.*;

public abstract class BasicEntity {
    protected int x;
    protected int y;
    protected int width;
    protected int height;

    public BasicEntity(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    public abstract void draw(Graphics g); // Method to draw the entity on the screen
}
