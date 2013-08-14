package ru.skilrex.tick_tack_toe;

import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.widget.TextView;

public class WinActivity extends Activity {

    TextView tvWinner;
    TextView tvGameField;
    TextView tvHistory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_win);

        tvWinner = (TextView) findViewById(R.id.tvWinner);
        tvGameField = (TextView) findViewById(R.id.tvGameField);
        tvHistory = (TextView) findViewById(R.id.tvHistory);

        tvWinner.setText(LastGameInfo.winner);
        tvGameField.setText(LastGameInfo.plane);
        tvHistory.setText(LastGameInfo.stepsHistory);
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
    
}
