package ru.skilrex.tick_tack_toe;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.app.Activity;
import android.view.View;
import android.widget.TextView;

import ru.skilrex.tick_tack_toe.game.StatGameInfo;

public class WinActivity extends Activity {

    SharedPreferences sPref;
    private final String NUM_WINS = "num_wins";
    private final String NUM_LOSES = "num_loses";
    private int numWins = 0;
    private int numLoses = 0;


    TextView tvWinner;
    TextView tvGameField;
    TextView tvHistory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_win);

        updateStat(StatGameInfo.winner);

        tvWinner = (TextView) findViewById(R.id.tvWinner);
        tvGameField = (TextView) findViewById(R.id.tvGameField);
        tvHistory = (TextView) findViewById(R.id.tvHistory);

        if(StatGameInfo.winner == 'X') tvWinner.setText(R.string.win_playerx);
        else if(StatGameInfo.winner == 'O') tvWinner.setText(R.string.win_playero);
        else tvWinner.setText(R.string.win_deadheat);

        tvGameField.setText(StatGameInfo.plane);
        tvHistory.setText(StatGameInfo.stepsHistory);
    }


/*    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.win, menu);
        return true;
    }*/

    public void onClickBtn(View view){
        switch (view.getId()){
            case R.id.btnShowHist:
                tvHistory.setVisibility(view.VISIBLE);
                break;
            case R.id.btnNewGame:
                Intent intent = new Intent(this, MainMenu.class);
                startActivity(intent);
                finish();
        }
    }

    public void updateStat(char c){
        loadStat();
        if(c == 'X') numWins++;
        else if (c == 'O') numLoses++;
        saveStat();
    }

    public void loadStat(){
        sPref = getSharedPreferences("Statistic", MODE_PRIVATE);
        StatGameInfo.numWins = sPref.getInt(StatGameInfo.NUM_WINS, StatGameInfo.numWins);
        StatGameInfo.numLoses = sPref.getInt(StatGameInfo.NUM_LOSES, StatGameInfo.numLoses);

        numWins = StatGameInfo.numWins;
        numLoses = StatGameInfo.numLoses;
    }

    public void saveStat(){
        StatGameInfo.numWins = numWins;
        StatGameInfo.numLoses = numLoses;

        sPref = getSharedPreferences("Statistic", MODE_PRIVATE);
        SharedPreferences.Editor editor = sPref.edit();
        editor.putInt(StatGameInfo.NUM_WINS, StatGameInfo.numWins);
        editor.putInt(StatGameInfo.NUM_LOSES, StatGameInfo.numLoses);
        editor.commit();
    }
    
}
