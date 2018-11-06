package laboratorywork.screens;

import android.content.Intent;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import iot.nulp.com.laboratorywork.R;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        findViewById(R.id.button_go_to_dogs).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startDogsActivity();
            }
        });


    }

    private void startDogsActivity() {
        Intent startIntent = DogsActivity.getStartIntent(MainActivity.this);
        startActivity(startIntent);
    }
}
