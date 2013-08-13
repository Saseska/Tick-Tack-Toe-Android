package ru.skilrex.tick_tack_toe.players;

import ru.skilrex.tick_tack_toe.game.GameField;

import java.util.Scanner;

public class Human extends ru.skilrex.tick_tack_toe.players.Player{
    private static final char HUMAN = 'H';
    private Scanner scanner = new Scanner(System.in);

    public Human(char c){
        super(c, HUMAN);
    }

    @Override
    public void stepHuman(GameField gameField, String etY, String etX){
        setY(Integer.parseInt(etY));
        setX(Integer.parseInt(etX));
    }
}
