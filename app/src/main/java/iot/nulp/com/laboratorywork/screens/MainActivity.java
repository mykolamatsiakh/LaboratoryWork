package iot.nulp.com.laboratorywork.screens;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import iot.nulp.com.laboratorywork.R;

public class MainActivity extends AppCompatActivity {

    EditText mEditText;
    Button mButtonNotify;
//    Button mButtonClear;
    TextView mTextViewSetInput;
    Button mButtonLabThree;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mEditText = findViewById(R.id.user_input);
//        mButtonClear = ;
        mButtonLabThree = findViewById(R.id.go_to_lab3);
        mTextViewSetInput = findViewById(R.id.user_text);
        mButtonNotify = findViewById(R.id.edit_text_notify);

        findViewById(R.id.edit_text_clear).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mEditText.setText("");
            }
        });
        mButtonNotify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mTextViewSetInput.setText(getString(R.string.say_hello,
                        mEditText.getText().toString().trim()));
                mEditText.setText("");
            }
        });
        mButtonLabThree.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent thirdLabActivity = new Intent(MainActivity.this,
                        ThreeLabActivity.class);
                startActivity(thirdLabActivity);
            }
        });


    }
}
