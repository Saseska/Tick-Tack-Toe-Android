package ru.skilrex.tick_tack_toe;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.app.Activity;
import android.view.View;

import ru.skilrex.tick_tack_toe.game.StatGameInfo;

public class MainMenu extends Activity {

    SharedPreferences sPref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        loadStat();
    }

    public void onClickBtn(View view){
        Intent intent = null;
        switch (view.getId()){
            case R.id.pvcBtn:
                intent = new Intent(this, SetDifficultyActivity.class);
                break;
            case R.id.statBtn:
                intent = new Intent(this, StatActivity.class);
                break;
        }
        startActivity(intent);
    }

    public void loadStat(){
        sPref = getSharedPreferences("Statistic", MODE_PRIVATE);
        StatGameInfo.numWins = sPref.getInt(StatGameInfo.NUM_WINS, StatGameInfo.numWins);
        StatGameInfo.numLoses = sPref.getInt(StatGameInfo.NUM_LOSES, StatGameInfo.numLoses);
    }
    
}
