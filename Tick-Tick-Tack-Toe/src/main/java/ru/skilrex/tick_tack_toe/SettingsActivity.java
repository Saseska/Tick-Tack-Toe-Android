package ru.skilrex.tick_tack_toe;

import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.widget.ToggleButton;

public class SettingsActivity extends Activity {

    ToggleButton tglBtn;
    SharedPreferences sPref;
    boolean tglBtnChecked = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        tglBtn = (ToggleButton) findViewById(R.id.tglBtnOrient);
        loadSettings();
        tglBtn.setChecked(tglBtnChecked);

        if(tglBtnChecked){ //landscape
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        } else { //portait
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        }
    }

    public void onClick(View view){
        loadSettings();
        switch (view.getId()){
            case R.id.tglBtnOrient:
                if(!tglBtnChecked){
                    tglBtn.setChecked(!tglBtnChecked);
                    tglBtnChecked = true;
                } else {
                    tglBtn.setChecked(!tglBtnChecked);
                    tglBtnChecked = false;
                }
        }
        saveSettings();
    }

    void saveSettings(){
        sPref = getSharedPreferences("Settings", MODE_PRIVATE);
        SharedPreferences.Editor editor = sPref.edit();
        editor.putBoolean("orientation", tglBtnChecked);
        editor.commit();
    }

    void loadSettings(){
        sPref = getSharedPreferences("Settings", MODE_PRIVATE);
        tglBtnChecked = sPref.getBoolean("orientation", tglBtnChecked);
    }
    
}
