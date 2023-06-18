package com.eriqoariefjsleeprj.frontend.misc;
import android.content.Context;
import android.content.SharedPreferences;

public class AppPreferences {
    private static final String PREF_NAME = "MyAppPreferences";
    private static final String KEY_REFRESH = "refreshToken";

    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;
    private Context context;

    public AppPreferences(Context context) {
        this.context = context;
        sharedPreferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
    }

    public void setRefreshToken(String token) {
        editor.putString(KEY_REFRESH, token);
        editor.apply();
    }

    public String getRefreshToken() {
        return sharedPreferences.getString(KEY_REFRESH, "");
    }

    public void removeRefreshToken() {
        editor.remove(KEY_REFRESH);
        editor.apply();
    }
}
