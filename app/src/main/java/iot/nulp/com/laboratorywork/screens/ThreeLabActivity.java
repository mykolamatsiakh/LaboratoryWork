package iot.nulp.com.laboratorywork.screens;

import android.app.Activity;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import iot.nulp.com.laboratorywork.R;


public class ThreeLabActivity extends AppCompatActivity {
   private EditText mEmailEditText;
   private EditText mPasswordEdittext;
   private EditText mPasswordConfirmedEditText;
   private EditText mFirstNameEditText;
   private EditText mLastNameEditText;
   private EditText mPhoneEditText;

   private TextView mErrorsTextView;

   private Button mSubmit;
   List<String> errorsList = new ArrayList<>();

    
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

        mSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mErrorsTextView.setText("");
                if (validateFields()){
                    mErrorsTextView.setVisibility(View.GONE);
                    showToast("Everything is ok");
                }
                else {
                    for(int counter = 0; counter < errorsList.size(); counter++){
                        mErrorsTextView.append(errorsList.get(counter));
                        mErrorsTextView.append("\n");
                        Log.e("ERROR", errorsList.get(counter));
                    }
                    errorsList.clear();
                }
            }
        });

    }

    private boolean validateFields() {
        return validateName(getName(), getLastName()) &
                validateEmail(getEmail()) &
                validatePassword(getPassword(), getPasswordConfirmation()) &
                validatePhone(getPhone());

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

    private boolean validatePhone(String phoneNumber) {
        String regexStr = "/\\(?([0-9]{3})\\)?([ .-]?)([0-9]{3})\\2([0-9]{4})/";
        if(phoneNumber.matches(regexStr) && !TextUtils.isEmpty(phoneNumber)) {
            return true;
        }
        else {
            setError("Number is incorrect");
            return false;
        }
    }

    private boolean validatePassword(String password, String passwordConfirmation) {
        //Якщо поле для паролю пусте
        if(!TextUtils.isEmpty(password) && password.equals(passwordConfirmation)){
            return true;
        }
        else{
            setError("Passwords are not equals or empty");
            return false;
        }
    }

    private boolean validateEmail(String email){
        //Якщо імейл відповідає шаблону для імейлів
        if(Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            return true;
        }
        else{
            setError("Email is incorrect");
            return false;
        }
    }

    private boolean validateName(String name, String fullName) {
        String regex = "^[\\p{L} .'-]+$";
        Pattern pattern = Pattern.compile(regex,Pattern.CASE_INSENSITIVE);
        Matcher matcherName = pattern.matcher(name);
        Matcher matcherLastName = pattern.matcher(fullName);
        if(matcherName.find() && matcherLastName.find()){
            return true;
        }
        else{
            setError("Name is incorrect");
            return false;
        }
    }

    private void showToast(String message){
        Toast.makeText(ThreeLabActivity.this, message, Toast.LENGTH_LONG).show();
    }

    private void setError(String errorMessage) {
        mErrorsTextView.setVisibility(View.VISIBLE);
        errorsList.add(errorMessage);

    }
}
