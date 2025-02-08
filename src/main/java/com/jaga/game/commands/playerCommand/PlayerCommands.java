package com.jaga.game.commands.playerCommand;

import com.jaga.game.commands.ArgumentType;

public enum PlayerCommands {
    CREATE("create"),
    HELP("help"),
    SWITCH("switch", new ArgumentType("player code")),;
    // Добавьте другие команды, которые вам нужqны

    // Конструкторы для каждой команды
    private final String commandName;
    private final ArgumentType[] argumentTypes;

    PlayerCommands(String commandName, ArgumentType... argumentTypes) {
        this.commandName = commandName;
        this.argumentTypes = argumentTypes;
    }

    // Получение имени команды
    public String getCommandName() {
        StringBuilder builder = new StringBuilder(commandName);
        if (argumentTypes.length > 0) {
            builder.append(" <");
            for (int i = 0; i < argumentTypes.length; i++) {
                builder.append(argumentTypes[i].getName());
                if (i < argumentTypes.length - 1) {
                    builder.append(" | ");
                }
            }
            builder.append(">");
        }
        return builder.toString();
    }

    public String getCommandNameWithoutArgs() {
        return commandName;
    }


    // Получение аргументов команды
    public ArgumentType[] getArgumentTypes() {
        return argumentTypes;
    }

    public static PlayerCommands findCommand(String commandName) {
        for (PlayerCommands command : PlayerCommands.values()) {
            if (command.getCommandNameWithoutArgs().equalsIgnoreCase(commandName)) {
                return command;
            }
        }
        return null;
    }

}
