package com.jaga.core.commands;

import com.jaga.core.Game;
import com.jaga.core.commands.global.EntityListCommandExecutor;
import com.jaga.core.commands.global.HelpCommandExecutor;
import com.jaga.core.commands.playerCommand.PlayerCommandHub;
import com.jaga.windows.TerminalGame;

import java.util.Arrays;
import java.util.logging.Level;

public class CommandHub {

    public CommandHub(String command) {
        String[] splitCommand = command.split(" ");
        String[] args = Arrays.copyOfRange(splitCommand, 1, splitCommand.length);

        Commands commands = Commands.findCommand(splitCommand[0]);
        if (commands != null) {
            switch (commands) {
                case HELP:
                    new HelpCommandExecutor();
                    break;
                case ENTITYLIST:
                    new EntityListCommandExecutor();
                    break;
                case PLAYER:
                    new PlayerCommandHub(args);
                    break;
                case PAUSE:
                    Game.setIsPaused(true);
                    Game.log.log(Level.INFO, "Game is paused");
                    break;
                case UNPAUSE:
                    Game.setIsPaused(false);
                    Game.log.log(Level.INFO, "Game is unpause");
                    break;
            }
        } else {
            TerminalGame.appendText("Unknown command, see available commands by /help", true);
        }
    }
}
