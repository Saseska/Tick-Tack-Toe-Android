package ru.skilrex.tick_tack_toe;

import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;

public class SetDifficultyActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setdifficulty);

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


/*    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.set_difficulty, menu);
        return true;
    }*/
    
}
