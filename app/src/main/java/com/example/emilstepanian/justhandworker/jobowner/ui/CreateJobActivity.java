package com.example.emilstepanian.justhandworker.jobowner.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.emilstepanian.justhandworker.R;
import com.example.emilstepanian.justhandworker.shared.controller.JSONParser;
import com.example.emilstepanian.justhandworker.shared.model.Category;
import com.example.emilstepanian.justhandworker.shared.model.Job;
import com.example.emilstepanian.justhandworker.shared.model.RequiredInfo;
import com.example.emilstepanian.justhandworker.shared.model.RequiredInfoValue;
import com.example.emilstepanian.justhandworker.shared.model.User;

import org.json.JSONObject;

import java.lang.reflect.Array;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class CreateJobActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private EditText title, description, location;
    private Spinner spinner;
    private LinearLayout requirementsLayout;
    private List<Category> categoryList;
    private List<String> spinnerArray;
    private Button createJobButton;
    private Bundle currentUserInfo;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_job);

        currentUserInfo = getIntent().getExtras();


        title = (EditText) findViewById(R.id.create_job_title);
        spinner = (Spinner) findViewById(R.id.create_job_spinner);

        description = (EditText) findViewById(R.id.create_job_description);
        location = (EditText) findViewById(R.id.create_job_location);

        requirementsLayout = (LinearLayout) findViewById(R.id.create_job_requirements_layout);

        createJobButton = (Button) findViewById(R.id.create_job_button);

        createJobButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createJob();
            }
        });


        fillSpinner();


    }

    private void createJob() {

        new android.os.Handler().post(
                new Runnable() {
                    @Override
                    public void run() {
                        JSONObject newJob = new JSONObject();

                        try {

                            List<Job> jobList = JSONParser.getListData(getApplicationContext(), "job");
                            int jobId = 0;
                            for (Job job : jobList){
                                if(jobId <= job.getId()){
                                    jobId = job.getId();
                                    jobId++;
                                }
                            }

                            newJob.put("id", jobId);
                            newJob.put("title", title.getText().toString());
                            newJob.put("description", description.getText().toString());
                            newJob.put("location", location.getText().toString());


                            long yourmilliseconds = System.currentTimeMillis();
                            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yy");
                            Date resultdate = new Date(yourmilliseconds);
                            System.out.println(sdf.format(resultdate));


                            newJob.put("date", sdf.format(resultdate).toString());
                            newJob.put("userId", currentUserInfo.getInt("id"));

                            for (Category category : categoryList) {
                                if (category.getTitle().equals(spinner.getSelectedItem().toString())) {
                                    newJob.put("categoryId", category.getId());
                                }
                            }
                            newJob.put("mainImageResourceId", "0");




                            List<RequiredInfoValue> requiredInfoValueList = JSONParser.getListData(getApplicationContext(), "requiredInfoValue");
                            int requiredInfoValueId = 0;
                            for (RequiredInfoValue requiredInfoValue : requiredInfoValueList){
                                if(requiredInfoValueId <= requiredInfoValue.getId()){
                                    requiredInfoValueId = requiredInfoValue.getId();
                                    requiredInfoValueId++;
                                }
                            }

                            List<RequiredInfo> requiredInfoList = JSONParser.getListData(getApplicationContext(), "requiredInfo");


                            int count = requirementsLayout.getChildCount();
                            View v = null;
                            for(int i=0; i<count; i++) {
                                v = requirementsLayout.getChildAt(i);
                                LinearLayout child = (LinearLayout) v;

                                JSONObject riValue = new JSONObject();
                                riValue.put("id", requiredInfoValueId++);

                                for(RequiredInfo requiredInfo : requiredInfoList) {
                                    if(requiredInfo.getTitle().equals(((TextView) child.getChildAt(0)).getText().toString())){
                                        riValue.put("requiredInfoId", requiredInfo.getId());

                                    }
                                }
                                riValue.put("jobId", jobId);
                                riValue.put("value", ((EditText) child.getChildAt(1)).getText().toString());

                                JSONParser.updateDatabase(getApplicationContext(), riValue, "requiredInfoValue");

                            }





                            JSONParser.updateDatabase(getApplicationContext(), newJob, "job");


                        } catch (Exception e) {

                        }


                    }
                });
        Toast.makeText(getApplicationContext(), "Opgave oprettet", Toast.LENGTH_LONG).show();

        finish();


    }


    private void fillSpinner() {
        categoryList = JSONParser.getListData(getApplicationContext(), "category");
        spinnerArray = new ArrayList<>();


        for (Category category : categoryList) {

            spinnerArray.add(category.getTitle());
        }

        String[] categoryStringArray = new String[spinnerArray.size()];
        categoryStringArray = spinnerArray.toArray(categoryStringArray);


        ArrayAdapter spinnerArrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, categoryStringArray);

        spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner.setAdapter(spinnerArrayAdapter);


        spinner.setOnItemSelectedListener(this);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {


        String selectedCategoryTitle = (String) parent.getItemAtPosition(position);
        Category selectedCategory = null;
        List<RequiredInfo> requiredInfoList = JSONParser.getListData(getApplicationContext(), "requiredInfo");
        List<RequiredInfo> requiredInfoForCategory = new ArrayList<>();
        LayoutInflater inflater = LayoutInflater.from(getApplicationContext());

        requirementsLayout.removeAllViews();


        for (Category category : categoryList) {

            if (category.getTitle().equals(selectedCategoryTitle)) {
                selectedCategory = category;
            }

        }


        for (RequiredInfo requiredInfo : requiredInfoList) {

            if (requiredInfo.getCategoryId() == selectedCategory.getId()) {

                requiredInfoForCategory.add(requiredInfo);

                View requiredInfoView = inflater.inflate(R.layout.requiredinfo, requirementsLayout, false);

                TextView riTitle = (TextView) requiredInfoView.findViewById(R.id.requiredInfo_field);

                TextView riValue = (TextView) requiredInfoView.findViewById(R.id.requiredInfoValue_field);

                riValue.setText("");
                riValue.setHint("Indtast " + requiredInfo.getTitle());


                riTitle.setText(requiredInfo.getTitle());

                requirementsLayout.addView(requiredInfoView);

            }

        }


    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
