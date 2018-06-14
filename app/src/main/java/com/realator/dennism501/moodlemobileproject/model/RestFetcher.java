package com.realator.dennism501.moodlemobileproject.model;

import android.util.Log;

import com.realator.dennism501.moodlemobileproject.POJO.Course;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by dennism501 on 3/30/17.
 */

public class RestFetcher {



    public String getData(String service) {

        String jsonResult = "";
        HttpURLConnection httpURLConnection;


        try {

            URL url = new URL(service);
            httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setDoInput(true);
            httpURLConnection.setDoOutput(true);
            httpURLConnection.setRequestMethod("POST");

            //the writer is passing wrong parameters
            OutputStreamWriter writer = new OutputStreamWriter(httpURLConnection.getOutputStream());
            writer.write("");
            writer.flush();
            writer.close();


            BufferedReader reader = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream()));

            String line;
            StringBuilder stringBuilder = new StringBuilder();

            while ((line = reader.readLine()) != null) {
                stringBuilder.append(line);

                jsonResult = stringBuilder.toString();
                Log.d("String builder", jsonResult);
            }

            reader.close();



        } catch (Exception e) {
            e.printStackTrace();
        }

        return jsonResult;

    }

}
