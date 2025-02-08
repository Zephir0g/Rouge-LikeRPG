package com.jaga;

import com.jaga.game.Game;
import com.jaga.windows.MainMenu;

public class Main {
    public static void main(String[] args) {
        try{
            //Game game = new Game();
            MainMenu mainMenu = new MainMenu();
        } catch (Exception e){
            System.out.println(e.getMessage() + " " + e.getCause());
        }
    }
}