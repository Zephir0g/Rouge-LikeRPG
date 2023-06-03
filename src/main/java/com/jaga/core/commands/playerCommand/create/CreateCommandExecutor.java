package com.jaga.core.commands.playerCommand.create;

import com.jaga.config.ConfigCore;
import com.jaga.config.ConfigEntity;
import com.jaga.core.Game;
import com.jaga.core.entities.movableObjects.Player;

import java.util.logging.Level;

public class CreateCommandExecutor {
    public CreateCommandExecutor() {
        try {
            Player player = new Player(ConfigCore.width / 2 , ConfigCore.height / 2, ConfigEntity.playerWidth, ConfigEntity.playerHeight);
            Game.addRenderedEntity(player);
        } catch (Exception e) {
            Game.log.log(Level.SEVERE, "Error while creating player");
        }
    }
}
