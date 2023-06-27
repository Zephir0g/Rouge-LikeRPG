package com.jaga.windows;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

public class Menu extends JFrame {

    private BufferedImage backgroundImage;
    private JPanel buttonPanel;

    public Menu() {
        setTitle("Background Image Window");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(true);
        setLocationRelativeTo(null);
        setMinimumSize(new Dimension(800, 600));
        setSize(new Dimension(800, 600));

        backgroundImage = loadImage("/assets/windows/v1.png");


        //Set listner to resize background image with window size
        addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                repaint();
            }
        });

        buttonPanel = new JPanel(new GridBagLayout());

        createButtons();

        // Set panel with buttons in the center of the window
        getContentPane().setLayout(new BorderLayout());
        getContentPane().add(buttonPanel, BorderLayout.CENTER);


    }

    private void createButtons(){
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.insets = new Insets(10, 0, 10, 0);

        JButton newGameButton = createButton("New Game");
        JButton continueButton = createButton("Continue");
        JButton savesButton = createButton("Saves");
        JButton settingsButton = createButton("Settings");


        // Add buttons to the panel
        buttonPanel.add(newGameButton, gbc);
        buttonPanel.add(continueButton, gbc);
        buttonPanel.add(savesButton, gbc);
        buttonPanel.add(settingsButton, gbc);
    }

    private JButton createButton(String text) {
        JButton button = new JButton(text);
        button.setPreferredSize(new Dimension(120, 30));
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            }
        });
        return button;
    }

    private void revalidateButtons() {
        buttonPanel.revalidate();
        buttonPanel.repaint();
    }

    private BufferedImage loadImage(String imagePath) {
        try {
            return ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream(imagePath)));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        if (backgroundImage != null) {
            Image scaledImage = backgroundImage.getScaledInstance(getWidth(), getHeight(), Image.SCALE_SMOOTH);
            g.drawImage(scaledImage, 0, 0, null);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Menu window = new Menu();
            window.setVisible(true);
        });
    }
}