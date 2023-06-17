package com.jaga;


import com.jaga.game.GameWindow;

public class Main {
    public static void main(String[] args) {
        try{
            GameWindow gameWindow = new GameWindow();
        } catch (Exception e){
            System.out.println(e.getMessage() + " " + e.getCause());
        }
    }
}