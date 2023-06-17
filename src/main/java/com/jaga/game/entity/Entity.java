package com.jaga.game.entity;

import java.awt.*;
import java.awt.image.BufferedImage;

public abstract class Entity {

    public int x, y;
    public double speed;

    public BufferedImage up1, up2, down1, down2, left1, left2, right1, right2;
    public String direction;
    public int animation = 0;
    public int animationNum = 1;

    public abstract void update();
    public abstract void draw(Graphics g);
    public abstract void draw(Graphics2D g2);
}
