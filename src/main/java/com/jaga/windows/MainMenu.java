package com.jaga.windows;

import com.jaga.core.config.ConfigEntity;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;
import java.util.ResourceBundle;

public class MainMenu extends JFrame {
//    Image icon = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream(ConfigEntity.defaultPlayerTexture)));
//    ImageIcon image = new ImageIcon(icon.getScaledInstance(200, 200, Image.SCALE_SMOOTH));

    public MainMenu() throws IOException {

        // Установка размеров и положения окна в полноэкранный режим
        GraphicsEnvironment env = GraphicsEnvironment.getLocalGraphicsEnvironment();
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        env.getDefaultScreenDevice().setFullScreenWindow(this);

        // Создание и добавление компонентов в окно
        setLayout(new BorderLayout());

        // Верхнее изображение
        BufferedImage topImage = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream(ConfigEntity.MainMenuTopBottomTextures))); // set image on top
        ImagePanel topImagePanel = new ImagePanel(topImage);
        topImagePanel.setPreferredSize(new Dimension(getWidth(), getHeight() / 10));
        add(topImagePanel, BorderLayout.NORTH);

        // Нижнее изображение
        BufferedImage bottomImage = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream(ConfigEntity.MainMenuTopBottomTextures))); // set image on bottom
        ImagePanel bottomImagePanel = new ImagePanel(bottomImage);
        bottomImagePanel.setPreferredSize(new Dimension(getWidth(), getHeight() / 10));
        add(bottomImagePanel, BorderLayout.SOUTH);

        // Пользовательская панель с фоновым изображением для кнопок
        BufferedImage backgroundImage = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream(ConfigEntity.MenuImage))); // set image on buttons
        JPanel buttonPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), null);
            }
        };
        buttonPanel.setLayout(new GridBagLayout());
        buttonPanel.setOpaque(false);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(25, 0, 25, 0); // Отступы кнопок

        // Создание и добавление кнопок
        JButton playButton = new JButton("Play");
        JButton continueButton = new JButton("Continue");
        JButton saveButton = new JButton("Save");
        JButton settingsButton = new JButton("Settings");
        JButton exitButton = new JButton("Exit");

        gbc.gridx = 0;
        gbc.gridy = 0;
        buttonPanel.add(playButton, gbc);

        gbc.gridy = 1;
        buttonPanel.add(continueButton, gbc);

        gbc.gridy = 2;
        buttonPanel.add(saveButton, gbc);

        gbc.gridy = 3;
        buttonPanel.add(settingsButton, gbc);

        gbc.gridy = 4;
        buttonPanel.add(exitButton, gbc);

        add(buttonPanel, BorderLayout.CENTER);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    // Пользовательская панель для отображения изображений
    private static class ImagePanel extends JPanel {
        private final BufferedImage image;

        public ImagePanel(BufferedImage image) {
            this.image = image;
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            int width = getWidth();
            int height = getHeight();
            int x = (getWidth() - width) / 2;
            int y = (getHeight() - height) / 2;
            g.drawImage(image, x, y, width, height, null);
        }
    }

}
