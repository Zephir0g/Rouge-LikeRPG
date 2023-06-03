package com.jaga.core.commands.global;

import com.jaga.core.commands.Commands;
import com.jaga.core.commands.playerCommand.PlayerCommands;
import com.jaga.windows.TerminalGame;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class HelpCommandExecutor {
    public HelpCommandExecutor(Commands enumInstance) {
        TerminalGame.appendText("Available commands:", true);
        Arrays.stream(enumInstance.getDeclaringClass().getEnumConstants())
                .sorted(Comparator.comparing(Commands::getCommandName))
                .forEach(command -> TerminalGame.appendText(command.getCommandName(), true));
    }
    public HelpCommandExecutor(PlayerCommands enumInstance) {
        TerminalGame.appendText("Available commands:", true);
        Arrays.stream(enumInstance.getDeclaringClass().getEnumConstants())
                .sorted(Comparator.comparing(PlayerCommands::getCommandName))
                .forEach(command -> TerminalGame.appendText(command.getCommandName(), true));
    }

}
