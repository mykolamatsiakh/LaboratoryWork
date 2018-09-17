package iot.nulp.com.laboratorywork.screens;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import iot.nulp.com.laboratorywork.R;
import iot.nulp.com.laboratorywork.screens.Prefs.User;
import iot.nulp.com.laboratorywork.screens.Prefs.UserPref;

public class FourthLabActivity extends AppCompatActivity{
    private static final String EXTRA_USER = "EXTRA_USER";
    User mUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fourth_lab);

        mUser = UserPref.get(FourthLabActivity.this).getUser();
        Log.e("USER", mUser.getName());
        mUser = UserPref.get(FourthLabActivity.this).getUser();
        Log.e("USER", mUser.getName());
    }
}
