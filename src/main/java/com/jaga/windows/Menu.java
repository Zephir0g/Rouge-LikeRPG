package com.jaga.windows;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.image.BufferedImage;
import java.io.File;
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
        savesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // hide current panel and image background
                buttonPanel.setVisible(false);
                backgroundImage = null;

                // show saves panel
                savesPanel();
            }
        });
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

    private void savesPanel(){
        setLayout(new BorderLayout());

        // Create panel with scroll bar
        JPanel savesContentPanel = new JPanel();
        savesContentPanel.setLayout(new BoxLayout(savesContentPanel, BoxLayout.Y_AXIS));



        //Check Saves folder and print all saves
        File savesFolder = new File("saves");
        File[] saves = savesFolder.listFiles();
        for (File save : saves) {
            //TODO change this print to user friendly, add it`s like a button and load save
            JLabel saveLabel = new JLabel(save.getName());
            savesContentPanel.add(saveLabel);
        }


        // Create vertical scroll bar
        JScrollPane scrollPane = new JScrollPane(savesContentPanel);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        // Add scroll panel to the main panel
        add(scrollPane, BorderLayout.CENTER);

        // Create "Back" button with bottom padding
        JButton backButton = new JButton("Back");
        backButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        backButton.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // hide current panel
                savesContentPanel.setVisible(false);
                backButton.setVisible(false);


                // show main panel
                backgroundImage = loadImage("/assets/windows/v1.png");
                buttonPanel.setVisible(true);
                repaint();

            }
        });

        // Add "Back" button to the main panel
        add(backButton, BorderLayout.PAGE_END);
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

class SavesPanel extends JPanel {
    public SavesPanel() {

    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Saves");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            SavesPanel savesPanel = new SavesPanel();
            frame.getContentPane().add(savesPanel);

            frame.pack();
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        });
    }
}