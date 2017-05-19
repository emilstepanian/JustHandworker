package com.example.emilstepanian.justhandworker.controller;

import android.content.Context;
import android.util.JsonWriter;

import com.example.emilstepanian.justhandworker.R;
import com.example.emilstepanian.justhandworker.model.Bid;
import com.example.emilstepanian.justhandworker.model.Category;
import com.example.emilstepanian.justhandworker.model.Image;
import com.example.emilstepanian.justhandworker.model.Job;
import com.example.emilstepanian.justhandworker.model.Specification;
import com.example.emilstepanian.justhandworker.model.SpecificationValue;
import com.example.emilstepanian.justhandworker.model.User;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
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
            inputStream = new FileInputStream(getJSONfile(context));//getJSONfile(context);//context.getResources().openRawResource(R.raw.database);

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
        }

        return authorizedUser;

    }

    public static <T> List<T> getListData(Context context, String table) {
        List data = null;
        try {
            inputStream = new FileInputStream(getJSONfile(context));

            JSONArray jsonArray = getJSONArray(inputStream, table);

            switch (table) {

                case "user":
                    data = new ArrayList();
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject jsonUser = jsonArray.getJSONObject(i);
                        User user = new User();

                        user.setId(jsonUser.getInt("id"));
                        user.setFirstName(jsonUser.getString("firstName"));
                        user.setLastName(jsonUser.getString("lastName"));
                        user.setUsername(jsonUser.getString("username"));
                        user.setPassword(jsonUser.getString("password"));
                        user.setProfessionId(jsonUser.getInt("professionId"));

                        data.add(user);
                    }
                    break;


                case "job":
                    data = new ArrayList<>();

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
                        job.setMainImageTitle(getJSONObjectById(context, "image", job.getMainImageResourceId()).getString("imageTitle"));
                        data.add(job);
                    }
                    break;


                case "image":
                    data = new ArrayList<>();

                    for (int i = 0; i < jsonArray.length(); i++) {

                        JSONObject jsonImage = jsonArray.getJSONObject(i);


                        Image image = new Image();

                        image.setId(jsonImage.getInt("id"));
                        image.setImageTitle(jsonImage.getString("imageTitle"));
                        image.setJobId(jsonImage.getInt("jobId"));

                        data.add(image);
                    }
                    break;

                case "requiredInfo":
                    data = new ArrayList<>();

                    for (int i = 0; i < jsonArray.length(); i++) {

                        JSONObject jsonRequiredInfo = jsonArray.getJSONObject(i);


                        Specification specification = new Specification();

                        specification.setId(jsonRequiredInfo.getInt("id"));
                        specification.setCategoryId(jsonRequiredInfo.getInt("categoryId"));
                        specification.setTitle(jsonRequiredInfo.getString("title"));

                        data.add(specification);
                    }
                    break;


                case "requiredInfoValue":
                    data = new ArrayList<>();

                    for (int i = 0; i < jsonArray.length(); i++) {

                        JSONObject jsonRequiredInfoValue = jsonArray.getJSONObject(i);

                        SpecificationValue specificationValue = new SpecificationValue();

                        specificationValue.setId(jsonRequiredInfoValue.getInt("id"));
                        specificationValue.setJobId(jsonRequiredInfoValue.getInt("jobId"));
                        specificationValue.setSpecificationId(jsonRequiredInfoValue.getInt("requiredInfoId"));
                        specificationValue.setValue(jsonRequiredInfoValue.getString("value"));
                        data.add(specificationValue);
                    }
                    break;



                case "bid":
                    data = new ArrayList();

                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject jsonBid = jsonArray.getJSONObject(i);
                        Bid bid = new Bid();
                        bid.setId(jsonBid.getInt("id"));
                        bid.setPrice(jsonBid.getDouble("price"));
                        bid.setUserId(jsonBid.getInt("userId"));
                        bid.setAccepted(jsonBid.getBoolean("isAccepted"));
                        bid.setDate(jsonBid.getString("date"));
                        bid.setJobId(jsonBid.getInt("jobId"));

                        data.add(bid);
                    }
                    break;


                case "category":
                    data = new ArrayList();

                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject jsonCategory = jsonArray.getJSONObject(i);
                        Category category = new Category();
                        category.setTitle(jsonCategory.getString("title"));
                        category.setId(jsonCategory.getInt("id"));
                        category.setProfessionId(jsonCategory.getInt("professionId"));

                        data.add(category);
                    }
                    break;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return data;
    }

    public static JSONObject getJSONObjectById(Context context, String table, int id) {
        JSONObject object = null;

        try {
            FileInputStream fileInputStream = new FileInputStream(getJSONfile(context));


            JSONArray jsonArray = getJSONArray(fileInputStream, table);
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

    public static void copyAssets(Context context) throws IOException {


        InputStream in = null;
        OutputStream out = null;
        try {
            InputStream file = context.getResources().openRawResource(R.raw.database);


            File outFile = new File(context.getExternalFilesDir(null), "database.json");

            out = new FileOutputStream(outFile);

            copyFile(file, out);
        } catch (Exception e) {


        } finally {
            if (in != null) {
                try {
                    in.close();
                } catch (Exception e) {

                }
            }
        }


    }

    public static void copyFile(InputStream in, OutputStream out) throws IOException {
        byte[] buffer = new byte[1024];
        int read;

        while ((read = in.read(buffer)) != -1) {
            out.write(buffer, 0, read);
        }
    }

    public static File getJSONfile(Context context) {
        File jsonFile = null;
        try {

            jsonFile = new File(context.getExternalFilesDir(null).getPath(), "database.json");

            return jsonFile;

        } catch (Exception e) {


        }

        return jsonFile;

    }

    public static void parseDatabase(Context context) throws Exception {

        File databaseFile = getJSONfile(context);
        OutputStream fileOutputStream = new FileOutputStream(databaseFile);

        JsonWriter writer = new JsonWriter(new OutputStreamWriter(fileOutputStream, "UTF-8"));
        writer.setIndent("  ");

        writer.beginObject();


        JSONArray jobArray = getJSONArray(context.getResources().openRawResource(R.raw.database), "job");
        JSONArray userArray = getJSONArray(context.getResources().openRawResource(R.raw.database), "user");
        JSONArray professionArray = getJSONArray(context.getResources().openRawResource(R.raw.database), "profession");
        JSONArray imageArray = getJSONArray(context.getResources().openRawResource(R.raw.database), "image");
        JSONArray categoryArray = getJSONArray(context.getResources().openRawResource(R.raw.database), "category");
        JSONArray requiredInfoValueArray = getJSONArray(context.getResources().openRawResource(R.raw.database), "requiredInfoValue");
        JSONArray requiredInfoArray = getJSONArray(context.getResources().openRawResource(R.raw.database), "requiredInfo");
        JSONArray messageArray = getJSONArray(context.getResources().openRawResource(R.raw.database), "message");
        JSONArray reviewArray = getJSONArray(context.getResources().openRawResource(R.raw.database), "review");
        JSONArray bidArray = getJSONArray(context.getResources().openRawResource(R.raw.database), "bid");


        writeJsonArray(writer, jobArray, "job");
        writeJsonArray(writer, userArray, "user");
        writeJsonArray(writer, professionArray, "profession");
        writeJsonArray(writer, imageArray, "image");
        writeJsonArray(writer, categoryArray, "category");
        writeJsonArray(writer, requiredInfoValueArray, "requiredInfoValue");
        writeJsonArray(writer, requiredInfoArray, "requiredInfo");
        writeJsonArray(writer, messageArray, "message");
        writeJsonArray(writer, reviewArray, "review");
        writeJsonArray(writer, bidArray, "bid");


        writer.endObject();

        writer.close();

    }

    public static Map<String, JSONArray> getDatabase(Context context) {
        Map<String, JSONArray> arrayMap = null;

        try {
            arrayMap = new HashMap<>();
            InputStream inputStream = new FileInputStream(getJSONfile(context));

            arrayMap.put("job", getJSONArray(new FileInputStream(getJSONfile(context)), "job"));
            arrayMap.put("user", getJSONArray(new FileInputStream(getJSONfile(context)), "user"));
            arrayMap.put("profession", getJSONArray(new FileInputStream(getJSONfile(context)), "profession"));
            arrayMap.put("image", getJSONArray(new FileInputStream(getJSONfile(context)), "image"));
            arrayMap.put("category", getJSONArray(new FileInputStream(getJSONfile(context)), "category"));
            arrayMap.put("requiredInfo", getJSONArray(new FileInputStream(getJSONfile(context)), "requiredInfo"));
            arrayMap.put("requiredInfoValue", getJSONArray(new FileInputStream(getJSONfile(context)), "requiredInfoValue"));
            arrayMap.put("message", getJSONArray(new FileInputStream(getJSONfile(context)), "message"));
            arrayMap.put("review", getJSONArray(new FileInputStream(getJSONfile(context)), "review"));
            arrayMap.put("bid", getJSONArray(new FileInputStream(getJSONfile(context)), "bid"));

            return arrayMap;



        } catch(Exception e){



        }




        return arrayMap;
    }

    public static void insertIntoDatabase(Context context, JSONObject newObject, String table){

        try {
            Map<String, JSONArray> arrayMap = getDatabase(context);
            OutputStream fileOutputStream = new FileOutputStream(getJSONfile(context));
            arrayMap.get(table).put(newObject);

            JsonWriter writer = new JsonWriter(new OutputStreamWriter(fileOutputStream, "UTF-8"));
            writer.setIndent("  ");

            writer.beginObject();

            writeJsonArray(writer, arrayMap.get("job"), "job");
            writeJsonArray(writer, arrayMap.get("user"), "user");
            writeJsonArray(writer, arrayMap.get("profession"), "profession");
            writeJsonArray(writer, arrayMap.get("image"), "image");
            writeJsonArray(writer, arrayMap.get("category"), "category");
            writeJsonArray(writer, arrayMap.get("requiredInfoValue"), "requiredInfoValue");
            writeJsonArray(writer, arrayMap.get("requiredInfo"), "requiredInfo");
            writeJsonArray(writer, arrayMap.get("message"), "message");
            writeJsonArray(writer, arrayMap.get("review"), "review");
            writeJsonArray(writer, arrayMap.get("bid"), "bid");

            writer.endObject();

            writer.close();

        } catch (Exception e){

            e.printStackTrace();

        }
    }

    public static void updateBidInDatabase(Context context, JSONObject bidAcceptedUpdate, String table){

        try {
            Map<String, JSONArray> arrayMap = getDatabase(context);
            OutputStream fileOutputStream = new FileOutputStream(getJSONfile(context));

            for(int i = 0; i < arrayMap.get(table).length(); i++){

                if(bidAcceptedUpdate.getInt("id") == arrayMap.get(table).getJSONObject(i).getInt("id")){
                    arrayMap.get(table).getJSONObject(i).put("isAccepted", "true");
                }

            }

            JsonWriter writer = new JsonWriter(new OutputStreamWriter(fileOutputStream, "UTF-8"));
            writer.setIndent("  ");

            writer.beginObject();

            writeJsonArray(writer, arrayMap.get("job"), "job");
            writeJsonArray(writer, arrayMap.get("user"), "user");
            writeJsonArray(writer, arrayMap.get("profession"), "profession");
            writeJsonArray(writer, arrayMap.get("image"), "image");
            writeJsonArray(writer, arrayMap.get("category"), "category");
            writeJsonArray(writer, arrayMap.get("requiredInfoValue"), "requiredInfoValue");
            writeJsonArray(writer, arrayMap.get("requiredInfo"), "requiredInfo");
            writeJsonArray(writer, arrayMap.get("message"), "message");
            writeJsonArray(writer, arrayMap.get("review"), "review");
            writeJsonArray(writer, arrayMap.get("bid"), "bid");

            writer.endObject();

            writer.close();

        } catch (Exception e){

            e.printStackTrace();

        }
    }


    private static void writeJsonArray(JsonWriter writer, JSONArray array, String table) throws Exception {
        writer.name(table).beginArray();
        for (int i = 0; i < array.length(); i++) {

            JSONObject object = array.getJSONObject(i);
            writeJsonObject(writer, object, table);

        }

        writer.endArray();

    }

    private static void writeJsonObject(JsonWriter writer, JSONObject object, String table) throws Exception {
        writer.beginObject();
        switch (table) {
            case "user":
                writer.name("firstName").value(object.getString("firstName"));
                writer.name("lastName").value(object.getString("lastName"));
                writer.name("username").value(object.getString("username"));
                writer.name("password").value(object.getString("password"));
                writer.name("id").value(object.getInt("id"));
                writer.name("professionId").value(object.getInt("professionId"));

                break;
            case "job":

                writer.name("id").value(object.getInt("id"));
                writer.name("location").value(object.getString("location"));
                writer.name("categoryId").value(object.getInt("categoryId"));
                writer.name("date").value(object.getString("date"));
                writer.name("description").value(object.getString("description"));
                writer.name("title").value(object.getString("title"));
                writer.name("userId").value(object.getInt("userId"));
                writer.name("mainImageResourceId").value(object.getInt("mainImageResourceId"));
                break;
            case "profession":
                writer.name("id").value(object.getInt("id"));
                writer.name("title").value(object.getString("title"));
                break;
            case "image":
                writer.name("id").value(object.getInt("id"));
                writer.name("jobId").value(object.getInt("jobId"));
                writer.name("imageTitle").value(object.getString("imageTitle"));
                break;
            case "category":

                writer.name("id").value(object.getInt("id"));
                writer.name("title").value(object.getString("title"));

                writer.name("professionId").value(object.getInt("professionId"));
                break;
            case "requiredInfoValue":
                writer.name("id").value(object.getInt("id"));
                writer.name("requiredInfoId").value(object.getInt("requiredInfoId"));
                writer.name("jobId").value(object.getInt("jobId"));
                writer.name("value").value(object.getString("value"));
                break;
            case "requiredInfo":
                writer.name("id").value(object.getInt("id"));
                writer.name("categoryId").value(object.getInt("categoryId"));
                writer.name("title").value(object.getString("title"));
                break;
            case "message":
                writer.name("id").value(object.getInt("id"));
                writer.name("userIdSender").value(object.getInt("userIdSender"));
                writer.name("userIdReceiver").value(object.getInt("userIdReceiver"));
                writer.name("text").value(object.getString("text"));
                writer.name("date").value(object.getString("date"));
                break;
            case "review":
                writer.name("id").value(object.getInt("id"));
                writer.name("userIdSender").value(object.getInt("userIdSender"));
                writer.name("userIdReceiver").value(object.getInt("userIdReceiver"));
                writer.name("jobId").value(object.getInt("jobId"));
                writer.name("rating").value(object.getString("rating"));
                writer.name("comment").value(object.getString("comment"));
                writer.name("date").value(object.getString("date"));
                break;
            case "bid":
                writer.name("id").value(object.getInt("id"));
                writer.name("jobId").value(object.getInt("jobId"));
                writer.name("userId").value(object.getInt("userId"));
                writer.name("isAccepted").value(object.getString("isAccepted"));
                writer.name("price").value(object.getString("price"));
                writer.name("date").value(object.getString("date"));
                break;
        }
        writer.endObject();

    }


}


