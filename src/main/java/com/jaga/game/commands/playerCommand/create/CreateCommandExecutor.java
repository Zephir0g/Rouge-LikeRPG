package com.jaga.game.commands.playerCommand.create;

import com.jaga.core.config.ConfigCore;
import com.jaga.core.config.ConfigEntity;
import com.jaga.game.Game;
import com.jaga.game.entities.Player;

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
