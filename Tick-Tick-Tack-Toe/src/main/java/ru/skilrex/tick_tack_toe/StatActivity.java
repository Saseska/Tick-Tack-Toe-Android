package ru.skilrex.tick_tack_toe;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ProgressBar;
import android.widget.TextView;

import ru.skilrex.tick_tack_toe.game.StatGameInfo;

public class StatActivity extends Activity {

    TextView tvNumWins;
    TextView tvNumLoses;
    ProgressBar progressBar;

    int numWins = StatGameInfo.numWins;
    int numLoses = StatGameInfo.numLoses;
    boolean orientation = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stat);

        loadSettings();
        if(orientation){ //landscape
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        } else { //portait
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        }

        tvNumWins = (TextView) findViewById(R.id.tvNumWins);
        tvNumLoses = (TextView) findViewById(R.id.tvNumLoses);
        progressBar = (ProgressBar) findViewById(R.id.pbWinsLoses);

        tvNumWins.setText("" + numWins);
        tvNumLoses.setText("" + numLoses);

        progressBar.setMax(numWins + numLoses);
        if(numWins > numLoses) progressBar.setProgress(progressBar.getMax() - numLoses);
        else if (numWins < numLoses) progressBar.setProgress(numWins);
        else if (numWins == numLoses) progressBar.setProgress(numWins);
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

    public void loadSettings(){
        SharedPreferences sPref;
        sPref = getSharedPreferences("Settings", MODE_PRIVATE);
        orientation = sPref.getBoolean("orientation", orientation);
    }

}
