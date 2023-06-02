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

    private JFrame frame;
    private static EntityRenderer renderer;
    private GraphicsDevice device;
    private Timer updateTimer;
    private FPSMeter fps;
    private TerminalGame terminal = new TerminalGame();
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
            renderer.repaint();
        });

        initEntities();
        addKeyListener();

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

    private void addKeyListener() {
        frame.addKeyListener(new KeyListener() {
            @Override
            public void keyPressed(KeyEvent e) {

                if (e.getKeyCode() == KeyEvent.VK_F11) {
                    if (isFullscreen) {
                        frame.dispose();
                        frame.setUndecorated(false);
                        frame.setVisible(true);
                        isFullscreen = false;
                        log.log(Level.INFO, "Fullscreen off");
                    } else {
                        frame.dispose();
                        frame.setUndecorated(false); // Убираем рамки окна
                        device.setFullScreenWindow(frame);
                        frame.setVisible(true);
                        isFullscreen = true;
                        log.log(Level.INFO, "Fullscreen on");
                    }
                } else if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
                    // Thread safe to pause game
                    if (SwingUtilities.isEventDispatchThread()) {
                        togglePause();
                    } else {
                        SwingUtilities.invokeLater(() -> togglePause());
                    }
                }
                if (e.getKeyCode() == KeyEvent.VK_Q) {
                    System.exit(0);
                }
                if (e.isControlDown() && e.isShiftDown() && e.getKeyCode() == KeyEvent.VK_D) {
                    if (isDevProfile) {
                        isDevProfile = false;
                        terminal.close();
                        log.log(Level.INFO, "Stop dev profile");
                    } else {
                        isDevProfile = true;
                        terminal.open();
                        log.log(Level.INFO, "Start dev profile");
                    }
                    // Здесь можно выполнить дополнительные действия, связанные с выбором профиля "Dev"
                }
            }

            @Override
            public void keyTyped(KeyEvent e) {
                // Не используется
            }

            @Override
            public void keyReleased(KeyEvent e) {
                // Не используется
            }
        });
    }

    private void togglePause() {
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
        Player player = new Player(width / 2 - 60, height / 2 - 60, ConfigEntity.playerWidth, ConfigEntity.playerHeight);

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

}
