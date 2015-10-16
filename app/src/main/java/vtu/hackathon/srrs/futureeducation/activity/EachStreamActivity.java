package vtu.hackathon.srrs.futureeducation.activity;

import android.annotation.TargetApi;
import android.app.Dialog;
import android.os.Build;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.marshalchen.ultimaterecyclerview.ObservableScrollState;
import com.marshalchen.ultimaterecyclerview.ObservableScrollViewCallbacks;
import com.marshalchen.ultimaterecyclerview.UltimateRecyclerView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import vtu.hackathon.srrs.futureeducation.AppController;
import vtu.hackathon.srrs.futureeducation.R;
import vtu.hackathon.srrs.futureeducation.Supporting_Files.ProgressDialogUtil;
import vtu.hackathon.srrs.futureeducation.adapter.StreamAdapter;
import vtu.hackathon.srrs.futureeducation.adapter.StreamAdapter.onSendPageListener;
import vtu.hackathon.srrs.futureeducation.dto.StreamDTO;

public class EachStreamActivity extends AppCompatActivity implements onSendPageListener {

    private Dialog dialog = null;
    RelativeLayout generalLayout, courseCostLayout, futureScopeLayout, trendingLayout;
    Toolbar toolbar;


    public static final String URL_BASE = "http://firebreathingkittens.net46.net/readAllJson.php";
    public static final String ERROR_JSON_PARSER = "Error! Please try later.";
    public static final String ERROR_SERVER = "Unable to process your request,please try again later";

    private UltimateRecyclerView recyclerView;
    private StreamAdapter adapter;
    private List<StreamDTO> itemDetailsList = new ArrayList<>();
    StreamDTO details;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_each_stream);

        toolbar = (Toolbar) findViewById(R.id.app_bar);
        setSupportActionBar(toolbar);


        generalLayout = (RelativeLayout) findViewById(R.id.generalInfo);
        courseCostLayout = (RelativeLayout) findViewById(R.id.courseCost);
        futureScopeLayout = (RelativeLayout) findViewById(R.id.futureScope);






        ProgressDialogUtil.showDialog(this, "Friends... a moment away!");
        initRecyclerView();
        optionLayout();
        dialog.hide();
        setLayouts(View.VISIBLE, View.INVISIBLE, View.INVISIBLE);
        populateJson();
    }


    private void initRecyclerView() {   //set recycler view
        recyclerView = (UltimateRecyclerView) findViewById(R.id.listView_products);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(false);
        adapter = new StreamAdapter(this, itemDetailsList, this);
        //set adapter
        recyclerView.setAdapter(adapter);
        UltimateRecyclerView.CustomRelativeWrapper wrapper = new UltimateRecyclerView.CustomRelativeWrapper(this.getApplicationContext());
        //   wrapper.addView(header);
        adapter.setCustomHeaderView(wrapper);
        recyclerView.enableLoadmore();
    }



    public void setLayouts(int general, int cost, int scope)
    {
        generalLayout.setVisibility(general);
        courseCostLayout.setVisibility(cost);
        futureScopeLayout.setVisibility(scope);

    }


    private void populateJson() {


        // Creating volley request obj
        JsonArrayRequest getItemDetailsReq = new JsonArrayRequest(Request.Method.POST,
                URL_BASE , null,
                new Response.Listener<JSONArray>() {
                    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
                    @Override
                    public void onResponse(JSONArray response) {

                        ProgressDialogUtil.hidePDialog();
                        // Parsing json
                        //TO DO make it work for just one JSON result
                        try {
                            JSONArray hitsArray = response;


                            //parse all incoming results of the query
                            for (int i = 0; i < hitsArray.length(); ) {

                                JSONObject sourceObj1 = hitsArray.getJSONObject(i);



                                details = new StreamDTO();

                                //get images
                                if (sourceObj1.has("image")) {
                                    if(!sourceObj1.getString("image").isEmpty())
                                        details.setImage(sourceObj1.getString("image"));
                                    else
                                        details.setImage("http://www.sathishmun.comule.com/wp-content/uploads/2015/08/na.png");
                                }


                                //topic
                                if (sourceObj1.has("topic") ) {
                                    details.setTopic(sourceObj1.getString("topic"));



                                }

                                //heading
                                if (sourceObj1.has("heading")) {
                                    details.setHeading(sourceObj1.getString("heading"));

                                }

                                //content
                                if (sourceObj1.has("content")) {
                                    details.setContent(sourceObj1.getString("content"));

                                }

                                //page
                                if (sourceObj1.has("page")) {
                                    details.setPage(sourceObj1.getString("page"));

                                }

                                    itemDetailsList.add(details);

                                    adapter.notifyDataSetChanged();



                                i = i + 1;

                            } //end for loop

                        } catch (JSONException error) {


                        }

                        // notifying list adapter about data changes
                        // so that it renders the list view with updated data
                        //TODO modify to prevent entire adapter refresh


                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {


                Toast.makeText(getApplicationContext(), "Please check your Internet Connection", Toast.LENGTH_LONG).show();





                ProgressDialogUtil.hidePDialog();

            }
        });
        // Adding request to request queue
        AppController.getInstance().addToRequestQueue(getItemDetailsReq);


    }



    public void options(View v)
    {
        dialog.show();
    }

    public void optionLayout()
    {
        if (dialog == null) {
            dialog = new Dialog(this, R.style.DialogSlideAnim);
        }
        dialog.setContentView(R.layout.options);
        initDialogView();


        WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
        lp.copyFrom(dialog.getWindow().getAttributes());
        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT;

        dialog.show();
        lp.dimAmount = 0.7f;
        dialog.getWindow().setAttributes(lp);
        dialog.getWindow().setGravity(Gravity.BOTTOM);
        dialog.getWindow().addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);

    }


    Button generalInfo, techInfo, courseCost, futureScope;

    private void initDialogView() {

        dialog.setCanceledOnTouchOutside(true);

        generalInfo = (Button) dialog.findViewById(R.id.generalInfo);
        techInfo = (Button) dialog.findViewById(R.id.technicalInfo);
        courseCost = (Button) dialog.findViewById(R.id.courseCost);
        futureScope = (Button) dialog.findViewById(R.id.futureScope);

        generalInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setLayouts(View.VISIBLE, View.INVISIBLE, View.INVISIBLE);

                dialog.hide();
            }
        });

        techInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                dialog.hide();
            }
        });

        courseCost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setLayouts(View.INVISIBLE, View.VISIBLE, View.INVISIBLE);

                dialog.hide();
            }
        });
        futureScope.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setLayouts(View.INVISIBLE, View.INVISIBLE, View.VISIBLE);

                dialog.hide();
            }
        });


    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_each_stream, menu);
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

    @Override
    public void sendPageLink(String pageLink) {
    Toast.makeText(getApplicationContext(),"u hav clicked the link", Toast.LENGTH_SHORT).show();
    }
}
