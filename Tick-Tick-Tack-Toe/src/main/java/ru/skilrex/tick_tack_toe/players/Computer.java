package ru.skilrex.tick_tack_toe.players;

import ru.skilrex.tick_tack_toe.game.GameField;

import java.util.Random;

public class Computer extends ru.skilrex.tick_tack_toe.players.Player{
    private static final char COMPUTER = 'C';
    int difficulty = 0;

    Random random = new Random();

    public Computer(String difficulty, char c){
        super(c, COMPUTER);

        if(difficulty.equals("easy")) this.difficulty = 1;
        else if (difficulty.equals("medium")) this.difficulty = 2;
        else if (difficulty.equals("hard")) this.difficulty = 3;
    }

    @Override
    public void stepComputer(GameField gameField){
        switch (difficulty){
            case 1:
                if (betterStep(gameField, 'O')){
                    randomStep(gameField);
                }
                break;
            case 2:
                if (betterStep(gameField, 'O')){ //Поиск посл хода для противника
                    if(betterStep(gameField, 'X')){ //Поиск посл хода для себя
                        randomStep(gameField); //Рандом, на случай, если нет подходящего хода
                    }
                }
                break;
            case 3:
                if (betterStep(gameField, 'O')){ //Поиск посл хода для противника
                    if(betterStep(gameField, 'X')){ //Поиск посл хода для себя
                        if(stepInCenter(gameField)){
                            if(stepInCorner(gameField)){ //Бить в углы если нет более подходящего хода.
                                randomStep(gameField);
                            }
                        }
                    }
                }
                break;
        }
    }

    //AI

    //Метод проверки на победу
    public boolean betterStep(GameField gameField, char player){ //Закрывает последнее место, для победы, для выбранного игрока - player.
        if(!betterStepDiagonal(gameField, player) && !betterStepHorizontal(gameField, player) && !betterStepVertical(gameField, player)){
            return true;
        } else return false;
    }

    public void randomStep(GameField gameField){
        setY(random.nextInt(gameField.getFieldLength()) + GameField.RULER_MIN_VALUE);
        setX(random.nextInt(gameField.getFieldLength()) + GameField.RULER_MIN_VALUE);
    }

    public boolean stepInCorner(GameField gameField){
        for (int row = 0; row <= 2; row += 2){
            for (int col = 0; col <= 2; col += 2){
                if(gameField.getPoint(row, col) == GameField.DEFAULT_SYMBOL){
                    setY(row + GameField.RULER_MIN_VALUE);
                    setX(col + GameField.RULER_MIN_VALUE);
                    return false;
                }
            }
        }
        return true;
    }

    public boolean stepInCenter(GameField gameField){
        if(gameField.getPoint(1, 1) == GameField.DEFAULT_SYMBOL){
            setY(1 + GameField.RULER_MIN_VALUE);
            setX(1 + GameField.RULER_MIN_VALUE);
            return false;
        }
        else return true;
    }

    //Методы проверок победы.
    private boolean betterStepHorizontal(GameField gameField, char player){
        boolean haveBest = false;
        int numSymb;
        int bestRow = -1;
        int bestCol;
        for(int row = 0; row < gameField.getFieldLength(); row++){
            numSymb = 0;
            for (int col = 0; col < gameField.getFieldLength(); col++){
                if(gameField.getPoint(row, col) == player) numSymb++;
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

    private boolean betterStepVertical(GameField gameField, char player){
        boolean haveBest = false;
        int numSymb;
        int bestRow;
        int bestCol = -1;
        for(int col = 0; col < gameField.getFieldLength(); col++){
            numSymb = 0;
            for (int row = 0; row < gameField.getFieldLength(); row++){
                if(gameField.getPoint(row, col) == player) numSymb++;
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

    private boolean betterStepDiagonal(GameField gameField, char player){
        boolean haveBest = false;

        int numSymb = 0;
        for(int i = 0; i < gameField.getFieldLength(); i++){
            if(gameField.getPoint(i,i) == player) numSymb++;
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
            if(gameField.getPoint(row, col) == player) numSymb++;
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