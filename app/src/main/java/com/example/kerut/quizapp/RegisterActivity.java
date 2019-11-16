package com.example.kerut.quizapp;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.HashMap;

import javax.net.ssl.HttpsURLConnection;

/**
 * Created by Vilius Kerutis on 30/09/2018.
 */

public class RegisterActivity extends AppCompatActivity {

    private EditText username;
    private EditText email;
    private EditText password1;
    private EditText password2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        this.username = findViewById(R.id.register_name);
        this.password1 = findViewById(R.id.register_password1);
        this.password2 = findViewById(R.id.register_password2);
        this.email = findViewById(R.id.register_email);


        Button register = findViewById(R.id.register);
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View focusView) {

                String username2 = username.getText().toString();
                String email2 = email.getText().toString();
                String passwordFirst = password1.getText().toString();
                String passwordSecond = password2.getText().toString();
                Authentication authentication = new Authentication();

                boolean cancel = false;

                if (!authentication.isValidCredentials(username2)) {
                    username.setError(getString(R.string.login_invalid_username));
                    focusView = username;
                    cancel = true;
                }

                if (!authentication.isValidCredentials(passwordFirst)) {
                    password1.setError(getString(R.string.login_invalid_password));
                    focusView = password1;
                    cancel = true;
                }

                if (!authentication.isValidCredentials(passwordSecond)) {
                    password2.setError(getString(R.string.login_invalid_password));
                    focusView = password2;
                    cancel = true;
                }

                if (!authentication.isValidEmail(email2)) {
                    email.setError(getString(R.string.register_invalid_email));
                    focusView = email;
                    cancel = true;
                }

                if (cancel) {
                    focusView.requestFocus();
                } else {
                    if (passwordFirst.compareTo(passwordSecond) == 0) {
                        User userRegistration = new User(username.getText().toString(), password2.getText().toString(), email.getText().toString());
                        registerToDB(userRegistration);
                    } else {
                        Toast.makeText(RegisterActivity.this, getString(R.string.passwords_not_match), Toast.LENGTH_LONG).show();
                    }
                }
            }
        });

    }

    public void registerToDB(final User userRegistration) {

        class Registration extends AsyncTask<String, Void, String> {
            ProgressDialog loading;
            DB database = new DB();

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(RegisterActivity.this, getString(R.string.Login_please_wait), null, true, true);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);

                if (s.equals(Integer.toString(HttpsURLConnection.HTTP_OK))) {
                    Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                    startActivity(intent);
                    Toast.makeText(RegisterActivity.this, getString(R.string.register_success), Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(RegisterActivity.this, getString(R.string.register_failure), Toast.LENGTH_LONG).show();
                }
                loading.dismiss();
            }

            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
            @Override
            protected String doInBackground(String... params) {
                HashMap<String, String> data = new HashMap<String, String>();
                data.put("username", params[0]);
                data.put("password", params[1]);
                data.put("email", params[2]);

                String result = database.sendPostRequest(getString(R.string.URL_Registration), data);

                return result;
            }
        }

        Registration registration = new Registration();
        registration.execute(userRegistration.getUsernameForRegistration(), userRegistration.getPasswordForRegistration(),
                userRegistration.getEmailForRegistration());
    }
}