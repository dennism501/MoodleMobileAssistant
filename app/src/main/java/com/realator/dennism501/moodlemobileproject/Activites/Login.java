package com.realator.dennism501.moodlemobileproject.Activites;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;

import java.io.UnsupportedEncodingException;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


import com.realator.dennism501.moodlemobileproject.POJO.SiteInfo;
import com.realator.dennism501.moodlemobileproject.R;
import com.realator.dennism501.moodlemobileproject.helpers.TokenRequest;
import com.realator.dennism501.moodlemobileproject.modelRest.MoodleServices;

import org.json.JSONException;
import org.json.JSONObject;


public class Login extends AppCompatActivity implements View.OnClickListener {

    private String username = "twaambo";
    private SiteInfo siteInfo = new SiteInfo();
    private String password = "Theone843663!";
    private String siteUrl = siteInfo.getUrl();
    private String token = null;
    String usr = null;
    String pass = null;
    String siteIP = null;
    private EditText txtUsername, txtPassword, txtSiteip;
    private Button btnLogin;
    private TextView textView;
    TokenRequest tokenRequest = new TokenRequest();
    String url;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        txtUsername = (EditText) findViewById(R.id.txtloginusername);
        txtPassword = (EditText) findViewById(R.id.txtloginpassword);
        txtSiteip = (EditText) findViewById(R.id.site_ip);
        btnLogin = (Button) findViewById(R.id.btnlogin);
        txtPassword.setTransformationMethod(new PasswordTransformationMethod());
        txtSiteip.setVisibility(View.GONE);
        btnLogin.setOnClickListener(this);
        txtSiteip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                txtSiteip.setVisibility(View.VISIBLE);

            }
        });

    }

    //when
    @Override
    public void onClick(View v) {

        switch (v.getId()) {

            case R.id.btnlogin:
                try {

                    if (CheckNetwork()) {
                        Gettext();


                    } else {
                        Toast.makeText(this, "Please check internet connection", Toast.LENGTH_LONG).show();
                    }


                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
                break;


        }

    }

    /*checks if the network is working*/
    public boolean CheckNetwork() {

        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();

        if (networkInfo != null && networkInfo.isConnected()) {

            return true;
        } else {

            return false;


        }

    }

    /*gets the text that will be posted to the URL*/
    public void Gettext() throws UnsupportedEncodingException {

        username = txtUsername.getText().toString();
        password = txtPassword.getText().toString();

        JSONObject params = new JSONObject();

        try {

            params.put("username", username);
            params.put("password", password);
            params.put("url", siteUrl);

            if (params.length() > 0) {

                usr = String.valueOf(params.getString("username"));
                pass = String.valueOf(params.getString("password"));
                siteIP = String.valueOf(params.getString("url"));
            }

        } catch (JSONException e) {

            e.printStackTrace();
        }


        url = "http://" + siteIP + "/moodle/login/token.php?username=" + usr + "&password=" + pass + "&service=" + MoodleServices.SERVICE_MOODLE_SEVICE;
        new LoginTask().execute(url);

    }


    public class LoginTask extends AsyncTask<String, Void, Integer> {

        ProgressDialog progressDialog = new ProgressDialog(Login.this);


        Integer result = 0;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressDialog.setIndeterminate(true);
            progressDialog.setMessage("Please wait...");
            progressDialog.show();

        }

        @Override
        protected Integer doInBackground(String... params) {

            boolean ispresent = false;
            boolean iswrong = false;
            token = tokenRequest.getToken(url);
            ispresent = token.contains("token");
            iswrong = token.contains("error");

            try {

                if (ispresent) {

                    Log.d("Token received", token);
                    result = 1;
                }
                if (iswrong) {

                    result = 0;
                    Log.d("Login failed", token);
                }

            } catch (Exception e) {

                Log.d("error:", e.getLocalizedMessage());
            }
            return result;
        }

        @Override
        protected void onPostExecute(Integer result) {
            super.onPostExecute(result);
            progressDialog.hide();

            if (result == 1) {

                //textView.setText(token);
                Intent intent = new Intent(Login.this, MainActivity.class);
                startActivity(intent);

            }
            if (result == 0) {
                Toast.makeText(Login.this, "Please enter correct credentials ", Toast.LENGTH_LONG).show();
                //textView.setText(token);

            }
        }
    }


}
