package com.jaga.game.commands.playerCommand;

import com.jaga.game.commands.global.HelpCommandExecutor;
import com.jaga.game.commands.playerCommand.create.CreateCommandExecutor;
import com.jaga.game.commands.playerCommand.switchPlayer.SwitchPlayerCommandExecutor;
import com.jaga.windows.TerminalGame;

import java.util.Arrays;

public class PlayerCommandHub {
    public PlayerCommandHub(String[] command) {
        String commandName = null;
        String[] args = {};
        if (command.length >= 1) {
            args = Arrays.copyOfRange(command, 1, command.length);
            commandName = command[0];
        }
        PlayerCommands playerCommand = PlayerCommands.findCommand(commandName);
        if (playerCommand != null) {
            switch (playerCommand) {
                case CREATE -> new CreateCommandExecutor();
                case HELP -> new HelpCommandExecutor(playerCommand);
                case SWITCH -> new SwitchPlayerCommandExecutor(args);
                default -> TerminalGame.appendText("Unknown command, see available commands by /help", true);
            }
        }
    }
}
