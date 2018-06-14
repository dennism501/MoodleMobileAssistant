package com.realator.dennism501.moodlemobileproject.Fragments;


import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.realator.dennism501.moodlemobileproject.Activites.CourseContent;
import com.realator.dennism501.moodlemobileproject.POJO.Course;
import com.realator.dennism501.moodlemobileproject.POJO.SiteInfo;
import com.realator.dennism501.moodlemobileproject.R;
import com.realator.dennism501.moodlemobileproject.RecyclerAdaptors.SiteHomeAdaptor;
import com.realator.dennism501.moodlemobileproject.model.RestFetcher;
import com.realator.dennism501.moodlemobileproject.modelRest.MoodleServices;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class SiteHomeFragment extends Fragment {

    SiteHomeAdaptor.onItemClickListener OnItemClickListener = new SiteHomeAdaptor.onItemClickListener() {

        @Override
        public void onItemClick(View v, int position) {

            Intent intent = new Intent(getContext(), CourseContent.class);
            intent.putExtra(CourseContent.course_shortname,courseList.get(position).getShortName());
            intent.putExtra(CourseContent.course_id,courseList.get(position).getId());
            getActivity().startActivity(intent);

        }
    };
    private List<Course> courseList;
    private RecyclerView recyclerView;
    private SiteHomeAdaptor siteHomeAdaptor;
    private SiteInfo siteInfo = new SiteInfo();
    private ProgressBar progressBar;
    private String token = "355b6a7b497b2bb9aaa80206f21c078a";
    private String id = "4";
    private String tok, serv, id1, form;
    private RestFetcher restFetcher = new RestFetcher();
    private String url;
    private View view;



    public SiteHomeFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_site_home, container, false);
        recyclerView = (RecyclerView) view.findViewById(R.id.homerecyclerview);
        progressBar = (ProgressBar) view.findViewById(R.id.progressbar);


        JSONObject object = new JSONObject();

        try {

            object.put("token", token);
            object.put("service", MoodleServices.COURSE_SERVICE);
            object.put("format", MoodleServices.FORMAT);
            object.put("id", id);

            if (object.length() > 0) {

                tok = String.valueOf(object.getString("token"));
                serv = String.valueOf(object.getString("service"));
                form = String.valueOf(object.getString("format"));
                id1 = String.valueOf(object.getString("id"));

            }


        } catch (JSONException e) {
            e.printStackTrace();
        }

        url = "http://"+siteInfo.getUrl()
                +"/moodle/webservice/rest/server.php?wstoken="
                + tok + "&wsfunction="
                + serv + "&moodlewsrestformat="
                + form + "&userid=" + id1;

        new CourseTask().execute(url);


        return view;
    }

    public void setAdaptor() {
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(view.getContext());
        siteHomeAdaptor = new SiteHomeAdaptor(view.getContext(), courseList);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        siteHomeAdaptor.setOnItemClickListener(OnItemClickListener);
        recyclerView.setAdapter(siteHomeAdaptor);
        siteHomeAdaptor.notifyDataSetChanged();


    }

    private void parseResult(String result) {


        try {
            courseList = new ArrayList<>();
            JSONArray jsonArray = new JSONArray(result);

            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject post = jsonArray.getJSONObject(i);
                Course item = new Course();
                item.setFullName(post.optString("fullname"));
                item.setShortName(post.optString("shortname"));
                item.setSummary(post.optString("summary"));
                item.setId(post.optString("id"));

                courseList.add(item);
            }


        } catch (JSONException e) {
            e.printStackTrace();
        }


    }

    public class CourseTask extends AsyncTask<String, Void, Integer> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressBar.setVisibility(View.VISIBLE);
        }

        @Override
        protected Integer doInBackground(String... params) {

            Integer result = 0;
            String checker;

            checker = restFetcher.getData(url);

            try {


                if (checker.contains("id")) {

                    parseResult(checker);

                    result = 1;
                    Log.d("Fragment string", checker);
                } else {

                    result = 0;
                }
            } catch (Exception e) {

                e.getLocalizedMessage();
            }

            return result;


        }

        @Override
        protected void onPostExecute(Integer result) {
            super.onPostExecute(result);

            progressBar.setVisibility(View.GONE);

            if (result == 1) {
                setAdaptor();

            }

            if (result == 0) {


            }

        }


    }

}
