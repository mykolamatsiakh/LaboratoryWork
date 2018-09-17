package iot.nulp.com.laboratorywork.screens.Prefs;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.annotation.Nullable;


/**
 * Created by Roman on 01-Apr-18.
 */

public class UserPref {

    private static final String USER_PREF = "USER_PREF";
    private static final String KEY_USER_NAME = "USER_NAME";
    private static final String KEY_USER_SURNAME = "USER_SURNAME";
    private static final String KEY_USER_EMAIL = "USER_EMAIL";

    private static UserPref sInstance;
    private final SharedPreferences mPrefs;

    public static UserPref get(Context context) {
        if (sInstance == null) {
            synchronized (UserPref.class) {
                sInstance = new UserPref(context);
            }
        }
        return sInstance;
    }

    private UserPref(Context context) {
        mPrefs = context.getApplicationContext().getSharedPreferences(USER_PREF, Context.MODE_PRIVATE);
    }

    public void putUser(User user) {
        mPrefs.edit()
                .putString(KEY_USER_NAME, user.getName())
                .putString(KEY_USER_SURNAME, user.getSurname())
                .putString(KEY_USER_EMAIL, user.getPhoneNumber())
                .apply();
    }


    @Nullable
    public User getUser() {
       String userSurname =  mPrefs.getString(KEY_USER_SURNAME, "");
       String userEmail = mPrefs.getString(KEY_USER_EMAIL, "");
       String userName = mPrefs.getString(KEY_USER_NAME, "");

       return new User.Builder()
               .setName(userName)
               .setSurname(userSurname)
               .setPhone(userEmail)
               .build();
    }

    public void clear() {
        mPrefs.edit().clear().apply();
    }
}
