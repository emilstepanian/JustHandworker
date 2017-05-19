package com.example.emilstepanian.justhandworker.controller.jobowner;

import android.app.ProgressDialog;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
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
import com.example.emilstepanian.justhandworker.controller.JSONParser;
import com.example.emilstepanian.justhandworker.model.Category;
import com.example.emilstepanian.justhandworker.model.Job;
import com.example.emilstepanian.justhandworker.model.Specification;
import com.example.emilstepanian.justhandworker.model.SpecificationValue;

import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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

        ActionBar actionBar = getSupportActionBar();

        actionBar.setDisplayHomeAsUpEnabled(true);


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
        final ProgressDialog progressDialog = new ProgressDialog(CreateJobActivity.this);

        progressDialog.setIndeterminate(true);
        progressDialog.setMessage("Opretter opgave...");
        progressDialog.show();


        new android.os.Handler().postDelayed(
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


                            newJob.put("date", sdf.format(resultdate).toString());
                            newJob.put("userId", currentUserInfo.getInt("id"));

                            for (Category category : categoryList) {
                                if (category.getTitle().equals(spinner.getSelectedItem().toString())) {
                                    newJob.put("categoryId", category.getId());
                                }
                            }
                            newJob.put("mainImageResourceId", "2");




                            List<SpecificationValue> specificationValueList = JSONParser.getListData(getApplicationContext(), "requiredInfoValue");
                            int requiredInfoValueId = 0;
                            for (SpecificationValue specificationValue : specificationValueList){
                                if(requiredInfoValueId <= specificationValue.getId()){
                                    requiredInfoValueId = specificationValue.getId();
                                    requiredInfoValueId++;
                                }
                            }

                            List<Specification> specificationList = JSONParser.getListData(getApplicationContext(), "requiredInfo");


                            int count = requirementsLayout.getChildCount();
                            View v = null;
                            for(int i=0; i<count; i++) {
                                v = requirementsLayout.getChildAt(i);
                                LinearLayout child = (LinearLayout) v;

                                JSONObject riValue = new JSONObject();
                                riValue.put("id", requiredInfoValueId++);

                                for(Specification specification : specificationList) {
                                    if(specification.getTitle().equals(((TextView) child.getChildAt(0)).getText().toString())){
                                        riValue.put("requiredInfoId", specification.getId());

                                    }
                                }
                                riValue.put("jobId", jobId);
                                riValue.put("value", ((EditText) child.getChildAt(1)).getText().toString());

                                JSONParser.insertIntoDatabase(getApplicationContext(), riValue, "requiredInfoValue");

                            }





                            JSONParser.insertIntoDatabase(getApplicationContext(), newJob, "job");

                       //     progressDialog.dismiss();
                            Toast.makeText(getApplicationContext(), "Opgave oprettet", Toast.LENGTH_LONG).show();

                            progressDialog.dismiss();
                            finish();

                        } catch (Exception e) {

                        }


                    }
                }, 2000);



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
        List<Specification> specificationList = JSONParser.getListData(getApplicationContext(), "requiredInfo");
        List<Specification> specificationForCategory = new ArrayList<>();
        LayoutInflater inflater = LayoutInflater.from(getApplicationContext());

        requirementsLayout.removeAllViews();


        for (Category category : categoryList) {

            if (category.getTitle().equals(selectedCategoryTitle)) {
                selectedCategory = category;
            }

        }


        for (Specification specification : specificationList) {

            if (specification.getCategoryId() == selectedCategory.getId()) {

                specificationForCategory.add(specification);

                View requiredInfoView = inflater.inflate(R.layout.specification, requirementsLayout, false);

                TextView riTitle = (TextView) requiredInfoView.findViewById(R.id.specification_field);

                TextView riValue = (TextView) requiredInfoView.findViewById(R.id.specificationValue_field);

                riValue.setText("");
                riValue.setHint("Indtast " + specification.getTitle());


                riTitle.setText(specification.getTitle());

                requirementsLayout.addView(requiredInfoView);

            }

        }


    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:

                finish();
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
