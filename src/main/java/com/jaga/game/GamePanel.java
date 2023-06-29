package com.jaga.game;

import com.jaga.config.Config;
import com.jaga.game.entity.FPSCounter;
import com.jaga.game.entity.Player;
import com.jaga.game.generation.World;
import com.jaga.game.generation.WorldGeneration;
import com.jaga.game.keyListner.KeyHandler;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel implements Runnable {

    private Thread gameThread;
    private FPSCounter fpsCounter;
    private KeyHandler keyHandler = new KeyHandler();
    private WorldGeneration worldGeneration = new WorldGeneration(this);
    private World world;

    private Player player = new Player(this, keyHandler);

    public GamePanel() {
        this.setPreferredSize(new Dimension(Config.SCREEN_WIDTH, Config.SCREEN_HEIGHT));
        this.setBackground(Color.BLACK);


        this.setDoubleBuffered(true); // For smooth rendering
        this.addKeyListener(keyHandler);
        this.setFocusable(true); // For keyListener to work

        fpsCounter = new FPSCounter();
    }

    public void startGame() {
        world = GameWindow.getWorldStatic();
        gameThread = new Thread(this);
        gameThread.start();
    }

    public void stopGame() {
        gameThread = null;
    }

    @Override
    public void run() {
        fpsCounter.start();

        long desiredFrameTime = (long) (1000.0 / Config.MAX_FPS);

        while (gameThread != null) {
            long startTime = System.currentTimeMillis();

            update();
            repaint();
            fpsCounter.update();

            long elapsedTime = System.currentTimeMillis() - startTime;

            if (elapsedTime < desiredFrameTime) {
                try {
                    Thread.sleep(desiredFrameTime - elapsedTime);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void update() {

        player.update();
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;

        //World draw
        world.drawTile(g2);

        // Draw FPS counter
        g2.setColor(Color.WHITE);
        g2.setFont(new Font("Arial", Font.BOLD, 16));
        g2.drawString("FPS: " + fpsCounter.getFPS(), 10, 20);

        // Draw player
        player.draw(g2);


        // Clear graphics
        g2.dispose();
    }
}
