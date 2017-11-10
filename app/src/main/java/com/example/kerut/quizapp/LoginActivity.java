package com.example.kerut.quizapp;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.appevents.AppEventsLogger;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;

import org.json.JSONException;
import org.json.JSONObject;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

import javax.net.ssl.HttpsURLConnection;

public class LoginActivity extends AppCompatActivity {

    private static final String TAG = "LoginActivity";
    private EditText username;
    private EditText password;
    private CheckBox rememberMeCheckBox;
    private CallbackManager callbackManager;
    private boolean accessGranted = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle(R.string.app_name);
        setContentView(R.layout.activity_login);

        LoginManager.getInstance().logOut();

        login();

        printHashKey(this);

        callbackManager = CallbackManager.Factory.create();
        final LoginButton fbloginButton = (LoginButton) findViewById(R.id.login_button);
        fbloginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                if (accessGranted) {
                    GraphRequest request = GraphRequest.newMeRequest(loginResult.getAccessToken(), new GraphRequest.GraphJSONObjectCallback() {

                        @Override
                        public void onCompleted(JSONObject object, GraphResponse response) {

                            try {
                                Log.i("LoginActivity", object.toString());
                                String firstName = object.getString("first_name");
                                String email = object.getString("email");
                                final User user = new User(firstName, "unknown", email);
                                ArrayList<String> list = new ArrayList<String>();
                                list.add(0, firstName);
                                list.add(1, "unknown");
                                loginFBtoDB(list);
                                registerToDB(user);
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                            // Get facebook data from logi
                        }
                    });
                    Bundle parameters = new Bundle();
                    parameters.putString("fields", "id, first_name, last_name, email,gender, birthday, location"); // Parámetros que pedimos a facebook
                    request.setParameters(parameters);
                    request.executeAsync();
                } else {
                    accessGranted = true;
                    LoginManager.getInstance().logInWithReadPermissions(LoginActivity.this, Arrays.asList("public_profile", "email"));
                }
            }

            @Override
            public void onCancel() {
                accessGranted = false;
            }

