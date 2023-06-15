package com.jaga.game.commands.playerCommand.switchPlayer;

import com.jaga.game.Game;
import com.jaga.windows.TerminalGame;

public class SwitchPlayerCommandExecutor {
    public SwitchPlayerCommandExecutor(String[] args) {
        if (args.length == 0) {
            TerminalGame.appendText("Please specify player name", true);
        } else {
            try {
                String playerName = args[0];
                System.out.println("Cur player name:" + Game.getPlayer().getHashName());
                Game.switchPlayerByHashName(playerName);
                TerminalGame.appendText("Switching to player " + playerName, true);
            } catch (Exception e) {
                TerminalGame.appendText("Player not found", true);
            }
        }
    }
}
