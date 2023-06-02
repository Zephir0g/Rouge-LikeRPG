package com.jaga.core.commands.playerCommand.playerCommandFirstArgument;

import com.jaga.config.ConfigEntity;
import com.jaga.core.Game;
import com.jaga.core.entities.movableObjects.Player;

import java.util.logging.Level;

public class CreateCommandExecutor {
    public CreateCommandExecutor(String[] args) {
        try {
            Player player = new Player(10, 10, ConfigEntity.playerWidth, ConfigEntity.playerWidth);
            Game.addRenderedEntity(player);
        } catch (Exception e) {
            Game.log.log(Level.SEVERE, "Error while creating player");
        }
    }
}
