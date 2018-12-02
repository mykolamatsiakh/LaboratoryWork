package laboratorywork;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;

import iot.nulp.com.laboratorywork.R;
import laboratorywork.dogList.DogsFragment;
import laboratorywork.timer.TimerFragment;


public class MainActivity extends FragmentActivity{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.button_go_to_dogs).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                setTimerFragment(new TimerFragment());
                findViewById(R.id.button_go_to_dogs).setVisibility(View.INVISIBLE);
            }
        });
    }

    private void setTimerFragment(Fragment fragment){
        getSupportFragmentManager().
                beginTransaction()
                .replace(R.id.container, fragment)
                .addToBackStack(null)
                .commit();
    }

}
