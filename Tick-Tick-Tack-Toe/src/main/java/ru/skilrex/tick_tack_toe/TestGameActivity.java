package ru.skilrex.tick_tack_toe;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridLayout;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import ru.skilrex.tick_tack_toe.game.GameField;
import ru.skilrex.tick_tack_toe.players.Computer;
import ru.skilrex.tick_tack_toe.players.Human;
import ru.skilrex.tick_tack_toe.players.Player;

public class TestGameActivity extends Activity {

    ImageButton ib1;
    ImageButton ib2;
    ImageButton ib3;
    ImageButton ib4;
    ImageButton ib5;
    ImageButton ib6;
    ImageButton ib7;
    ImageButton ib8;
    ImageButton ib9;
    GridLayout gl1;

    Context context;
    private boolean win = false;
    private Human playerX = new Human('X');
    private Computer playerO = new Computer('O');
    private GameField gameField = new GameField(3,3);
    private boolean work;
    int childCount;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_testgame);
        ib1 = (ImageButton) findViewById(R.id.ib1);
        ib2 = (ImageButton) findViewById(R.id.ib2);
        ib3 = (ImageButton) findViewById(R.id.ib3);
        ib4 = (ImageButton) findViewById(R.id.ib4);
        ib5 = (ImageButton) findViewById(R.id.ib5);
        ib6 = (ImageButton) findViewById(R.id.ib6);
        ib7 = (ImageButton) findViewById(R.id.ib7);
        ib8 = (ImageButton) findViewById(R.id.ib8);
        ib9 = (ImageButton) findViewById(R.id.ib9);
        gl1 = (GridLayout) findViewById(R.id.gl1);
        childCount = gl1.getChildCount();
        context = getApplicationContext();
    }


    /*@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.game, menu);
        return true;
    }*/

    public void onBtnClick(View view){
        work = true;
        int x,y;
        x = Math.round(view.getX() / 100) + GameField.RULER_MIN_VALUE;
        y = Math.round(view.getY() / 100) + GameField.RULER_MIN_VALUE;

        while (work){
            playerX.step(gameField, String.valueOf(y), String.valueOf(x), context);
            work = playerX.getWork();
        }
        view.setBackgroundResource(R.drawable.x);
        view.setClickable(false);
        checkWin(playerX);

        playerO.step(gameField, String.valueOf(x), String.valueOf(y), context);
        for(int i = 0; i < childCount; i++ ){
            x = Math.round(gl1.getChildAt(i).getX() / 100) + GameField.RULER_MIN_VALUE;
            y = Math.round(gl1.getChildAt(i).getY() / 100) + GameField.RULER_MIN_VALUE;
            if((x == gameField.getLastStepX()) && (y == gameField.getLastStepY())){
                gl1.getChildAt(i).setBackgroundResource(R.drawable.o);
                gl1.getChildAt(i).setClickable(false);
            }
        }
        checkWin(playerO);
    }

    public void checkWin(Player player){
        win = gameField.checkWin(player);
        if(win){
            LastGameInfo.winner = ("Player " + player.getSymbol());
            LastGameInfo.plane = gameField.viewPlane().toString();
            LastGameInfo.stepsHistory = gameField.getHistory();
            Intent intent = new Intent(this, WinActivity.class);
            startActivity(intent);
            finish();
        }

        if(!win && (gameField.getHistorySteps() == gameField.getMaxSteps())){
            LastGameInfo.winner = ("Dead heat");
            LastGameInfo.plane = gameField.viewPlane().toString();
            LastGameInfo.stepsHistory = gameField.getHistory();
            Intent intent = new Intent(this, WinActivity.class);
            startActivity(intent);
            finish();
        }
    }
}
