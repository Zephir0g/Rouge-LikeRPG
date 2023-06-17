package com.jaga.game.entities.objects.staticObjects;

import com.jaga.core.entities.staticObjects.StaticEntity;

import java.awt.*;

public class TmpBg extends StaticEntity {

    private Color colour;

    public TmpBg(int x, int y, int width, int height) {
        super(x, y, width, height);
    }

    @Override
    public void draw(Graphics g) {
        g.setColor(colour);
        g.fillRect(x, y, width, height);
    }
    public void setColour(Color colour){
        this.colour = colour;
    }
}
