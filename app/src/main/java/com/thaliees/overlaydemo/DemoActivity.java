package com.thaliees.overlaydemo;

import android.content.Intent;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.Button;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class DemoActivity extends AppCompatActivity implements View.OnClickListener {
    // All our tips
    private static final int TIP_1 = 1;
    private static final int TIP_2 = 2;
    private static final int TIP_3 = 3;

    // Define the duration of CountDownTimer
    private static int DEFAULT_DURATION_TIMER = 5000;
    // Variable for set the current value
    private int CURRENT_TIP;
    // Variable for set the preview value
    private int PREVIEW_TIP;
    // Variable for realize the move of scroll
    private boolean alternate;

    // SECTION 1 (TIP 2)
    private LinearLayout categories;
    private HorizontalScrollView scroll;
    private Button bAll;

    // SECTION 2 (TIP 3)
    private LinearLayout feed;
    private CardView info;
    private TextView description;
    private ImageView image;

    private TextView helpTip_1, helpTip_2, helpTip_3;
    private Button back, next;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demo);

        CURRENT_TIP = TIP_1;
        PREVIEW_TIP = 0;
        alternate = true;

        // SECTION 1
        helpTip_1 = findViewById(R.id.helpText1);

        // SECTION 2
        categories = findViewById(R.id.categories);
        scroll = findViewById(R.id.scroll);
        bAll = findViewById(R.id.bAll);
        helpTip_2 = findViewById(R.id.helpText2);

        // SECTION 3
        feed = findViewById(R.id.feed);
        info = findViewById(R.id.card_info);
        description = findViewById(R.id.description);
        image = findViewById(R.id.image);
        helpTip_3 = findViewById(R.id.helpText3);

        back = findViewById(R.id.button_back);
        next = findViewById(R.id.button_next);

        back.setOnClickListener(this);
        next.setOnClickListener(this);

        // You should not see the return button in the first tip
        showButtonBack(true);
        // Configure the section 3
        configureSection3(false);
        // Start our CountDownTimer. The counter will help us to automatically show the next tip
        nextTip.start();
    }

    @Override
    public void onClick(View v) {
        // Stop CountDownTimer
        nextTip.cancel();
        PREVIEW_TIP = CURRENT_TIP;

        switch (v.getId()) {
            case R.id.button_next:
                CURRENT_TIP++;
                if (CURRENT_TIP == TIP_2) showButtonBack(false);

                if (CURRENT_TIP == TIP_3) next.setText(getString(R.string.button_start));
                if (CURRENT_TIP > TIP_3) initMainActivity();
                else showTip();
                break;

            case R.id.button_back:
                if (CURRENT_TIP == TIP_3) next.setText(getString(R.string.button_next));

                CURRENT_TIP--;
                if (CURRENT_TIP == TIP_1) showButtonBack(true);
                showTip();
                break;

            default:
                break;
        }
    }

    private CountDownTimer nextTip = new CountDownTimer(DEFAULT_DURATION_TIMER, 1000) {
        @Override
        public void onTick(long millisUntilFinished) {
            // What will you do while the accountant is working?
            switch (CURRENT_TIP){
                case TIP_1:
                    break;

                case TIP_2:
                    // When the tip of the menu to filter is visible, we will move the scroll
                    scroll.scrollTo(alternate ? 500 : -500, 0);
                    alternate = !alternate;
                    break;
            }
        }

        @Override
        public void onFinish() {
            // We must update our variables to show the corresponding tip
            PREVIEW_TIP = CURRENT_TIP;
            CURRENT_TIP += 1;
            showTip();
        }
    };

    private void showButtonBack(boolean isInit){
        back.setVisibility(isInit ? View.GONE : View.VISIBLE);
    }

    private void showTip(){
        // What tip show?
        switch (CURRENT_TIP){
            case TIP_1:
                configureSection1(true);
                configureSection2(false);
                nextTip.start();
                break;

            case TIP_2:
                showButtonBack(false);
                if (PREVIEW_TIP == TIP_1)
                    configureSection1(false);
                else if (PREVIEW_TIP == TIP_3)
                    configureSection3(false);

                PREVIEW_TIP = TIP_2;
                configureSection2(true);
                nextTip.start();

                break;

            case TIP_3:
                next.setText(getString(R.string.button_start));
                if (PREVIEW_TIP == TIP_2) configureSection2(false);
                configureSection3(true);
                break;
        }
    }

    private void configureSection1(boolean isInitialize){
        helpTip_1.setVisibility(isInitialize ? View.VISIBLE : View.GONE);
    }

    private void configureSection2(boolean isInitialize){
        helpTip_2.setVisibility(isInitialize ? View.VISIBLE : View.GONE);
        if (isInitialize){
            categories.setBackgroundColor(getResources().getColor(R.color.colorTitleToolbar));
            bAll.setTextColor(getResources().getColor(R.color.colorPrimary));
        }
        else {
            categories.setBackgroundColor(getResources().getColor(R.color.colorTransparent));
            bAll.setTextColor(getResources().getColor(R.color.colorPrimary30));
        }
    }

    private void configureSection3(boolean isInitialize){
        helpTip_3.setVisibility(isInitialize ? View.VISIBLE : View.GONE);
        if (isInitialize){
            feed.setBackgroundColor(getResources().getColor(R.color.colorTitleToolbar));
            info.setBackgroundColor(getResources().getColor(R.color.colorTitleToolbar));
            description.setAlpha(1F);
            image.setAlpha(1F);
        }
        else {
            feed.setBackgroundColor(getResources().getColor(R.color.colorTransparent));
            info.setBackgroundColor(getResources().getColor(R.color.colorTransparent));
            description.setAlpha(0.3F);
            image.setAlpha(0.1F);
        }
    }

    private void initMainActivity(){
        startActivity(new Intent(this, MainActivity.class));
        finish();
    }
}
