package com.thaliees.overlaydemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import com.thaliees.overlaydemo.Adapters.InformationAdapter;
import com.thaliees.overlaydemo.Adapters.TagAdapter;
import com.thaliees.overlaydemo.Models.InformationModel;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    protected RecyclerView listTags, content;
    protected RecyclerView.LayoutManager layoutManager;
    protected TagAdapter tagAdapter;
    protected InformationAdapter informationAdapter;
    protected String[] tags;
    protected List<InformationModel> informationList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Project ButtonFilterJava
        initTags();
        initInfo();
        listTags = findViewById(R.id.recyclerView_listTags);
        content = findViewById(R.id.recyclerView_content);

        layoutManager = new LinearLayoutManager(this);

        ((LinearLayoutManager) layoutManager).setOrientation(LinearLayoutManager.HORIZONTAL);
        listTags.setLayoutManager(layoutManager);
        layoutManager = new LinearLayoutManager(this);
        content.setLayoutManager(layoutManager);

        tagAdapter = new TagAdapter(tags);
        informationAdapter = new InformationAdapter(informationList);

        listTags.setAdapter(tagAdapter);
        content.setAdapter(informationAdapter);

        tagAdapter.setItemListener(buttonSelected);
    }

    private void initTags(){
        tags = new String[]{ "ALL", "GENERAL", "MOBILE", "WEB", "DATABASES", "GAMES" };
    }

    private void initInfo(){
        informationList = new ArrayList<>();

        InformationModel information = new InformationModel("Apple: Swift UI", "June 03, 2019", "", "In WWDC19 Apple announces the Swift UI beta: The Interface Builder editor within Xcode makes it simple to design a full user interface without writing any code. Simply drag and drop windows, buttons, text fields, and other objects onto the design canvas to create a functioning user interface.", "MOBILE");
        informationList.add(information);
        information = new InformationModel("What is Flutter", "June 03, 2019", "im_flutter", "Here’s another Google product, but this one is applied to developing mobile Android and iOS applications, as well as creating apps for Google Fuchsia OS.\nIFlutter is absolutely JS-free as it’s written in Dart, a programming language also created by Google for developing server-side and web applications for both desktop and mobile platforms. This lets Flutter interact with the platform without passing through the JavaBridge which, in turn, allows you to work way faster than otherwise.", "MOBILE");
        informationList.add(information);
        information = new InformationModel("Vue.js", "June 03, 2019", "im_vue_js", "Vue.js is one of the newer frameworks for web development which is growing in popularity very quickly. Its greatest advantage is, if you already have a product, you use Vue.js on its part and everything will function just fine. No lags, no troubles.", "WEB");
        informationList.add(information);
        information = new InformationModel("Git 2.0", "June 10, 2019", "", "One of the most important changes of this release is that it has compatibility problems with previous versions or backwards. This means that there are differences in performance with respect to versions 1.x. On the other hand, the push.default is not defined ; its implicit value changes the behavior matching to simple , which means that when doing a git push without specifying a branch, only the current one will be updated (the same one that would use git pull to bring the code). However Git allows you to define in your global configuration file which one you want to use by default.", "GENERAL");
        informationList.add(information);
    }

    private View.OnClickListener buttonSelected = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            String tagToSearch = (String) v.getTag();
            tagAdapter.setCurrentPosition(v.getId());
            tagAdapter.changeColorAll();
            Button select = (Button) v;
            select.setTextColor(getResources().getColor(R.color.colorAccent));
            searchInfoWithTag(tagToSearch);
        }
    };

    private void searchInfoWithTag(final String tag){
        List<InformationModel> newInfo = new ArrayList<>();
        if (tag.equals("ALL")) newInfo = informationList;
        else {
            for (InformationModel info : informationList) {
                if (info.tag.equals(tag))
                    newInfo.add(info);
            }
        }

        informationAdapter = new InformationAdapter(newInfo);
        content.setAdapter(informationAdapter);
    }
}
