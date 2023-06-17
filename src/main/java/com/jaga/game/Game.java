package com.jaga.game;

import com.jaga.core.config.ConfigEntity;
import com.jaga.core.config.ConfigLogger;
import com.jaga.core.entities.BasicEntity;
import com.jaga.core.entities.render.EntityRenderer;
import com.jaga.game.entities.Player;
import com.jaga.game.entities.objects.staticObjects.FPSMeter;
import com.jaga.game.entities.objects.staticObjects.Obstacles;
import com.jaga.game.generation.WorldGenerator;
import com.jaga.keyListener.GameKeyListener;
import com.jaga.windows.TerminalGame;

import javax.swing.*;
import java.awt.*;
import java.util.logging.Level;
import java.util.logging.Logger;

import static com.jaga.core.config.ConfigCore.screenHeight;
import static com.jaga.core.config.ConfigCore.screenWidth;

public class Game {

    public static Logger log = Logger.getLogger(Game.class.getName());

    private static boolean isFullscreen = true;

    private static JFrame frame;
    private static EntityRenderer renderer;

    private static GraphicsDevice device;
    private static Timer updateTimer;
    private static Obstacles obstacles;
    private FPSMeter fps;
    private TerminalGame terminal = new TerminalGame();
    private static Player player;
    private JProgressBar progressBar;
    private static boolean isPaused = false;
    private static boolean isDevProfile = false;
    // private Terminal terminal = new Terminal();

    public Game() {
        log.setUseParentHandlers(false);
        log.addHandler(ConfigLogger.LoggerColor());
        initGraphics();


        updateTimer = new Timer(1, e -> {
            fps.update();
            renderer.repaint();
        });
        Timer playerMoveTimer = new Timer(1, e -> {
            player.tick();
        });

        initEntities();
        frame.addKeyListener(GameKeyListener.addKeyListener(terminal));

        frame.setVisible(true);
        log.log(Level.INFO, "Start game");

        playerMoveTimer.start();
        updateTimer.start();
    }

    private void initGraphics() {
        log.log(Level.INFO, "Start create window");

        frame = new JFrame("Rouge-like Game");
        renderer = new EntityRenderer();
        frame.add(renderer);
        progressBar = new JProgressBar();
        progressBar.setMinimum(0);
        progressBar.setMaximum(100);
        progressBar.setStringPainted(true);
        frame.add(progressBar, BorderLayout.SOUTH);

        //Get the screen size
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        screenWidth = screenSize.width;
        screenHeight = screenSize.height;

        frame.setSize(screenWidth, screenHeight);
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

        player = new Player(screenWidth / 2 - 60, screenHeight / 2 - 60, ConfigEntity.playerWidth, ConfigEntity.playerHeight);
        //renderer.addEntity(wall);


        WorldGenerator worldGenerator = new WorldGenerator("world");
        //create new Thread for world generation
        Thread thread = new Thread(() -> {
            worldGenerator.generateWorld();
        });

        Timer redrawTimer = new Timer(1000, e -> {
            renderer.repaint();
        });
        redrawTimer.start();
        thread.start();

        // Add progress bar to frame and print progress
        while (worldGenerator.isGenerated()) {
            progressBar.setValue(worldGenerator.getProgress());
            progressBar.setString("World generation: " + worldGenerator.getProgress() + "%");
        }
        thread.interrupt();
        frame.remove(progressBar);

        // Add world to renderer
        renderer.addEntity(player);
        fps = new FPSMeter(10, 10, 10, 10);
        renderer.addEntity(fps);
        frame.addKeyListener(player);

       /* WorldGenerator worldGenerator = new WorldGenerator("world.txt");
        worldGenerator.generate();
        JPanel worldPanel = worldGenerator.readWorld();*/


        /*BasicField basicField = new BasicField();

        basicField.creatGameFieldWalls(width, height);
        for (StaticEntity wall : ConfigCore.staticEntities) {
            renderer.addEntity(wall);
        }
//        InitObstacles initObstacles = new InitObstacles();

        renderer.addEntity(player); // Add player to renderer
        frame.addKeyListener(player); // Add player to keyListener
        renderer.addEntity(fps);    // Add fps meter to renderer
        log.log(Level.INFO, "Static entities: " + ConfigCore.staticEntities.size());*/
    }


    public static void switchPlayerByHashName(String hashName) {
        Player newPlayer = (Player) EntityRenderer.getEntitiesMap().get(hashName);
        if (newPlayer != null) {
            frame.removeKeyListener(player);
            player = newPlayer;
            frame.addKeyListener(player);
        } else {
            // Player not found by hashName
            System.out.println("Player with hashName " + hashName + " not found.");
        }
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

    public static Player getPlayer() {
        return player;
    }

    public static void setPlayer(Player player) {
        Game.player = player;
    }

    public static EntityRenderer getRenderer() {
        return renderer;
    }
}
