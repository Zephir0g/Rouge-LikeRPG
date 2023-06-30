package com.jaga.windows.menu;

import com.jaga.game.GameWindow;
import com.jaga.game.generation.LoadWorld;
import com.jaga.game.generation.World;
import org.apache.commons.io.FileUtils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.util.Objects;

public class SavesPanel extends JPanel {
    private Menu menu;
    private JPanel savesContentPanel;

    public SavesPanel(Menu menu) {
        this.menu = menu;
        setLayout(new BorderLayout());

        savesContentPanel = new JPanel();
        savesContentPanel.setLayout(new BoxLayout(savesContentPanel, BoxLayout.Y_AXIS));

        loadSaves();

        JScrollPane scrollPane = new JScrollPane(savesContentPanel);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        add(scrollPane, BorderLayout.CENTER);

        JButton backButton = new JButton("Back");
        backButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        backButton.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                menu.showMenu();
            }
        });
        add(backButton, BorderLayout.PAGE_END);
    }

    private void loadSaves() {
        File savesFolder = new File("saves");
        File[] saves = savesFolder.listFiles();
        if (saves.length > 0) {
            for (File save : saves) {
                File worldFile = new File("saves/" + save.getName());

                JButton saveButton = new JButton(save.getName());
                saveButton.setAlignmentX(Component.CENTER_ALIGNMENT);
                saveButton.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));

                saveButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        // Disable the "Save" button
                        saveButton.setEnabled(false);

                        // Remove existing buttons
                        savesContentPanel.removeAll();
                        savesContentPanel.revalidate();
                        savesContentPanel.repaint();

                        // Create a new panel for the new buttons
                        JPanel newButtonsPanel = new JPanel();
                        newButtonsPanel.setLayout(new BorderLayout());

                        // Set the maximum height of the newButtonsPanel
                        newButtonsPanel.setMaximumSize(new Dimension(500, 40));

                        // Add the "Save" label at the top
                        JLabel saveLabel = new JLabel(save.getName());
                        saveLabel.setHorizontalAlignment(JLabel.CENTER);
                        newButtonsPanel.add(saveLabel, BorderLayout.NORTH);

                        // Create a panel for the buttons
                        JPanel buttonsPanel = new JPanel();
                        buttonsPanel.setLayout(new GridLayout(1, 0, 10, 0)); // Rows, Columns, Horizontal Gap, Vertical Gap

                        // Add new buttons for "Load", "Delete", and "Settings"
                        JButton loadButton = new JButton("Load");
                        JButton deleteButton = new JButton("Delete");
                        JButton settingsButton = new JButton("Settings");
                        JButton backButton = new JButton("Back");

                        // Add action listeners to the new buttons
                        loadButton.addActionListener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                GameWindow gameWindow = new GameWindow();
                                World world = gameWindow.getWorld();

                                world.loadWorld(worldFile);
                                gameWindow.init();
                            }
                        });

                        deleteButton.addActionListener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                //Delete the save
                                File worldFile = new File("saves/" + save.getName());
                                try {
                                    FileUtils.deleteDirectory(worldFile);
                                } catch (IOException ex) {
                                    throw new RuntimeException(ex);
                                }


                                // Re-enable the "Save" button
                                saveButton.setEnabled(true);

                                // Remove the new buttons panel
                                savesContentPanel.remove(newButtonsPanel);

                                // Repaint the panel to update the UI
                                savesContentPanel.revalidate();
                                savesContentPanel.repaint();
                                loadSaves();

                            }
                        });

                        settingsButton.addActionListener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                // Perform "Settings" action
                                // Add your implementation here
                            }
                        });

                        backButton.addActionListener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                // Re-enable the "Save" button
                                saveButton.setEnabled(true);

                                // Remove the new buttons panel
                                savesContentPanel.remove(newButtonsPanel);

                                // Repaint the panel to update the UI
                                savesContentPanel.revalidate();
                                savesContentPanel.repaint();
                                loadSaves();
                            }
                        });

                        // Add the new buttons to the buttons panel
                        buttonsPanel.add(loadButton);
                        buttonsPanel.add(deleteButton);
                        buttonsPanel.add(settingsButton);
                        buttonsPanel.add(backButton);

                        // Add the buttons panel to the new panel
                        newButtonsPanel.add(buttonsPanel, BorderLayout.CENTER);

                        // Add the new buttons panel to the main panel
                        savesContentPanel.add(newButtonsPanel);

                        // Repaint the panel to update the UI
                        savesContentPanel.revalidate();
                        savesContentPanel.repaint();
                    }
                });

                savesContentPanel.add(Box.createRigidArea(new Dimension(0, 10)));
                savesContentPanel.add(saveButton);
            }
        } else {
            JLabel saveLabel = new JLabel("No saves");
            savesContentPanel.add(saveLabel);
        }
    }


}
