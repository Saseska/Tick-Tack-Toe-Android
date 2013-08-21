package ru.skilrex.tick_tack_toe;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import ru.skilrex.tick_tack_toe.game.StatGameInfo;

public class MainMenu extends Activity {

    SharedPreferences sPref;
    boolean orientation = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        loadSettings();
        loadStat();
        if(orientation){ //landscape
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        } else { //portait
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        }
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()){
            case R.id.action_settings:
                Intent intent = new Intent(this, SettingsActivity.class);
                startActivity(intent);
                break;
        }
        return true;
    }

    public void loadStat(){
        sPref = getSharedPreferences("Statistic", MODE_PRIVATE);
        StatGameInfo.numWins = sPref.getInt(StatGameInfo.NUM_WINS, StatGameInfo.numWins);
        StatGameInfo.numLoses = sPref.getInt(StatGameInfo.NUM_LOSES, StatGameInfo.numLoses);
    }

    public void loadSettings(){
        sPref = getSharedPreferences("Settings", MODE_PRIVATE);
        orientation = sPref.getBoolean("orientation", orientation);
    }
    
}
