package ru.skilrex.tick_tack_toe;

import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.widget.Button;

public class MainMenu extends Activity {
    Button pvcBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }


/*    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }*/

    public void onClickBtn(View view){
        switch (view.getId()){
            case R.id.pvcBtn:
                Intent intent = new Intent(this, GameActivity.class);
                startActivity(intent);
                break;
        }
    }
    
}
