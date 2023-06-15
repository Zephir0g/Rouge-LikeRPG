package com.jaga.game.entities.objects.staticObjects;

import com.jaga.core.entities.staticObjects.StaticEntity;

import java.awt.*;

public class FPSMeter extends StaticEntity {
    private int frameCount;
    private long lastTime;
    double fps;

    public FPSMeter(int x, int y, int width, int height) {
        super(x, y, width, height);
        frameCount = 0;
        lastTime = System.currentTimeMillis();
    }


    public void update() {
        frameCount++;
        long currentTime = System.currentTimeMillis();
        if (currentTime - lastTime >= 1000) {
            fps = (double) frameCount / (currentTime - lastTime) * 1000;
            frameCount = 0;
            lastTime = currentTime;
        }
    }

    public void draw(Graphics g) {
        g.setColor(Color.RED);
        g.drawString("FPS: " + String.format("%.2f", fps), x, y);
    }


}
