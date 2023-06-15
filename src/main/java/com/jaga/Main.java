package com.jaga;

import com.jaga.game.Game;

public class Main {
    public static void main(String[] args) {
        try{
            Game game = new Game();
        } catch (Exception e){
            System.out.println(e.getMessage() + " " + e.getCause());
        }
    }
}