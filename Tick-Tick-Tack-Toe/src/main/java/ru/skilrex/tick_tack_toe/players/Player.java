package ru.skilrex.tick_tack_toe.players;

import android.content.Context;
import android.widget.Toast;

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

    public void step(GameField gameField, String etY, String etX, Context context){
        work = true;
        while (work){
            if(playerType == 'H'){
                stepHuman(gameField, etY, etX);
            }
            else if(playerType == 'C'){
                stepComputer(gameField);
            }
            if(!checkNum(y,gameField) || !checkNum(x,gameField)){
                if (playerType == 'H'){
                    Toast.makeText(context , "Input Error", Toast.LENGTH_SHORT).show();
                    break;
                }
                return;
            }
            gameField.setPoint(this.getSymbol(), x - GameField.RULER_MIN_VALUE, y - GameField.RULER_MIN_VALUE, context);
            if((playerType == 'H') && work){
                Toast.makeText(context , "Error, point fill", Toast.LENGTH_SHORT).show();
                break;
            }
        }
        gameField.addStep(x - GameField.RULER_MIN_VALUE, y - GameField.RULER_MIN_VALUE);   //Добавить в память ходов
        gameField.incHistory();
    }

    public void stepHuman(GameField gameField, String etY, String etX){}
    public void stepComputer(GameField gameField){}
}
