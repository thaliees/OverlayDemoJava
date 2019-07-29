package com.thaliees.overlaydemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private ImageView imgSettings, imgMenu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imgSettings = findViewById(R.id.ic_setting);
        imgMenu = findViewById(R.id.ic_menu);

        imgSettings.setOnClickListener(this);
        imgMenu.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ic_setting:
                Toast.makeText(this, "Settings icon clicked", Toast.LENGTH_LONG).show();
                break;

            case R.id.ic_menu:
                Toast.makeText(this, "Menu icon clicked", Toast.LENGTH_LONG).show();
                break;

            default:
                break;
        }
    }
}
