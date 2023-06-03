package com.jaga.core.commands;

import com.jaga.core.commands.playerCommand.PlayerCommands;

public enum Commands {

    HELP("/help"),
    PAUSE("/pause"),
    UNPAUSE("/unpause"),
    ENTITYLIST("/entityList"),
    PLAYER("/player", new ArgumentType(PlayerCommands.CREATE.getCommandName()));
    // Добавьте другие команды, которые вам нужны

    // Конструкторы для каждой команды
    private final String commandName;
    private final ArgumentType[] argumentTypes;

    Commands(String commandName, ArgumentType... argumentTypes) {
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

    public static Commands findCommand(String commandName) {
        String commandNameNew = commandName.replace("/", "");
        for (Commands command : Commands.values()) {
            if (command.getCommandNameWithoutArgs().equalsIgnoreCase(commandName)) {
                return command;
            }
        }
        return null;
    }

}
