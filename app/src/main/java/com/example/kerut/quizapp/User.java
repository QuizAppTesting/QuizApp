package com.example.kerut.quizapp;

import android.content.Context;
import android.content.SharedPreferences;


/**
 * Created by Vilius Kerutis on 30/09/2018.
 */

public class User {
    private String usernameLogin, usernameRegister, password, email, usernameFB, emailFB, passwordFB;
    private static final String PREFERENCES_FILE_NAME = "com.example.kerut.helloworld";
    private static final String USERNAME_KEY = "username";
    private static final String PASSWORD_KEY = "password";
    private static final String REMEMBER_ME_KEY = "rememberMe";
    private SharedPreferences sharedPreferences;

    //Naudojama registruojant naują vartotoją
    User(String usernameRegister, String password, String email) {
        this.usernameRegister = usernameRegister;
        this.password = password;
        this.email = email;
    }

    //Naudojamas prisijungimo lange
    User(Context context) {
        this.sharedPreferences = context.getSharedPreferences(User.PREFERENCES_FILE_NAME, Context.MODE_PRIVATE);
    }

    String getUsernameForLogin() {
        return this.sharedPreferences.getString(USERNAME_KEY, "");
    }

    void setUsernameLogin(String usernameLogin) {
        this.sharedPreferences.edit().putString(USERNAME_KEY, usernameLogin).apply();
    }

    String getUsernameForRegistration() {
        return this.usernameRegister;
    }

    public void setUsername(String usernameRegister) {
        this.sharedPreferences.edit().putString(USERNAME_KEY, usernameRegister).apply();
    }

    String getPasswordForRegistration() {
        return this.password;
    }

    String getPasswordForLogin() {
        return this.sharedPreferences.getString(PASSWORD_KEY, "");
    }

    void setPasswordForLogin(String password) {
        this.sharedPreferences.edit().putString(PASSWORD_KEY, password).apply();
    }

    String getEmailForRegistration() {
        return this.email;
    }

    boolean isRemembered() {
        return this.sharedPreferences.getBoolean(REMEMBER_ME_KEY, false);
    }

    void setRemembered(boolean remembered) {
        this.sharedPreferences.edit().putBoolean(REMEMBER_ME_KEY, remembered).apply();
    }

    void setUsernameFB(String usernameFB) {
        this.usernameFB = usernameFB;
    }

    void setEmailFB(String emailFB) {
        this.emailFB = emailFB;
    }

    void setPasswordFB(String passwordFB) {
        this.passwordFB = passwordFB;
    }

    String getEmailFB() {
        return this.emailFB;
    }

    String getUsernameFB() {
        return this.usernameFB;
    }

    String getPasswordFB() {
        return this.passwordFB;
    }
}
