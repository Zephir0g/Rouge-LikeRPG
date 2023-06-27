package com.jaga.windows.menu;

import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.Arrays;

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
                JLabel saveLabel = new JLabel(save.getName());
                savesContentPanel.add(saveLabel);
            }
        } else {
            JLabel saveLabel = new JLabel("No saves");
            savesContentPanel.add(saveLabel);
        }
    }
}