            @Override
            public void onError(FacebookException error) {
                accessGranted = false;
                Toast.makeText(LoginActivity.this, "Err: " + error.getLocalizedMessage(), Toast.LENGTH_LONG).show();
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, resultCode, data);
    }

    public void printHashKey(Context pContext) {
        try {
            PackageInfo info = getPackageManager().getPackageInfo(getPackageName(), PackageManager.GET_SIGNATURES);
            for (Signature signature : info.signatures) {
                MessageDigest md = MessageDigest.getInstance("SHA");
                md.update(signature.toByteArray());
                String hashKey = new String(Base64.encode(md.digest(), 0));
                Log.i(TAG, "printHashKey() Hash Key: " + hashKey);
            }
        } catch (NoSuchAlgorithmException e) {
            Log.e(TAG, "printHashKey()", e);
        } catch (Exception e) {
            Log.e(TAG, "printHashKey()", e);
        }
    }


    private void login() {
        this.username = (EditText) findViewById(R.id.username);
        this.password = (EditText) findViewById(R.id.password);

        Button submit = (Button) findViewById(R.id.login_submit);

        rememberMeCheckBox = (CheckBox) findViewById(R.id.login_remember_me);
        final User user = new User(getApplicationContext());
        rememberMeCheckBox.setChecked(user.isRemembered());


        if (user.isRemembered()) {
            username.setText(user.getUsernameForLogin(), TextView.BufferType.EDITABLE);
            password.setText(user.getPasswordForLogin(), TextView.BufferType.EDITABLE);
        } else {
            username.setText("", TextView.BufferType.EDITABLE);
            password.setText("", TextView.BufferType.EDITABLE);
        }


        submit.setOnClickListener(new View.OnClickListener() {
            Authentication authentication = new Authentication();

            public void onClick(View focusView) {


                String username2 = username.getText().toString();
                String password2 = password.getText().toString();

                boolean cancel = false;

                if (!authentication.isValidCredentials(username2)) {
                    username.setError(getString(R.string.login_invalid_username));
                    focusView = username;
                    cancel = true;
                }

                if (!authentication.isValidCredentials(password2)) {
                    password.setError(getString(R.string.login_invalid_password));
                    focusView = password;
                    cancel = true;
                }

                if (cancel) {
                    focusView.requestFocus();
                } else {
                    if (rememberMeCheckBox.isChecked()) {
                        user.setUsernameLogin(username2);
                        user.setPasswordForLogin(password2);
                        user.setRemembered(true);
                    } else {
                        user.setUsernameLogin(username2);
                        user.setPasswordForLogin(password2);
                        user.setRemembered(false);
                    }
                    loginToDB(user);
                }
            }
        });

        Button register = (Button) findViewById(R.id.login_register);
        register.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent goToRegistryActivity = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(goToRegistryActivity);
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
                loading = ProgressDialog.show(LoginActivity.this, getString(R.string.Login_please_wait), null, true, true);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);

                if (s.equals(Integer.toString(HttpsURLConnection.HTTP_OK))) {
                    Intent goToSearchActivity = new Intent(LoginActivity.this, QuizActivity.class);
                    startActivity(goToSearchActivity);
                } else {
                    Intent goToSearchActivity = new Intent(LoginActivity.this, QuizActivity.class);
                    startActivity(goToSearchActivity);
                }
                loading.dismiss();
            }

            @Override
            protected String doInBackground(String... params) {
                HashMap<String, String> data = new HashMap<String, String>();
                data.put("action", "registertoquiz");
                data.put("username", params[0]);
                data.put("password", params[1]);
                data.put("email", params[2]);

                String result = database.sendPostRequest(getString(R.string.URL_DATABASE), data);

                return result;
            }
        }

        Registration registration = new Registration();
        registration.execute(userRegistration.getUsernameForRegistration(), userRegistration.getPasswordForRegistration(),
                userRegistration.getEmailForRegistration());

    }

    private void loginToDB(final User user) {
        class Login extends AsyncTask<String, Void, String> {
            ProgressDialog loading;
            DB database = new DB();

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(LoginActivity.this, getString(R.string.Login_please_wait), null, true, true);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                if (s.equals(Integer.toString(HttpsURLConnection.HTTP_ACCEPTED))) {
                    Intent goToSearchActivity = new Intent(LoginActivity.this, QuizActivity.class);
                    startActivity(goToSearchActivity);

                    Toast.makeText(LoginActivity.this, getString(R.string.login_success), Toast.LENGTH_LONG).show();
                } else if (s.equals(Integer.toString(HttpsURLConnection.HTTP_NOT_AUTHORITATIVE))) {
                    Toast.makeText(LoginActivity.this, getString(R.string.login_failure), Toast.LENGTH_LONG).show();
                }

                loading.dismiss();
            }

            @Override
            protected String doInBackground(String... params) {
                HashMap<String, String> data = new HashMap<String, String>();
                data.put("action", "logintoquiz");
                data.put("username", params[0]);
                data.put("password", params[1]);
                String result = database.sendPostRequest(getString(R.string.URL_DATABASE), data);
                return result;
            }
        }
        Login login = new Login();
        login.execute(user.getUsernameForLogin(), user.getPasswordForLogin());
    }

    private void loginFBtoDB(ArrayList<String> list) {
        class Login extends AsyncTask<String, Void, String> {
            ProgressDialog loading;
            DB database = new DB();

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(LoginActivity.this, getString(R.string.Login_please_wait), null, true, true);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                if (s.equals(Integer.toString(HttpsURLConnection.HTTP_ACCEPTED))) {
                    Intent goToSearchActivity = new Intent(LoginActivity.this, QuizActivity.class);
                    startActivity(goToSearchActivity);
                    Toast.makeText(LoginActivity.this, getString(R.string.login_success), Toast.LENGTH_LONG).show();
                } else if (s.equals(Integer.toString(HttpsURLConnection.HTTP_NOT_AUTHORITATIVE))) {
                    Toast.makeText(LoginActivity.this, getString(R.string.login_failure), Toast.LENGTH_LONG).show();
                }

                loading.dismiss();
            }

            @Override
            protected String doInBackground(String... params) {
                HashMap<String, String> data = new HashMap<String, String>();
                data.put("action", "logintoquiz");
                data.put("username", params[0]);
                data.put("password", params[1]);
                String result = database.sendPostRequest(getString(R.string.URL_DATABASE), data);
                return result;
            }
        }
        User user = new User(getApplicationContext());
        user.setUsernameLogin(list.get(0));
        Login login = new Login();
        login.execute(list.get(0), list.get(1));
    }
}
