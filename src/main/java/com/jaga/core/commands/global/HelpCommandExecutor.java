package com.jaga.core.commands.global;

import com.jaga.core.commands.Commands;
import com.jaga.windows.TerminalGame;

public class HelpCommandExecutor {
    public HelpCommandExecutor() {
        TerminalGame.appendText("Available commands:", true);
        for (Commands command : Commands.values()) {
            TerminalGame.appendText(command.getCommandName(), true);
        }
    }
}
