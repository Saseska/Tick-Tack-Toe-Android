package ru.skilrex.tick_tack_toe;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.app.Activity;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;

import ru.skilrex.tick_tack_toe.game.GameField;
import ru.skilrex.tick_tack_toe.game.StatGameInfo;
import ru.skilrex.tick_tack_toe.players.Computer;
import ru.skilrex.tick_tack_toe.players.Human;
import ru.skilrex.tick_tack_toe.players.Player;

public class GameActivity extends Activity {

    LinearLayout llGame;
    LinearLayout ll0;
    LinearLayout ll1;
    LinearLayout ll2;

    Context context;

    final static int ONE_POINT_SIZE = 160;
    boolean win = false;
    Human playerX = new Human('X');
    Computer playerO = null;
    GameField gameField = new GameField(3,3);
    boolean work;
    boolean gameEnd = false;
    int llGameChild;
    int ll0Child;
    String difficulty;
    boolean orientation = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        loadSettings();
        if(orientation){ //landscape
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        } else { //portait
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        }

        context = getApplicationContext();


        difficulty = getIntent().getStringExtra("difficulty");
        playerO = new Computer(difficulty, 'O');

        llGame = (LinearLayout) findViewById(R.id.llGame);
        ll0 = (LinearLayout) findViewById(R.id.ll0);
        ll1 = (LinearLayout) findViewById(R.id.ll1);
        ll2 = (LinearLayout) findViewById(R.id.ll2);


        llGameChild = llGame.getChildCount();
        ll0Child = ll0.getChildCount();

    }

    public void onBtnClick(View view){
        switch (view.getId()){
            default:
                work = true;
                int x = 0, y = 0;

                LinearLayout linL = (LinearLayout) view.getParent();

                for(int i = 0; i < 3; i++){
                    x = i;
                    if(linL.getChildAt(x) == view) break;
                }

                if(linL.getId() == R.id.ll0) y = 0;
                else if(linL.getId() == R.id.ll1) y = 1;
                else if(linL.getId() == R.id.ll2) y = 2;

                while (work){
                    playerX.step(gameField, y, x);
                    work = playerX.getWork();
                }
                view.setBackgroundResource(R.drawable.x);
                view.setClickable(false);

                checkWin(playerX);
                if(gameEnd) return;

                playerO.step(gameField, x, y);
                for(int col = 0; col < ll0Child; col++ ){
                    x = col + GameField.RULER_MIN_VALUE;
                    for (int row = 0; row < llGameChild; row++){
                        y = row + GameField.RULER_MIN_VALUE;
                        if((x == gameField.getLastStepX()) && (y == gameField.getLastStepY())){
                            LinearLayout ll = (LinearLayout) llGame.getChildAt(row);
                            ll.getChildAt(col).setBackgroundResource(R.drawable.o);
                            ll.getChildAt(col).setClickable(false);
                        }
                    }
                }
                checkWin(playerO);
                break;

            case R.id.btnBack:
                if(gameField.getHistorySteps() > 1){
                    gameField.stepBack(llGame, gameField, ONE_POINT_SIZE);
                }
                break;
        }
    }

    public void checkWin(Player player){
        win = gameField.checkWin(player);
        if(win){
            gameEnd = true;
            StatGameInfo.winner = (player.getSymbol());
            StatGameInfo.plane = gameField.viewPlane().toString();
            StatGameInfo.stepsHistory = gameField.getHistory();
            Intent intent = new Intent(this, WinActivity.class);
            startActivity(intent);
            finish();
        } else if(gameField.getHistorySteps() == gameField.getMaxSteps()){
            gameEnd = true;
            StatGameInfo.winner = ('D');
            StatGameInfo.plane = gameField.viewPlane().toString();
            StatGameInfo.stepsHistory = gameField.getHistory();
            Intent intent = new Intent(this, WinActivity.class);
            startActivity(intent);
            finish();
        }
    }

    public void loadSettings(){
        SharedPreferences sPref;
        sPref = getSharedPreferences("Settings", MODE_PRIVATE);
        orientation = sPref.getBoolean("orientation", orientation);
    }
}
