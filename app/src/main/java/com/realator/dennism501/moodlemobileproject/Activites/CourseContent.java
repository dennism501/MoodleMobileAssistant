package com.realator.dennism501.moodlemobileproject.Activites;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.design.widget.BottomSheetBehavior;
import android.support.design.widget.BottomSheetDialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.realator.dennism501.moodlemobileproject.Fragments.Grade_fragment;
import com.realator.dennism501.moodlemobileproject.POJO.CourseContentPOJO;
import com.realator.dennism501.moodlemobileproject.POJO.Module;
import com.realator.dennism501.moodlemobileproject.POJO.SiteInfo;
import com.realator.dennism501.moodlemobileproject.POJO.User;
import com.realator.dennism501.moodlemobileproject.R;
import com.realator.dennism501.moodlemobileproject.RecyclerAdaptors.CourseContentAdaptor;
import com.realator.dennism501.moodlemobileproject.model.RestFetcher;
import com.realator.dennism501.moodlemobileproject.modelRest.MoodleServices;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class CourseContent extends AppCompatActivity {

    public static final String course_shortname = "shortname";
    public static final String course_id = "id";
    private List<CourseContentPOJO> content;
    private List<Module> moduleList;
    private CourseContentAdaptor courseContentAdaptor;
    private Toolbar toolbar;
    private String toolbartitle;
    private ProgressBar progressBar;
    private RestFetcher restFetcher = new RestFetcher();
    private String url;
    private RecyclerView recyclerView;
    private String checker;
    private SiteInfo siteInfo = new SiteInfo();
    private User user = new User();
    private BottomSheetBehavior behavior;

    private TextView txtname, txtdescription, txtmodname;
    private ImageView imgIcon;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_content);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        recyclerView = (RecyclerView) findViewById(R.id.course_content_recyclerview);
        toolbar.setNavigationIcon(getResources().getDrawable(R.drawable.ic_action_previous_item));
        txtname = (TextView) findViewById(R.id.course_content_name);
        txtdescription = (TextView)findViewById(R.id.module_description);
        imgIcon = (ImageView)findViewById(R.id.module_image);
        txtmodname = (TextView)findViewById(R.id.mod_name);
        content = new ArrayList<>();
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
                onSupportNavigateUp();
            }
        });
        progressBar = (ProgressBar) findViewById(R.id.progressbar);
        Intent title = getIntent();
        url = "http://" + siteInfo.getUrl()
                + "/moodle/webservice/rest/server.php?wstoken="
                + user.getUserToken() + "&wsfunction="
                + MoodleServices.GET_COURSE_CONTENT + "&moodlewsrestformat="
                + MoodleServices.FORMAT + "&courseid="
                + title.getStringExtra(course_id);

        toolbartitle = title.getStringExtra(course_shortname);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(toolbartitle);
        new CoursecontentTask().execute(url);

        View bottomSheet = findViewById(R.id.bottom_sheet);
        behavior = BottomSheetBehavior.from(bottomSheet);

    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.grade_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {

            case R.id.menu_item:
                BottomSheetDialogFragment bottomSheetDialogFragment = new Grade_fragment();
                bottomSheetDialogFragment.show(getSupportFragmentManager(), bottomSheetDialogFragment.getTag());
                break;


        }
        return super.onOptionsItemSelected(item);
    }

    public void setAdaptor() {

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        courseContentAdaptor = new CourseContentAdaptor(content, this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(courseContentAdaptor);
        courseContentAdaptor.notifyDataSetChanged();


    }

    //create new method to parse the json string and set the adaptor
    public void parseResult(String result) {


        CourseContentPOJO item = new CourseContentPOJO();
        moduleList = new ArrayList<>();

        try {
            JSONArray array = new JSONArray(result);
            Log.d("Array length", String.valueOf(array.length()));

            if(array != null && array.length()>0){
            for (int i = 0; i < array.length(); i++) {
                JSONObject post = array.getJSONObject(i);
                JSONArray modules = post.getJSONArray("modules");


                item.setId(post.optString("id"));
                item.setName(post.optString("name"));
                item.setVisible(post.optString("visible"));
                item.setSummary(post.optString("summary"));
                item.setSection(post.optString("section"));
                item.setHiddenbynumsection(post.optString("hiddenbynumsections"));

                if (modules != null && modules.length() > 0) {
                    for (int j = 0; j < modules.length(); j++) {
                        JSONObject mod = modules.getJSONObject(j);

                        item.setModid(mod.optString("id"));
                        item.setModurl(mod.optString("url"));
                        item.setModname(mod.optString("name"));
                        item.setModvisible(mod.optString("visible"));
                        item.setModicon(mod.getString("modicon"));
                        item.setModModname(mod.optString("modname"));
                        item.setModplural(mod.optString("modplural"));
                        item.setModindent(mod.optString("indent"));
                        item.setModdescription(mod.optString("description"));
                        item.setModinstance(mod.optString("instance"));

                    }
                }

            }

                content.add(item);


                Log.d("id", item.getId());
                Log.d("name", item.getName());
                Log.d("visible", item.getVisible());
                Log.d("summary", item.getSummary());


            }


        } catch (JSONException e) {
            e.printStackTrace();
        }


    }


    public class CoursecontentTask extends AsyncTask<String, Void, Integer> {


        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressBar.setVisibility(View.VISIBLE);
        }

        @Override
        protected Integer doInBackground(String... params) {

            Integer result = 0;
            checker = restFetcher.getData(url);
            parseResult(checker);
            return result;
        }

        @Override
        protected void onPostExecute(Integer integer) {
            super.onPostExecute(integer);
            progressBar.setVisibility(View.GONE);

            if (integer == 0) {


                setAdaptor();

            } else {

            }

        }
    }
}
