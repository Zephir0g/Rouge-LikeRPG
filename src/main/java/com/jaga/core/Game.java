package com.jaga.core;

import com.jaga.config.ConfigCore;
import com.jaga.config.ConfigEntity;
import com.jaga.config.ConfigLogger;
import com.jaga.core.entities.BasicEntity;
import com.jaga.core.entities.gameField.BasicField;
import com.jaga.core.entities.movableObjects.Player;
import com.jaga.core.entities.render.EntityRenderer;
import com.jaga.core.entities.staticObjects.FPSMeter;
import com.jaga.core.entities.staticObjects.Wall;
import com.jaga.keyListener.GameKeyListener;
import com.jaga.windows.TerminalGame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Game {

    public static Logger log = Logger.getLogger(Game.class.getName());

    private static boolean isFullscreen = true;

    private static JFrame frame;
    private static EntityRenderer renderer;



    private static GraphicsDevice device;
    private static Timer updateTimer;
    private FPSMeter fps;
    private TerminalGame terminal = new TerminalGame();
    private Player player;
    private static boolean isPaused = false;
    private static boolean isDevProfile = false;
    // private Terminal terminal = new Terminal();

    int width;
    int height;

    public Game() {
        log.setUseParentHandlers(false);
        log.addHandler(ConfigLogger.LoggerColor());
        initGraphics();


        updateTimer = new Timer(1, e -> {
            fps.update();
            player.tick();
            renderer.repaint();
        });

        initEntities();
        frame.addKeyListener(GameKeyListener.addKeyListener(terminal));

        frame.setVisible(true);
        log.log(Level.INFO, "Start game");

        updateTimer.start();
    }

    private void initGraphics() {
        log.log(Level.INFO, "Start create window");

        frame = new JFrame("Rouge-like Game");
        renderer = new EntityRenderer();
        frame.add(renderer);

        //Get the screen size
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        width = screenSize.width;
        height = screenSize.height;

        frame.setSize(width, height);
        frame.setResizable(false);

        //Fullscreen
        // Get the GraphicsDevice instance responsible for displaying the application
        device = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


        frame.setUndecorated(false); // Removing the window frame
        device.setFullScreenWindow(frame); // Set full screen mode
    }



    public static void togglePause() {
        if (isPaused) {
            isPaused = false;
            log.log(Level.INFO, "Game is paused");
            updateTimer.start();
        } else {
            isPaused = true;
            log.log(Level.INFO, "Game is unpause");
            updateTimer.stop();
        }
    }

    private void initEntities() {
        player = new Player(width / 2 - 60, height / 2 - 60, ConfigEntity.playerWidth, ConfigEntity.playerHeight);

        //renderer.addEntity(wall);
        BasicField basicField = new BasicField();
        fps = new FPSMeter(10, 10, 10, 10);
        ConfigCore.walls = basicField.creatGameFieldWalls(width, height);
        for (Wall wall : ConfigCore.walls) {
            renderer.addEntity(wall);
        }
        renderer.addEntity(player);

        frame.addKeyListener(player);
        renderer.addEntity(fps);
    }

    public static boolean isPaused() {
        return isPaused;
    }

    public static void setIsPaused(boolean isPaused) {
        Game.isPaused = isPaused;
    }

    public static void addRenderedEntity(BasicEntity entity) {
       renderer.addEntity(entity);
    }

    public static void removeRenderedEntity(BasicEntity entity) {
       renderer.removeEntity(entity);
    }

    public static boolean isIsFullscreen() {
        return isFullscreen;
    }

    public static void setIsFullscreen(boolean isFullscreen) {
        Game.isFullscreen = isFullscreen;
    }

    public static JFrame getFrame() {
        return frame;
    }
    public static GraphicsDevice getDevice() {
        return device;
    }
    public static void setDevice(GraphicsDevice device) {
        device = device;
    }

    public static boolean isIsDevProfile() {
        return isDevProfile;
    }

    public static void setIsDevProfile(boolean isDevProfile) {
        Game.isDevProfile = isDevProfile;
    }
}
