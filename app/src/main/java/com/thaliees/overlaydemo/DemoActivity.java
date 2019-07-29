package com.thaliees.overlaydemo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class DemoActivity extends AppCompatActivity implements View.OnClickListener {
    private ImageView imgSettings, imgMenu;
    private TextView textTip;
    private Button back, next;
    private Integer position;
    private String[] tipsList;
    private View[] images;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demo);

        imgSettings = findViewById(R.id.o_ic_setting);
        imgMenu = findViewById(R.id.o_ic_menu);
        textTip = findViewById(R.id.o_helpText);
        back = findViewById(R.id.button_back);
        next = findViewById(R.id.button_next);

        back.setOnClickListener(this);
        next.setOnClickListener(this);

        showButtonBack(true);
        position = 0;
        initLists();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button_next:
                position++;
                if (position == 1) showButtonBack(false);

                if (position == tipsList.length - 1) next.setText(getString(R.string.button_start));
                if (position > tipsList.length - 1) initMainActivity();
                else {
                    setBackgroundColor(position);
                    textTip.setText(tipsList[position]);
                }
                break;

            case R.id.button_back:
                if (position == tipsList.length - 1) next.setText(getString(R.string.button_next));

                position--;
                setBackgroundColor(position);
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
        // Define all the Views
        images = new View[] { imgMenu, imgSettings };
    }

    private void showButtonBack(boolean isInit){
        back.setVisibility(isInit ? View.GONE : View.VISIBLE);
    }
    
    private void setBackgroundColor(int position){
        for (int i = 0; i < tipsList.length; i++){
            images[i].setBackgroundColor(getResources().getColor(R.color.colorTransparent));
        }

        images[position].setBackgroundColor(getResources().getColor(R.color.colorTitleToolbar));
    }

    private void initMainActivity(){
        startActivity(new Intent(this, MainActivity.class));
        finish();
    }
}
