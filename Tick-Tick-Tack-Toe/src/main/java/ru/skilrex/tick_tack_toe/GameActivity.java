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
import android.widget.TextView;

import ru.skilrex.tick_tack_toe.game.GameField;
import ru.skilrex.tick_tack_toe.players.Computer;
import ru.skilrex.tick_tack_toe.players.Human;
import ru.skilrex.tick_tack_toe.players.Player;

public class GameActivity extends Activity{

    Context context;
    private boolean win = false;
    private Human playerX = new Human('X');
    private Computer playerO = new Computer('O');
    private GameField gameField = new GameField(3,3);
    private boolean work;

    EditText etY;
    EditText etX;
    TextView tvGameField;
    Button btnEnter;
    Button btnStepBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        etY = (EditText) findViewById(R.id.etY);
        etX = (EditText) findViewById(R.id.etX);
        tvGameField = (TextView) findViewById(R.id.tvGameField);
        btnEnter = (Button) findViewById(R.id.btnEnter);
        btnStepBack = (Button) findViewById(R.id.btnStepBack);
        tvGameField.setText(gameField.viewPlane());
        context = getApplicationContext();
    }


    /*@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.game, menu);
        return true;
    }*/

    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    public void onBtnClick(View view){
        switch (view.getId()){
            case R.id.btnEnter:
                if(!(etY.getText().toString().equals("") || etX.getText().toString().equals(""))){
                    step();
                    etX.setText("");
                    etY.setText("");
                }
                break;
            case R.id.btnStepBack:
                if(gameField.getHistorySteps() > 1){
                    gameField.stepBack();
                    tvGameField.setText(gameField.viewPlane().toString());
                }
                break;
        }
    }


    public void step(){
        work = true;
        while (work){
            playerX.step(gameField, etY.getText().toString(), etX.getText().toString(), context);
            work = playerX.getWork();
        }
        tvGameField.setText(gameField.viewPlane());
        checkWin(playerX);

        playerO.step(gameField, etY.getText().toString(), etX.getText().toString(), context);
        tvGameField.setText(gameField.viewPlane());
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
