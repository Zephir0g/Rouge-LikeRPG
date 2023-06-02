package com.jaga.core.commands;

import com.jaga.core.commands.global.EntityListCommandExecutor;
import com.jaga.core.commands.global.HelpCommandExecutor;
import com.jaga.core.commands.playerCommand.PlayerCommandHub;
import com.jaga.windows.TerminalGame;

import java.util.Arrays;

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
            }
        } else {
            TerminalGame.appendText("Unknown command, see available commands by /help", true);
        }
    }
}
