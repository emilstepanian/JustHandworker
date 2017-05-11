package com.example.emilstepanian.justhandworker.shared.ui;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.emilstepanian.justhandworker.R;
import com.example.emilstepanian.justhandworker.jobowner.ui.JobOwnerMainActivity;
import com.example.emilstepanian.justhandworker.shared.controller.JSONParser;
import com.example.emilstepanian.justhandworker.shared.model.User;
import com.example.emilstepanian.justhandworker.jobtaker.ui.JobTakerMainActivity;

import org.json.JSONObject;


public class MainActivity extends AppCompatActivity {

    private Button loginBtn;
    private TextView signupLink;
    private EditText usernameInput, passwordInput;

    //https://sourcey.com/beautiful-android-login-and-signup-screens-with-material-design/
    //Inspiration

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        try {

           // if(!JSONParser.getJSONfile(getApplicationContext()).exists()) {
                JSONParser.copyAssets(getApplicationContext());
                JSONParser.parseDatabase(getApplicationContext());
            //}



        } catch(Exception e){

            e.printStackTrace();

        }

        //Delete these two lines and un-comment code beneath to get login page.
      //  Intent i = new Intent(this, JobTakerMainActivity.class);
       // startActivity(i);


        //

        loginBtn = (Button) findViewById(R.id.btn_login);
        signupLink = (TextView) findViewById(R.id.link_signup);
        usernameInput = (EditText) findViewById(R.id.input_username);
        passwordInput = (EditText) findViewById(R.id.input_password);


        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login();
            }
        });

        signupLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), SignupActivity.class);
                startActivity(i);

            }
        });

        //



    }

    public void login() {
        final ProgressDialog progressDialog = new ProgressDialog(MainActivity.this);
        progressDialog.setIndeterminate(true);
        progressDialog.setMessage("Logger ind...");
        progressDialog.show();

        final String username = usernameInput.getText().toString();
        final String password = passwordInput.getText().toString();


        new android.os.Handler().postDelayed(
                new Runnable() {
                    @Override
                    public void run() {

                        User user = JSONParser.authorizeUser(getApplicationContext(), username, password);

                        if (user != null) {
                            progressDialog.dismiss();
                            onLoginSucess(user);

                        } else {
                            progressDialog.dismiss();
                            Toast.makeText(getApplicationContext(), "Kunne ikke logge ind. \nForsøg igen",
                                    Toast.LENGTH_LONG).show();
                        }

                    }
                }, 2000);

    }

    public void onLoginSucess(User user) {

        Bundle userInfo = new Bundle();
        userInfo.putString("firstName", user.getFirstName());
        userInfo.putString("lastName", user.getLastName());
        userInfo.putString("password", user.getPassword());
        userInfo.putString("username", user.getUsername());
        userInfo.putInt("id", user.getId());
        userInfo.putInt("professionId", user.getProfessionId());


        if (user.getProfessionId() != 0) {

            Intent i = new Intent(this, JobTakerMainActivity.class);

            i.putExtras(userInfo);

            startActivity(i);


        } else {
            Intent i = new Intent(this, JobOwnerMainActivity.class);

            i.putExtras(userInfo);
            startActivity(i);
        }


    }


}
