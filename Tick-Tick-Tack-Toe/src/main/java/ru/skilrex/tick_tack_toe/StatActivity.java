package ru.skilrex.tick_tack_toe;

import android.os.Bundle;
import android.app.Activity;
import android.widget.ProgressBar;
import android.widget.TextView;

import ru.skilrex.tick_tack_toe.game.StatGameInfo;

public class StatActivity extends Activity {

    TextView tvNumWins;
    TextView tvNumLoses;
    ProgressBar progressBar;

    public int numWins = StatGameInfo.numWins;
    private int numLoses = StatGameInfo.numLoses;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stat);

        tvNumWins = (TextView) findViewById(R.id.tvNumWins);
        tvNumLoses = (TextView) findViewById(R.id.tvNumLoses);
        progressBar = (ProgressBar) findViewById(R.id.pbWinsLoses);

        tvNumWins.setText("" + numWins);
        tvNumLoses.setText("" + numLoses);

        progressBar.setMax(numWins + numLoses);
        if(numWins > numLoses) progressBar.setProgress(progressBar.getMax() - numLoses);
        else if (numWins < numLoses) progressBar.setProgress(numWins - numLoses);
        else if (numWins == numLoses) progressBar.setProgress(numWins);
    }

}
