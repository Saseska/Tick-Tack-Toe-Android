package ru.skilrex.tick_tack_toe;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

public class SetDifficultyActivity extends Activity {

    boolean orientation = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setdifficulty);

        loadSettings();
        if(orientation){ //landscape
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        } else { //portait
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        }

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

    public void onClick(View view){
        Intent intent = new Intent(this, GameActivity.class);
        switch (view.getId()){
            case R.id.btnEasy:
                intent.putExtra("difficulty", "easy");
                break;
            case R.id.btnMedium:
                intent.putExtra("difficulty", "medium");
                break;
            case R.id.btnHard:
                intent.putExtra("difficulty", "hard");
                break;
        }
        startActivity(intent);
    }

    void loadSettings(){
        SharedPreferences sPref;
        sPref = getSharedPreferences("Settings", MODE_PRIVATE);
        orientation = sPref.getBoolean("orientation", orientation);
    }
    
}
