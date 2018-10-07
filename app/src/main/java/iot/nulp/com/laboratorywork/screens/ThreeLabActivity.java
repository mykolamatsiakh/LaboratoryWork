package iot.nulp.com.laboratorywork.screens;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.telephony.PhoneNumberUtils;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import iot.nulp.com.laboratorywork.R;
import retrofit2.http.HEAD;

import android.content.SharedPreferences.Editor;


public class ThreeLabActivity extends AppCompatActivity {
    private EditText mEmailEditText;
    private EditText mPasswordEdittext;
    private EditText mPasswordConfirmedEditText;
    private EditText mFirstNameEditText;
    private EditText mLastNameEditText;
    private EditText mPhoneEditText;

    private TextView mErrorsTextView;

    Button mSubmit;
    Button mViewList;

<<<<<<< HEAD

=======
    
>>>>>>> 0855e4e... [^] implement lab 5,6
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third_lab);
        mFirstNameEditText = findViewById(R.id.edit_text_first_name);
        mLastNameEditText = findViewById(R.id.edit_text_last_name);
        mEmailEditText = findViewById(R.id.edit_text_email);
        mPhoneEditText = findViewById(R.id.edit_text_phone);
        mPasswordEdittext = findViewById(R.id.edit_text_password);
        mPasswordConfirmedEditText = findViewById(R.id.edit_text_confirmation);
        mErrorsTextView = findViewById(R.id.text_view_errors);
        mSubmit = findViewById(R.id.button_submit);
        mViewList = findViewById(R.id.button_view_list);

        mSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                doSubmit();
            }
        });
        mViewList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ThreeLabActivity.this, FourthLabActivity.class);
                startActivity(intent);
            }
        });

    }

    private void doSubmit() {
        validateFields();
        mErrorsTextView.setText("");
<<<<<<<HEAD
        if (validateFields()) {
            SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
            String myStrValue = prefs.getString("NAME", "");
            Editor prefEditor = PreferenceManager.getDefaultSharedPreferences(this).edit();
            prefEditor.putString("NAME", myStrValue + "/" + getName());
            String mySurnameValue = prefs.getString("SURNAME", "");
            prefEditor.putString("SURNAME", mySurnameValue + "/" + getLastName());
            String myPhoneValue = prefs.getString("PHONE", "");
            prefEditor.putString("PHONE", myPhoneValue + "/" + getPhone());
            prefEditor.apply();
        } else {
=======
        if(validateFields()){
            SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
            String myStrValue = prefs.getString("NAME", "");
            Editor prefEditor = PreferenceManager.getDefaultSharedPreferences(this).edit();
            prefEditor.putString("NAME", myStrValue+"/"+getName());
            String mySurnameValue = prefs.getString("SURNAME", "");
            prefEditor.putString("SURNAME", mySurnameValue+"/"+getLastName());
            String myPhoneValue = prefs.getString("PHONE", "");
            prefEditor.putString("PHONE", myPhoneValue+"/"+getPhone());
            prefEditor.apply();
        }
        else{
>>>>>>> 0855e4e... [^] implement lab 5,6
            showToast("Error");
        }
    }

    private boolean validateFields() {
        validEmail(getEmail());
        validName(getName(), getLastName());
        validPassword(getPassword(), getPasswordConfirmation());
        validPhone(getPhone());
        return mFirstNameEditText.getError() == null &
                mLastNameEditText.getError() == null &
                mPhoneEditText.getError() == null &
                mPasswordEdittext.getError() == null &
                mPasswordConfirmedEditText.getError() == null &
                mEmailEditText.getError() == null;

    }

    @NonNull
    private String getEmail() {
        return mEmailEditText.getText().toString().trim();
    }

    @NonNull
    private String getPassword() {
        return mPasswordEdittext.getText().toString().trim();
    }

    @NonNull
    private String getPasswordConfirmation() {
        return mPasswordConfirmedEditText.getText().toString().trim();
    }

    @NonNull
    private String getPhone() {
        return mPhoneEditText.getText().toString().trim();
    }

    @NonNull
    private String getName() {
        return mFirstNameEditText.getText().toString().trim();
    }

    @NonNull
    private String getLastName() {
        return mLastNameEditText.getText().toString().trim();
    }

    private void validPhone(String phoneNumber) {
        if (PhoneNumberUtils.isGlobalPhoneNumber(phoneNumber) && !TextUtils.isEmpty(phoneNumber)) {
            mPhoneEditText.setError(null);
        } else {
            mPhoneEditText.setError("Number is incorrect");
        }
    }

    private void validPassword(String password, String passwordConfirmation) {
        if (!TextUtils.isEmpty(password) && password.equals(passwordConfirmation)) {
            mPasswordEdittext.setError(null);
        } else {
            mPasswordEdittext.setError("Passwords are not equals or empty");
        }
    }

    private void validEmail(String email) {
        if (Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            mEmailEditText.setError(null);
        } else {
            mEmailEditText.setError("Email is incorrect");
        }
    }

    private void validName(String name, String fullName) {
        String regex = "^[\\p{L} .'-]+$";
        Pattern pattern = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
        Matcher matcherName = pattern.matcher(name);
        Matcher matcherLastName = pattern.matcher(fullName);
        if (matcherName.find() & matcherLastName.find()) {
            mFirstNameEditText.setError(null);
<<<<<<< HEAD
        } else {
=======
        } else{
>>>>>>> 0855e4e... [^] implement lab 5,6
            mFirstNameEditText.setError("Name or Full name is incorrect");
        }
    }

    private void showToast(String message) {
        Toast.makeText(ThreeLabActivity.this, message, Toast.LENGTH_LONG).show();
    }

}
