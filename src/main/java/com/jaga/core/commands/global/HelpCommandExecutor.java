package com.jaga.core.commands.global;

import com.jaga.core.commands.Commands;
import com.jaga.windows.TerminalGame;

import java.util.Arrays;
import java.util.Comparator;

public class HelpCommandExecutor {
    public HelpCommandExecutor() {
        TerminalGame.appendText("Available commands:", true);
        //print all commands by alphabet
        Arrays.stream(Commands.values()).sorted(Comparator.comparing(Commands::getCommandName)).forEach(command -> TerminalGame.appendText(command.getCommandName(), true));

    }
}
