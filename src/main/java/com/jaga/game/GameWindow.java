package com.jaga.game;

import com.jaga.config.Config;
import com.jaga.game.generation.World;

import javax.swing.*;
import java.io.File;

public class GameWindow {

    private GamePanel gamePanel;
    private JFrame window;
    private static World world;

    public GameWindow() {
        window = new JFrame(Config.GAME_NAME);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(true);

        try{
            File savesFolder = new File("saves");
            if(!savesFolder.exists())
                savesFolder.mkdirs();

        } catch (Exception e) {
            e.printStackTrace();
        }

        gamePanel = new GamePanel();
        world = new World(gamePanel);
    }

    public void init() {
        gamePanel.startGame();
        window.add(gamePanel);

        window.pack();

        window.setLocationRelativeTo(null);
        window.setVisible(true);
    }

    public GamePanel getGamePanel() {
        return gamePanel;
    }

    public void setGamePanel(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
    }

    public JFrame getWindow() {
        return window;
    }

    public void setWindow(JFrame window) {
        this.window = window;
    }

    public World getWorld() {
        return world;
    }

    public static World getWorldStatic() {
        return world;
    }

    public void setWorld(World world) {
        this.world = world;
    }
}
