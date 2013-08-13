package ru.skilrex.tick_tack_toe.players;

import ru.skilrex.tick_tack_toe.game.GameField;

import java.util.Random;

public class Computer extends ru.skilrex.tick_tack_toe.players.Player{
    private static final char COMPUTER = 'C';

    Random random = new Random();

    public Computer(char c){
        super(c, COMPUTER);
    }

    @Override
    public void stepComputer(GameField gameField){
        System.out.println("\n Ход компьютера: ");

        if(!betterStepHorizontal(gameField) && !betterStepVertical(gameField) && !betterStepDiagonal(gameField)){
            setY(random.nextInt(gameField.getFieldLength()) + GameField.RULER_MIN_VALUE);
            setX(random.nextInt(gameField.getFieldLength()) + GameField.RULER_MIN_VALUE);
        }
    }

    //AI
    public boolean betterStepHorizontal(GameField gameField){
        boolean haveBest = false;
        int numSymb;
        int bestRow = -1;
        int bestCol;
        for(int row = 0; row < gameField.getFieldLength(); row++){
            numSymb = 0;
            for (int col = 0; col < gameField.getFieldLength(); col++){
                if(gameField.getPoint(row, col) == 'X') numSymb++;
                if(numSymb == (gameField.getNumToWin()-1)){
                    bestRow = row;
                }
            }
        }

        if(bestRow >= 0){
            for(int col = 0; col < gameField.getFieldLength(); col++){
                if(gameField.getPoint(bestRow, col) == GameField.DEFAULT_SYMBOL){
                    bestCol = col;
                    haveBest = true;
                    setY(bestRow  + GameField.RULER_MIN_VALUE);
                    setX(bestCol  + GameField.RULER_MIN_VALUE);
                }
            }
        }

        return haveBest;
    }

    public boolean betterStepVertical(GameField gameField){
        boolean haveBest = false;
        int numSymb;
        int bestRow;
        int bestCol = -1;
        for(int col = 0; col < gameField.getFieldLength(); col++){
            numSymb = 0;
            for (int row = 0; row < gameField.getFieldLength(); row++){
                if(gameField.getPoint(row, col) == 'X') numSymb++;
                if(numSymb == (gameField.getNumToWin()-1)){
                    bestCol = col;

                }
            }
        }

        if(bestCol >= 0){
            for(int row = 0; row < gameField.getFieldLength(); row++){
                if(gameField.getPoint(row, bestCol) == GameField.DEFAULT_SYMBOL){
                    bestRow = row;
                    haveBest = true;
                    setY(bestRow  + GameField.RULER_MIN_VALUE);
                    setX(bestCol  + GameField.RULER_MIN_VALUE);
                }
            }
        }

        return haveBest;
    }

    public boolean betterStepDiagonal(GameField gameField){
        boolean haveBest = false;

        int numSymb = 0;
        for(int i = 0; i < gameField.getFieldLength(); i++){
            if(gameField.getPoint(i,i) == 'X') numSymb++;
            if (numSymb == (gameField.getNumToWin()-1)){
                for(int j = 0; j < gameField.getFieldLength(); j++){
                    if(gameField.getPoint(j,j) == GameField.DEFAULT_SYMBOL){
                        haveBest = true;
                        setY(j  + GameField.RULER_MIN_VALUE);
                        setX(j  + GameField.RULER_MIN_VALUE);
                        return haveBest;
                    }
                }
            }
        }

        numSymb = 0;
        int col=0; //Столбец
        for(int row = gameField.getFieldLength() - 1; row >= 0; row--){
            if(gameField.getPoint(row, col) == 'X') numSymb++;
            col++;
            if (numSymb == (gameField.getNumToWin()-1)){
                int col2=0;
                for(int row2 = gameField.getFieldLength() - 1; row2 >= 0; row2--){
                    if(gameField.getPoint(row2, col2) == GameField.DEFAULT_SYMBOL){
                        haveBest = true;
                        setY(row2  + GameField.RULER_MIN_VALUE);
                        setX(col2  + GameField.RULER_MIN_VALUE);
                        return haveBest;
                    }
                    col2++;
                }
            }
        }

        return haveBest;
    }
}
