package com.jaga.core.commands.playerCommand;

import com.jaga.core.commands.playerCommand.playerCommandFirstArgument.CreateCommandExecutor;

import java.util.Arrays;

public class PlayerCommandHub {
    public PlayerCommandHub(String[] command) {
        String commandName = command[0];
        String[] args = {};
        if (command.length > 2) {
            args = Arrays.copyOfRange(command, 1, command.length);
        }
        PlayerCommands playerCommand = PlayerCommands.findCommand(commandName);
        if (playerCommand != null) {
            switch (playerCommand) {
                case CREATE:
                    new CreateCommandExecutor(args);
                    break;
            }
        }
    }
}
