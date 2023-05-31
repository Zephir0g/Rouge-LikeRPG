package com.jaga.core;

import com.jaga.config.ConfigEntity;
import com.jaga.entities.moveObj.Player;
import com.jaga.entities.render.EntityRenderer;
import com.jaga.entities.staticObj.Wall;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Game {

    public static Logger log = Logger.getLogger(Game.class.getName());

    private static boolean isFullscreen = true;
    int width;
    int height;

    public Game() {
        log.log(Level.INFO, "Start create window");
        JFrame frame = new JFrame("Rouge-like Game");
        EntityRenderer renderer = new EntityRenderer();
        frame.add(renderer);

        //Get the screen size
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        width = screenSize.width;
        height = screenSize.height;

        frame.setSize(width, height);
        frame.setResizable(false);


        // Получаем экземпляр GraphicsDevice, отвечающий за отображение приложения
        GraphicsDevice device = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Устанавливаем полноэкранный режим
        frame.setUndecorated(false); // Убираем рамки окна
        device.setFullScreenWindow(frame); // Устанавливаем окно в полноэкранный режим


        Player player = new Player(100, 100, ConfigEntity.playerWidth, ConfigEntity.playerHeight);
        Wall wall = new Wall(10, 10, 70, 90);

        renderer.addEntity(wall);
        renderer.addEntity(player);

        frame.addKeyListener(player);

        Thread gameThread = new Thread(() -> {
            while (true) {
                // Обновление состояния игры

                SwingUtilities.invokeLater(() -> {
                    renderer.repaint(); // Перерисовка экрана
                });

                try {
                    Thread.sleep(16); // Задержка для обновления окна
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });


        frame.addKeyListener(new KeyListener() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_F11) {
                    if(isFullscreen){
                        frame.dispose();
                        frame.setUndecorated(false);
                        frame.setVisible(true);
                        isFullscreen = false;
                    } else {
                        frame.dispose();
                        frame.setUndecorated(true); // Убираем рамки окна
                        device.setFullScreenWindow(frame);
                        frame.setVisible(true);
                        isFullscreen = true;
                    }
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

        frame.setVisible(true);
        log.log(Level.INFO, "Start game");
        gameThread.start();
    }


}
