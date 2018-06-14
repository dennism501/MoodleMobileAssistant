package com.realator.dennism501.moodlemobileproject.helpers;

/**
 * Created by dennism501 on 12/29/16.
 */

import android.util.Log;


import org.json.JSONObject;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

public class TokenRequest {

    public String getToken(String url) {
        String token = "";
        String result = "";
        String holder = "";
        HttpURLConnection urlConnection;

        try {

            URL url1 = new URL(url);
            urlConnection = (HttpURLConnection) url1.openConnection();
            urlConnection.setDoInput(true);
            urlConnection.setDoOutput(true);
            urlConnection.setRequestMethod("POST");


            OutputStreamWriter writer = new OutputStreamWriter(urlConnection.getOutputStream());
            writer.write("");
            writer.flush();
            writer.close();

            BufferedReader reader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));

            String line = null;
            StringBuilder stringBuilder = new StringBuilder();

            while ((line = reader.readLine()) != null) {
                stringBuilder.append(line);

                result = stringBuilder.toString();
                Log.d("String builder", result);
            }



            JSONObject jsonObject = new JSONObject(result);
            JSONObject Jtoken = jsonObject.getJSONObject("token");
            JSONObject Jerror = jsonObject.getJSONObject("error");

            if(Jtoken.has("token")) {
                holder = Jtoken.getString("token");
                token = holder;
            }
            if(Jerror.has("error"))
            {
                holder = Jerror.getString("error");
                token = holder;
            }
            reader.close();

        } catch (Exception e) {

            return new String("Resuts" + e);
        }



     return token;
    }
}
