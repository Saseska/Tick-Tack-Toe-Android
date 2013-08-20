package ru.skilrex.tick_tack_toe;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.view.View;
import android.widget.GridLayout;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.Toast;

import ru.skilrex.tick_tack_toe.game.GameField;
import ru.skilrex.tick_tack_toe.game.StatGameInfo;
import ru.skilrex.tick_tack_toe.players.Computer;
import ru.skilrex.tick_tack_toe.players.Human;
import ru.skilrex.tick_tack_toe.players.Player;

public class GameActivity extends Activity {

    ImageButton ib1;
    ImageButton ib2;
    ImageButton ib3;
    ImageButton ib4;
    ImageButton ib5;
    ImageButton ib6;
    ImageButton ib7;
    ImageButton ib8;
    ImageButton ib9;
    LinearLayout llGame;
    LinearLayout ll0;
    LinearLayout ll1;
    LinearLayout ll2;
    Context context;

    private final static int ONE_POINT_SIZE = 160;
    private boolean win = false;
    private Human playerX = new Human('X');
    private Computer playerO = null;
    private GameField gameField = new GameField(3,3);
    private boolean work;
    private boolean gameEnd = false;
    private int llGameChild;
    private int ll0Child;
    private String difficulty;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        context = getApplicationContext();

        difficulty = getIntent().getStringExtra("difficulty");
        playerO = new Computer(difficulty, 'O');

        ib1 = (ImageButton) findViewById(R.id.ib1);
        ib2 = (ImageButton) findViewById(R.id.ib2);
        ib3 = (ImageButton) findViewById(R.id.ib3);
        ib4 = (ImageButton) findViewById(R.id.ib4);
        ib5 = (ImageButton) findViewById(R.id.ib5);
        ib6 = (ImageButton) findViewById(R.id.ib6);
        ib7 = (ImageButton) findViewById(R.id.ib7);
        ib8 = (ImageButton) findViewById(R.id.ib8);
        ib9 = (ImageButton) findViewById(R.id.ib9);
        llGame = (LinearLayout) findViewById(R.id.llGame);
        ll0 = (LinearLayout) findViewById(R.id.ll0);
        ll1 = (LinearLayout) findViewById(R.id.ll1);
        ll2 = (LinearLayout) findViewById(R.id.ll2);


        llGameChild = llGame.getChildCount();
        ll0Child = ll0.getChildCount();

    }


    /*@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.game, menu);
        return true;
    }*/

    public void onBtnClick(View view){
        switch (view.getId()){
            default:
                work = true;
                int x = 0, y = 0;

                LinearLayout linL = (LinearLayout) view.getParent();
                //x = Math.round(view.getX() / ONE_POINT_SIZE) + GameField.RULER_MIN_VALUE;

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
}
