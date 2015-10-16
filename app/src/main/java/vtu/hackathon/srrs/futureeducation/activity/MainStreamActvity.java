package vtu.hackathon.srrs.futureeducation.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import vtu.hackathon.srrs.futureeducation.AppController;
import vtu.hackathon.srrs.futureeducation.R;

public class MainStreamActvity extends AppCompatActivity {

    private android.support.v7.widget.Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_stream_actvity);

        toolbar = (android.support.v7.widget.Toolbar) findViewById(R.id.app_bar);
        setSupportActionBar(toolbar);
    }

    public void engg(View v) { startSubStreamActivity("engg"); }
    public void medical(View v) { startSubStreamActivity("medical"); }
    public void masscom(View v) { startSubStreamActivity("masscom"); }
    public void sports(View v) { startSubStreamActivity("sports"); }
    public void llb(View v) { startSubStreamActivity("llb"); }
    public void defence(View v) { startSubStreamActivity("defence"); }

    public void startSubStreamActivity(String streamPath)
    {
        AppController.getInstance().setStreamPath(streamPath);
        Intent intent = new Intent(this, SubStreamActivity.class);
        startActivity(intent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main_stream_actvity, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
