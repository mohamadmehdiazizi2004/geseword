package com.example.content;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Base64;

public class SecurePrefs {

    private static final String PREF_NAME = "user_prefs";
    private static final String KEY_USERNAME = "username";
    private static final String KEY_PASSWORD = "password";

    private SharedPreferences prefs;

    public SecurePrefs(Context context) {
        prefs = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
    }

    private String encrypt(String value) {
        return Base64.encodeToString(value.getBytes(), Base64.DEFAULT);
    }

    private String decrypt(String value) {
        return new String(Base64.decode(value, Base64.DEFAULT));
    }

    public void saveUser(String username, String password) {
        prefs.edit()
                .putString(KEY_USERNAME, encrypt(username))
                .putString(KEY_PASSWORD, encrypt(password))
                .apply();
    }

    public String getUsername() {
        String v = prefs.getString(KEY_USERNAME, "");
        return v.isEmpty() ? "" : decrypt(v);
    }

    public String getPassword() {
        String v = prefs.getString(KEY_PASSWORD, "");
        return v.isEmpty() ? "" : decrypt(v);
    }

    public boolean isLoggedIn() {
        return prefs.contains(KEY_USERNAME) && prefs.contains(KEY_PASSWORD);
    }

    public void logout() {
        prefs.edit().clear().apply();
    }
}
