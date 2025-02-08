package com.jaga.game.entities.objects.staticObjects;

import com.jaga.core.entities.staticObjects.StaticEntity;

import java.awt.*;
import java.text.DecimalFormat;

public class FPSMeter extends StaticEntity {
    private int frameCount;
    private long lastTime;
    private double fps;

    public FPSMeter(int x, int y, int width, int height) {
        super(x, y, width, height);
        frameCount = 0;
        lastTime = System.currentTimeMillis();
    }

    public void update() {
        frameCount++;
        long currentTime = System.currentTimeMillis();
        if (currentTime - lastTime >= 1000) {
            fps = (double) frameCount / ((currentTime - lastTime) / 1000.0);
            frameCount = 0;
            lastTime = currentTime;
        }
    }

    @Override
    public void draw(Graphics g) {
        g.setColor(Color.RED);
        g.setFont(new Font("Arial", Font.PLAIN, 12));
        g.drawString("FPS: " + new DecimalFormat("0.00").format(fps), x, y);
    }
}
