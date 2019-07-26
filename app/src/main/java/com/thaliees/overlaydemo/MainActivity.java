package com.thaliees.overlaydemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.support.v7.widget.Toolbar;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private Toolbar toolbar;
    private TextView textTip;
    private ImageView imgSettings, imgMenu;
    private Button back, next;
    private RelativeLayout rlOverlay;
    private Integer position;
    private String[] tipsList;
    private int[] tipsImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = findViewById(R.id.toolbar);
        imgSettings = findViewById(R.id.ic_setting);
        imgMenu = findViewById(R.id.ic_menu);
        rlOverlay = findViewById(R.id.rlOverlay);
        textTip = findViewById(R.id.help);
        back = findViewById(R.id.button_back);
        next = findViewById(R.id.button_next);

        imgSettings.setOnClickListener(this);
        imgMenu.setOnClickListener(this);
        back.setOnClickListener(this);
        next.setOnClickListener(this);

        showButtonBack(true);
        position = 0;
        initLists();
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

            case R.id.button_next:
                position++;
                if (position == 1) showButtonBack(false);

                if (position == tipsList.length - 1) next.setText(getString(R.string.button_start));
                if (position > tipsList.length - 1) initContent();
                else {
                    textTip.setCompoundDrawablesRelativeWithIntrinsicBounds(0, tipsImage[position], 0, 0);
                    textTip.setText(tipsList[position]);
                }
                break;

            case R.id.button_back:
                if (position == tipsList.length - 1) next.setText(getString(R.string.button_next));

                position--;
                textTip.setCompoundDrawablesRelativeWithIntrinsicBounds(0, tipsImage[position], 0, 0);
                textTip.setText(tipsList[position]);
                if (position == 0) showButtonBack(true);

                break;

            default:
                break;
        }
    }

    private void initLists(){
        // Define all the help texts
        tipsList = new String[]{ getString(R.string.help_menu), getString(R.string.help_setting) };
        // Define all the icons
        tipsImage = new int[]{
                getResources().getIdentifier("ic_menu_24px", "drawable", getPackageName()),
                getResources().getIdentifier("ic_settings_20px", "drawable", getPackageName()) };
    }

    private void showButtonBack(boolean isInit){
        back.setVisibility(isInit ? View.GONE : View.VISIBLE);
    }

    private void initContent(){
        toolbar.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
        toolbar.setTitleTextColor(getResources().getColor(R.color.colorTitleToolbar));
        rlOverlay.setVisibility(View.GONE);
    }
}
