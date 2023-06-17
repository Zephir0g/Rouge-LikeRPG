package com.jaga.game.generation;

import com.jaga.game.Game;
import com.jaga.game.entities.objects.staticObjects.TmpBg;

import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import java.util.logging.Level;

public class WorldGenerator {

    private static int WORLD_WIDTH, WORLD_HEIGHT;
    public static final String[] TERRAINS = {"grass", "water", "mountain"};
    private static final double TERRAIN_PROBABILITY = 0.6;
    private String fileName;
    private int progress;
    private boolean isGenerated = true;

    public WorldGenerator(String fileName) {
        String timestamp = new SimpleDateFormat("yyyy_MM_dd_HH_mm_ss").format(new Date());
        this.fileName = "saves/" + fileName + "_" + timestamp + ".txt";
    }


    public void generateWorld() {
        Random random = new Random();
        WORLD_WIDTH = random.nextInt(200, 300) * 50;
        WORLD_HEIGHT = random.nextInt(100, 200) * 50;
        for (int y = 0; y <= WORLD_HEIGHT; y += 50) {
            for (int x = 0; x <= WORLD_WIDTH; x += 50) {
                String terrain = TERRAINS[random.nextInt(TERRAINS.length)];
                createObjectPanel(x, y, terrain);
                writeToFile(x, y, terrain);
            }

            // Display progress bar
            progress = (int) ((double) (y + 50) / WORLD_HEIGHT * 100);
            updateProgressBar(progress);
        }

        Game.log.log(Level.INFO, "World created\n" +
                "World dimensions: " + WORLD_WIDTH / 50 + "x" + WORLD_HEIGHT / 50);
        isGenerated = false;
    }

    public static void updateProgressBar(int progress) {

        String progressBar = progress + "%";

        Game.log.log(Level.INFO, "Generating world: " + progressBar);
    }

    private void writeToFile(int x, int y, String terrain) {
        final int BAR_WIDTH = 50;
        int filledWidth = BAR_WIDTH * progress / 100;
        String line = String.format("[x=%d,y=%d,terrain=%s]%n", x, y, terrain);

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName, true))) {
            writer.write(line);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public JPanel readWorld() {
        isGenerated = true;
        JPanel worldPanel = new JPanel();
        worldPanel.setLayout(null); // Установите null layout для точного позиционирования объектов

        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 3) {
                    int x = Integer.parseInt(parts[0].substring(parts[0].indexOf("=") + 1));
                    int y = Integer.parseInt(parts[1].substring(parts[1].indexOf("=") + 1));
                    String terrain = parts[2].substring(parts[2].indexOf("=") + 1, parts[2].indexOf("]"));
                    // Create an object based on the parameters and add it to JPanel
                    JPanel objectPanel = createObjectPanel(x, y, terrain);
                    worldPanel.add(objectPanel);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        isGenerated = false;
        return worldPanel;
    }

    private JPanel createObjectPanel(int x, int y, String terrain) {
        JPanel objectPanel = new JPanel();
        objectPanel.setBounds(x, y, 50, 50);

        if (terrain.equals("grass")) {
            TmpBg tmpbg = new TmpBg(x, y, 50, 50);
            tmpbg.setColour(Color.GREEN);
            Game.addRenderedEntity(tmpbg);
        } else if (terrain.equals("water")) {
            TmpBg tmpbg = new TmpBg(x, y, 50, 50);
            tmpbg.setColour(Color.BLUE);
            Game.addRenderedEntity(tmpbg);
        } else if (terrain.equals("mountain")) {
            TmpBg tmpbg = new TmpBg(x, y, 50, 50);
            tmpbg.setColour(Color.GRAY);
            Game.addRenderedEntity(tmpbg);
        }

        return objectPanel;
    }

    public int getProgress() {
        return progress;
    }

    public boolean isGenerated() {
        return isGenerated;
    }
}
