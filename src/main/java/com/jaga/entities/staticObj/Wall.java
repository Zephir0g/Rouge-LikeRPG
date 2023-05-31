package com.jaga.entities.staticObj;

import com.jaga.core.Game;

import java.awt.*;
import java.util.logging.Level;

public class Wall extends StaticEntity{

    public Wall(int x, int y, int width, int height) {
        super(x, y, width, height);
    }

    @Override
    public void draw(Graphics g) {
        // TODO Реализация отрисовки игрока (ассетами)
        g.setColor(Color.BLACK); // Например, установим красный цвет для стены
        g.fillRect(x, y, width, height); // Рисуем прямоугольник для представления игрока
    }
}
