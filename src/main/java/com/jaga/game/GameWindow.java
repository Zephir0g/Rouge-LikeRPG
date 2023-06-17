package com.jaga.game;

import com.jaga.config.Config;

import javax.swing.*;

public class GameWindow {

    private static GamePanel gamePanel;
    public GameWindow() {
        JFrame window = new JFrame(Config.GAME_NAME);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(true);

        gamePanel = new GamePanel();
        gamePanel.startGame();
        window.add(gamePanel);

        window.pack();

        window.setLocationRelativeTo(null);
        window.setVisible(true);
    }
}
