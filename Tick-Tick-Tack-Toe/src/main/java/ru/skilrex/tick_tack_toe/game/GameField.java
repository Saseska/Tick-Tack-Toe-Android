package ru.skilrex.tick_tack_toe.game;

import android.content.Context;
import android.widget.GridLayout;

import ru.skilrex.tick_tack_toe.R;
import ru.skilrex.tick_tack_toe.players.Player;

public class GameField {

    private final static int DEFAULT_FIELD_SIZE = 3;
    public final static char DEFAULT_SYMBOL = ' ';
    public final static int RULER_MIN_VALUE = 1;     //Мин. значение боковых линеек

    private int fieldSize = DEFAULT_FIELD_SIZE;
    private static int numToWin;
    private int maxSteps;
    private char[][] field;
    private int historySteps = 0;
    private int[] historyX;
    private int[] historyY;

    public GameField(int size, int numToWin){
        if (size > 1) {
            fieldSize = size;
            field = new char[fieldSize][fieldSize];
            this.numToWin = numToWin;
            maxSteps = fieldSize * fieldSize;
            historyX = new int[maxSteps];
            historyY = new int[maxSteps];
            this.eraseField();
        }
    }

    public int getNumToWin(){
        return numToWin;
    }

    public int getFieldLength(){
        return field.length;
    }

    public int getMaxSteps(){
        return maxSteps;
    }

    public void eraseField(){
        for(int k = 0; k < field.length; k++){
            for(int j = 0; j < field[k].length; j++){
                field[k][j] = DEFAULT_SYMBOL;
            }
        }
    }

    public StringBuilder viewPlane(){
        StringBuilder plane = new StringBuilder();
        //Создание верхней линейки
        plane.append(" | ");
        for(int i = 0; i < fieldSize; i++)
            plane.append((i + RULER_MIN_VALUE) + " | ");  //i+RULE_MIN_VALUE, чтобы линейка начиналась не с 0, а с Мин. значения линейки
        plane.append("\n");

        //Вывод поля в консоль + боковая линейка
        for(int i = 0; i < field.length; i++){
            plane.append((i + RULER_MIN_VALUE)  + "| "); //i+RULE_MIN_VALUE, чтобы линейка начиналась не с 0, а с Мин. значения линейки
            for(int j = 0; j < field[i].length; j++){
                plane.append(field[i][j] + " | ");
            }
            plane.append("\n");
        }
        return plane;
    }

    public boolean isPlayerPoint(int x, int y){
        boolean isPlayerPoint = true;
        if(field[y][x] == DEFAULT_SYMBOL) isPlayerPoint = false;
        return isPlayerPoint;
    }

    public void setPoint(char symbol, int x, int y, Context context){
        if(field[y][x] == DEFAULT_SYMBOL) {
            field[y][x] = symbol;
            Player.setWork(false);
        }
    }

    public char getPoint(int row, int col){
        return field[row][col];
    }

    public int getLastStepX(){
        return historyX[historySteps-1] + RULER_MIN_VALUE;
    }
    public int getLastStepY(){
        return historyY[historySteps-1] + RULER_MIN_VALUE;
    }

    //Методы истории ходов
    public void incHistory(){
        historySteps++;
    }

    public void addStep(int x, int y){
        historyX[historySteps] = x;
        historyY[historySteps] = y;
    }

    public void erasePoint(int x, int y){
            field[y][x] = DEFAULT_SYMBOL;
    }

    public void stepBack(GridLayout gl1, GameField gameField, int ONE_POINT_SIZE){
        int childCount = gl1.getChildCount();
        int x,y;
        for(int i = 0; i < childCount; i++ ){
            x = Math.round(gl1.getChildAt(i).getX() / ONE_POINT_SIZE) + GameField.RULER_MIN_VALUE;
            y = Math.round(gl1.getChildAt(i).getY() / ONE_POINT_SIZE) + GameField.RULER_MIN_VALUE;
            if((x == gameField.getLastStepX()) && (y == gameField.getLastStepY())){
                gl1.getChildAt(i).setBackgroundResource(R.drawable.clear);
                gl1.getChildAt(i).setClickable(true);
            }
        }
        historySteps--;
        erasePoint(historyX[historySteps], historyY[historySteps]);

        for(int i = 0; i < childCount; i++ ){
            x = Math.round(gl1.getChildAt(i).getX() / ONE_POINT_SIZE) + GameField.RULER_MIN_VALUE;
            y = Math.round(gl1.getChildAt(i).getY() / ONE_POINT_SIZE) + GameField.RULER_MIN_VALUE;
            if((x == gameField.getLastStepX()) && (y == gameField.getLastStepY())){
                gl1.getChildAt(i).setBackgroundResource(R.drawable.clear);
                gl1.getChildAt(i).setClickable(true);
            }
        }
        historySteps--;
        erasePoint(historyX[historySteps], historyY[historySteps]);
    }

    public StringBuilder getHistory(){
        StringBuilder line = new StringBuilder();
        if(historySteps == maxSteps) historySteps--;
        for (int i = 0; i<=historySteps; i++){
            if((i % 2) == 0) line.append("Player X:  "); else line.append("Player O:  ");
            line.append((historyX[i] + RULER_MIN_VALUE) + " ; " + (historyY[i] + RULER_MIN_VALUE) + "\n");
        }
        return line;
    }

    public int getHistorySteps(){
        return historySteps;
    }

    // Методы проверки победы
    public boolean checkWin(Player player){
        if(checkWinHorizontal(player) || checkWinVertical(player) || checkWinDiagonal(player)){
            System.out.println("Игрок " + player.getSymbol() + " победил!");
            return true;
        }
        return false;
    }

    private boolean checkWinHorizontal(Player player){
        int numSymb;    //Кол-во символов
        for(int row = 0; row < field.length; row++){
            numSymb = 0;
            for(int col = 0; col < field[row].length; col++){
                if(field[row][col] == player.getSymbol()) numSymb++;
            }
            if (numSymb == numToWin) return true;
        }
        return false;
    }

    private boolean checkWinVertical(Player player){
        int numSymb;
        for(int col = 0; col < field.length; col++){
            numSymb = 0;
            for(int row = 0; row < field.length; row++){
                if(field[row][col] == player.getSymbol()) numSymb++;
            }
            if (numSymb == numToWin) return true;
        }
        return false;
    }

    private boolean checkWinDiagonal(Player player){
        int numSymb = 0;

        for(int i = 0; i < field.length; i++){
            if(field[i][i] == player.getSymbol()) numSymb++;
            if (numSymb == numToWin) return true;
        }

        numSymb = 0;
        int col=0; //Столбец
        for(int row = field.length - 1; row >= 0; row--){
            if(field[row][col] == player.getSymbol()) numSymb++;
            col++;
            if (numSymb == numToWin) return true;
        }

        return false;
    }
}
