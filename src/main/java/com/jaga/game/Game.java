package com.jaga.game;

import com.jaga.core.config.ConfigCore;
import com.jaga.core.config.ConfigEntity;
import com.jaga.core.config.ConfigLogger;
import com.jaga.core.entities.BasicEntity;
import com.jaga.game.commands.Commands;
import com.jaga.game.entities.Player;
import com.jaga.core.entities.render.EntityRenderer;
import com.jaga.game.entities.objects.staticObjects.FPSMeter;
import com.jaga.game.entities.objects.staticObjects.Obstacles;
import com.jaga.core.entities.staticObjects.StaticEntity;
import com.jaga.core.entities.staticObjects.initEntities.BasicField;
import com.jaga.keyListener.GameKeyListener;
import com.jaga.windows.MainMenu;
import com.jaga.windows.TerminalGame;
import org.jline.console.impl.Builtins;

import javax.swing.*;
import java.awt.*;
import java.util.logging.Level;
import java.util.logging.Logger;

import static com.jaga.core.config.ConfigCore.height;
import static com.jaga.core.config.ConfigCore.width;

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
        basicField.creatGameFieldWalls(width, height);
        for (StaticEntity wall : ConfigCore.staticEntities) {
            renderer.addEntity(wall);
        }
//        InitObstacles initObstacles = new InitObstacles();

        renderer.addEntity(player); // Add player to renderer
        frame.addKeyListener(player); // Add player to keyListener
        renderer.addEntity(fps);    // Add fps meter to renderer
        log.log(Level.INFO, "Static entities: " + ConfigCore.staticEntities.size());
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

}
