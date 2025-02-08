package com.jaga.keyListener;

import com.jaga.game.Game;
import com.jaga.windows.MainMenu;
import com.jaga.windows.TerminalGame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;
import java.util.logging.Level;

public class GameKeyListener {


    public static KeyListener addKeyListener(TerminalGame terminal) {

        return new KeyListener() {

            @Override
            public void keyTyped(KeyEvent keyEvent) {

            }

            @Override
            public void keyPressed(KeyEvent e) {

                if (e.getKeyCode() == KeyEvent.VK_F11) {

                    JFrame frame = Game.getFrame();
                    GraphicsDevice device = Game.getDevice();

                    if (Game.isIsFullscreen()) {
                        frame.dispose();
                        frame.setUndecorated(false);
                        frame.setVisible(true);
                        Game.setIsFullscreen(false);
                        Game.log.log(Level.INFO, "Fullscreen off");
                    } else {
                        frame.dispose();
                        frame.setUndecorated(false); // Getting rid of the title bar
                        device.setFullScreenWindow(frame);
                        Game.setDevice(device);
                        frame.setVisible(true);
                        Game.setIsFullscreen(true);
                        Game.log.log(Level.INFO, "Fullscreen on");
                    }
                } else if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
                    // Thread safe to pause game
                    if (SwingUtilities.isEventDispatchThread()) {
                        Game.togglePause();
                        /*try {
                            MainMenu mainMenu = new MainMenu();
                        } catch (IOException ex) {
                            throw new RuntimeException(ex);
                        }*/
                    } else {
                        SwingUtilities.invokeLater(Game::togglePause);
                    }
                }

                if (e.getKeyCode() == KeyEvent.VK_Q) {
                    System.exit(0);
                }
                if (e.isControlDown() && e.isShiftDown() && e.getKeyCode() == KeyEvent.VK_D) {
                    if (Game.isIsDevProfile()) {
                        Game.setIsDevProfile(false);
                        terminal.close();
                        Game.log.log(Level.INFO, "Stop dev profile");
                    } else {
                        Game.setIsDevProfile(true);
                        terminal.open();
                        Game.log.log(Level.INFO, "Start dev profile");
                    }
                }
            }

            @Override
            public void keyReleased(KeyEvent keyEvent) {

            }
        };
    }

}
