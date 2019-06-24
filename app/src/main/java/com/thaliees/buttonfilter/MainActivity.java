package com.thaliees.buttonfilter;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import com.thaliees.buttonfilter.Adapters.InformationAdapter;
import com.thaliees.buttonfilter.Adapters.TagAdapter;
import com.thaliees.buttonfilter.Models.InformationModel;
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

        // Initialize our data
        initTags();
        initInfo();
        // Initialize RecyclerView
        listTags = findViewById(R.id.recyclerView_listTags);
        content = findViewById(R.id.recyclerView_content);

        // The RecyclerView.LayoutManager defines how elements are laid out.
        layoutManager = new LinearLayoutManager(this);

        // Define the orientation.
        // For our list of tags, horizontal
        ((LinearLayoutManager) layoutManager).setOrientation(LinearLayoutManager.HORIZONTAL);
        listTags.setLayoutManager(layoutManager);
        // For our list of content, vertical
        layoutManager = new LinearLayoutManager(this);
        content.setLayoutManager(layoutManager);

        // Initialize our Adapter, and passed our data
        tagAdapter = new TagAdapter(tags);
        informationAdapter = new InformationAdapter(informationList);

        // Set our adapter as the adapter for RecyclerView
        listTags.setAdapter(tagAdapter);
        content.setAdapter(informationAdapter);

        // Add OnClickListener to the Adapter of tags
        tagAdapter.setItemListener(buttonSelected);
    }

    private void initTags(){
        // Initialize dataset.
        // (This data would usually come from a local content provider or remote server)
        // For example, we will obtain 4 data (is possible that you use a Model)
        tags = new String[]{ "ALL", "GENERAL", "MOBILE", "WEB" };
    }

    private void initInfo(){
        informationList = new ArrayList<>();

        // Initialize dataset.
        // (This data would usually come from a local content provider or remote server)
        // For example, we will obtain 4 data of the InformationModel type
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
            // Obtain the value
            String tagToSearch = (String) v.getTag();
            // Change color to the other buttons
            tagAdapter.changeColorAll();
            // Change color to the button selected
            v.setBackgroundColor(getResources().getColor(R.color.colorAccent));
            // Is possible that here, you load the new info with the tag indicated
            searchInfoWithTag(tagToSearch);
        }
    };

    private void searchInfoWithTag(final String tag){
        // (This data would usually come from a local content provider or remote server)
        // In our case, we will obtain the data of our list initial
        List<InformationModel> newInfo = new ArrayList<>();
        if (tag.equals("ALL")) newInfo = informationList;
        else {
            for (InformationModel info : informationList) {
                if (info.tag.equals(tag))
                    newInfo.add(info);
            }
        }

        // Update the adapter (and the information)
        informationAdapter = new InformationAdapter(newInfo);
        content.setAdapter(informationAdapter);
    }
}
