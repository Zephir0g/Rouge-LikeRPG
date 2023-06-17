package com.jaga.game.entity;

public class FPSCounter {
    private long startTime;
    private int frameCount;
    private int fps;

    public void start() {
        startTime = System.nanoTime();
        frameCount = 0;
        fps = 0;
    }

    public void update() {
        frameCount++;

        long elapsedTime = System.nanoTime() - startTime;
        if (elapsedTime >= 1_000_000_000) { // 1 second has passed
            fps = frameCount;
            frameCount = 0;
            startTime = System.nanoTime();
        }
    }

    public int getFPS() {
        return fps;
    }
}
