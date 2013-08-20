package ru.skilrex.tick_tack_toe.players;

import ru.skilrex.tick_tack_toe.game.GameField;

public class Player {
    private char symbol;
    private char playerType = 'P';
    private int x,y;
    private static boolean work;

    public Player(char c, char t){
        symbol = c;
        playerType = t;
    }

    public char getSymbol(){
        return symbol;
    }

    public void setX(int x){
        this.x = x;
    }

    public void setY(int y){
        this.y = y;
    }

    public boolean checkNum(int num, GameField field){
        return (num <= field.getFieldLength() && num >= GameField.RULER_MIN_VALUE);
    }

    public static void setWork(Boolean bool){
        work = bool;
    }

    public static boolean getWork(){
        return work;
    }

    public void step(GameField gameField, int y, int x){
        work = true;
        while (work){
            if(playerType == 'H'){
                stepHuman(gameField, y, x);
            }
            else if(playerType == 'C'){
                stepComputer(gameField);
            }
            if(!checkNum(this.y,gameField) || !checkNum(this.x,gameField)){
                if (playerType == 'H'){
                    break;
                }
                return;
            }
            gameField.setPoint(this.getSymbol(), this.x - GameField.RULER_MIN_VALUE, this.y - GameField.RULER_MIN_VALUE);
            if((playerType == 'H') && work){
                break;
            }
        }
        gameField.addStep(this.x - GameField.RULER_MIN_VALUE, this.y - GameField.RULER_MIN_VALUE);   //Добавить в память ходов
        gameField.incHistory();
    }

    public void stepHuman(GameField gameField, int y, int x){}
    public void stepComputer(GameField gameField){}
}
