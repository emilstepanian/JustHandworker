package com.example.emilstepanian.justhandworker.shared.controller;

import android.content.Context;

import com.example.emilstepanian.justhandworker.R;
import com.example.emilstepanian.justhandworker.shared.model.Image;
import com.example.emilstepanian.justhandworker.shared.model.Job;
import com.example.emilstepanian.justhandworker.shared.model.User;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by emilstepanian on 05/05/2017.
 */

public class JSONParser {
    static InputStream inputStream = null;


    public static User authorizeUser(Context context, String username, String password) {

        User authorizedUser = null;
        try {
            inputStream = context.getResources().openRawResource(R.raw.database);

            JSONArray users = getJSONArray(inputStream, "user");


            for (int i = 0; i < users.length(); i++) {

                JSONObject JSONUser = users.getJSONObject(i);

                if (JSONUser.getString("username").equals(username) && JSONUser.getString("password").equals(password)) {
                    authorizedUser = new User();

                    authorizedUser.setFirstName(JSONUser.getString("firstName"));
                    authorizedUser.setLastName(JSONUser.getString("lastName"));
                    authorizedUser.setUsername(JSONUser.getString("username"));
                    authorizedUser.setPassword(JSONUser.getString("password"));
                    authorizedUser.setId(JSONUser.getInt("id"));
                    authorizedUser.setProfessionId(JSONUser.getInt("professionId"));

                }
            }


        } catch (Exception e) {
            e.printStackTrace();
        } /*finally {
            try {
                if (inputStream != null) inputStream.close();
            } catch (Exception squish) {
            }
        }*/

        return authorizedUser;

    }

    public static <T> List<T> getListData(Context context, String table) {
        inputStream = context.getResources().openRawResource(R.raw.database);
        List data = null;
        try {

            JSONArray jsonArray = getJSONArray(inputStream, table);

            if (table.equals("job")) {
               data  = new ArrayList<>();

                for (int i = 0; i < jsonArray.length(); i++) {

                    JSONObject jsonJob = jsonArray.getJSONObject(i);


                    Job job = new Job();

                    job.setId(jsonJob.getInt("id"));
                    job.setLocation(jsonJob.getString("location"));
                    job.setCategoryId(jsonJob.getInt("categoryId"));
                    job.setDate(jsonJob.getString("date"));
                    job.setDescription(jsonJob.getString("description"));
                    job.setTitle(jsonJob.getString("title"));
                    job.setUserId(jsonJob.getInt("userId"));
                    job.setMainImageResourceId(jsonJob.getInt("mainImageResourceId"));
                    data.add(job);
                }

                return data;
            } else if(table.equals("image")) {
                data = new ArrayList<>();

                for (int i = 0; i < jsonArray.length(); i++) {

                    JSONObject jsonImage = jsonArray.getJSONObject(i);


                    Image image = new Image();

                    image.setId(jsonImage.getInt("id"));
                    image.setImageTitle(jsonImage.getString("imageTitle"));
                    image.setJobId(jsonImage.getInt("id"));

                    data.add(image);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return data;
    }

    public static JSONObject getJSONObjectById(Context context, String table, int id) {
        JSONObject object = null;

        try {

            JSONArray jsonArray = getJSONArray(context.getResources().openRawResource(R.raw.database), table);
            for (int i = 0; i < jsonArray.length(); i++) {
                if (jsonArray.getJSONObject(i).getInt("id") == id) {

                    object = jsonArray.getJSONObject(i);
                    return object;
                }

            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return object;
    }

    public static JSONArray getJSONArray(InputStream inputStream, String table) {
        JSONArray JSONArray = null;
        String result = null;
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"), 8);
            StringBuilder stringBuilder = new StringBuilder();

            String line = null;

            while ((line = reader.readLine()) != null) {
                stringBuilder.append(line + "\n");
            }

            result = stringBuilder.toString();

            JSONObject jsonObject = new JSONObject(result);

            JSONArray = jsonObject.getJSONArray(table);
            return JSONArray;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return JSONArray;

    }
}
