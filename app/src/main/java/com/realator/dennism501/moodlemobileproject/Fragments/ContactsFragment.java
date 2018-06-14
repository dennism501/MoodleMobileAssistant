package com.realator.dennism501.moodlemobileproject.Fragments;


import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.realator.dennism501.moodlemobileproject.POJO.Contact;
import com.realator.dennism501.moodlemobileproject.POJO.SiteInfo;
import com.realator.dennism501.moodlemobileproject.POJO.User;
import com.realator.dennism501.moodlemobileproject.R;
import com.realator.dennism501.moodlemobileproject.RecyclerAdaptors.ContactsAdaptor;
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
public class ContactsFragment extends Fragment {

    private RestFetcher restFetcher = new RestFetcher();
    private User user = new User();
    private SiteInfo siteInfo = new SiteInfo();
    private ProgressBar progressBar;
    private ContactsAdaptor contactsAdaptor;
    private RecyclerView recyclerView;
    private TextView txtTester;
    private View view;
    private List<Contact> contactsList;

    private Toolbar toolbar;
    private String checker;
    private String url = "http://"+siteInfo.getUrl()+"/moodle/webservice/rest/server.php?wstoken="+user.getUserToken()+"&wsfunction="+ MoodleServices.GET_CONTATCS+"&moodlewsrestformat="+MoodleServices.FORMAT;


    public ContactsFragment() {
        // Required empty public constructor
    }



    ContactsAdaptor.onItemClickListener OnItemClickListener = new ContactsAdaptor.onItemClickListener() {
        @Override
        public void onItemClick(View v, int position) {

           Fragment fragment = new Fragment();

        }
    };
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_contacts, container, false);
        progressBar = (ProgressBar) view.findViewById(R.id.progressbar);
        toolbar = (Toolbar)view.findViewById(R.id.toolbar);
        recyclerView = (RecyclerView)view.findViewById(R.id.contacts_recyclerview);




        new ContactTask().execute(url);


        return view;
    }

    public void parseResult(String result){


        contactsList = new ArrayList<>();

        try {
            JSONObject response = new JSONObject(result);
            JSONArray online = response.optJSONArray("online");
            JSONArray offline = response.optJSONArray("offline");
            JSONArray strangers = response.getJSONArray("strangers");

            if(offline !=null && offline.length() > 0){

                for(int i = 0; i < offline.length(); i++){

                    JSONObject off = offline.optJSONObject(i);
                    Contact contact = new Contact();
                    contact.setStatus("Offline");
                    contact.setId(off.optString("id"));
                    contact.setFullname(off.optString("fullname"));
                    contact.setProfileimageurl(off.optString("profileimageurl"));
                    contact.setGetProfileimageurlsmall(off.optString("profileimageurlsmall"));
                    contact.setUread(off.optString("uread"));

                    contactsList.add(contact);

                }
            }

            if(online !=null && online.length()>0){

                for(int i = 0; i < online.length(); i++){

                    JSONObject on = online.optJSONObject(i);
                    Contact contact = new Contact();
                    contact.setStatus("Online");
                    contact.setId(on.optString("id"));
                    contact.setFullname(on.optString("fullname"));
                    contact.setProfileimageurl(on.optString("profileimageurl"));
                    contact.setGetProfileimageurlsmall(on.optString("profileimageurlsmall"));
                    contact.setUread(on.optString("uread"));

                    contactsList.add(contact);

                }

            }



        } catch (JSONException e) {
            e.printStackTrace();
        }


    }

    private void setAdaptor(){

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(view.getContext());
        contactsAdaptor = new ContactsAdaptor(contactsList,view.getContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        contactsAdaptor.setOnItemClickListener(OnItemClickListener);
        recyclerView.setAdapter(contactsAdaptor);
        contactsAdaptor.notifyDataSetChanged();


    }


    public class ContactTask extends AsyncTask<String, Void, Integer>{

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressBar.setVisibility(View.VISIBLE);
        }

        @Override
        protected Integer doInBackground(String... params) {

            Integer result = 0;
            checker = restFetcher.getData(url);

            if(checker.contains("id")){

                parseResult(checker);

                result = 1;
            }
            if(checker == null){

                result = 0;
            }
            return result;
        }

        @Override
        protected void onPostExecute(Integer integer) {
            super.onPostExecute(integer);
            progressBar.setVisibility(View.GONE);

            if(integer == 1){

                setAdaptor();


            }
        }
    }

}
