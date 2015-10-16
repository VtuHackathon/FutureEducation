package vtu.hackathon.srrs.futureeducation.activity;

import android.app.Activity;
import android.content.Intent;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import vtu.hackathon.srrs.futureeducation.R;
import vtu.hackathon.srrs.futureeducation.Supporting_Files.CirclePageIndicator;
import vtu.hackathon.srrs.futureeducation.Supporting_Files.PageIndicator;
import vtu.hackathon.srrs.futureeducation.adapter.AppTutorialAdapter;


/**
 * Created by Sathish Mun on 04-07-2015.
 */


public class AppTutorialScreen extends Activity implements View.OnClickListener {

    private static final String TAG = AppTutorialScreen.class.getSimpleName();
    private PageIndicator pageIndicator;
    private ViewPager viewPager;
    private Button btnSkipTutorial;
    private TypedArray tutorialScreensList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //setView
        setContentView(R.layout.app_tutorial);

        // initialize variables
        viewPager = (ViewPager) findViewById(R.id.viewPager_app_tutorial);
        pageIndicator = (CirclePageIndicator) findViewById(R.id.indicator);
        btnSkipTutorial = (Button) findViewById(R.id.btn_skip_tutorial);

        initViewPager();

        btnSkipTutorial.setOnClickListener(this);

    }

    private void initViewPager() {    //set View Pager
        tutorialScreensList = getResources().obtainTypedArray(
                R.array.app_tutorial_screens);
        viewPager.setAdapter(new AppTutorialAdapter(this, tutorialScreensList));
        pageIndicator.setViewPager(viewPager);
        pageIndicator.onPageScrolled(viewPager.getCurrentItem(), 0, 0);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_skip_tutorial:
                Toast.makeText(getApplicationContext(), "pressed skip tut", Toast.LENGTH_SHORT).show();
//                Intent b = new Intent(getApplicationContext(), TabCustomizationActivity.class);
//                startActivity(b);
//                finish();
                break;

        }

    }


}