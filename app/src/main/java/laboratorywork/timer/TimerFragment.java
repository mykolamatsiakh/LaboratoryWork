package laboratorywork.timer;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.ButterKnife;
import iot.nulp.com.laboratorywork.R;
import laboratorywork.dogList.DogsFragment;

public class TimerFragment extends Fragment implements View.OnClickListener, TimerView{

    @BindView(R.id.progressBarCircle)
    ProgressBar mProgressBar;
    @BindView(R.id.textViewTime)
    TextView mTextViewTime;
    @BindView(R.id.button_start_timer)
    Button mButtonStartTimer;
    CountDownTimer mCountDownTimer;

    private long timeCountInMilliSeconds = 60000;
    private TimerStatus timerStatus = TimerStatus.STOPPED;

    private enum TimerStatus {
        STARTED,
        STOPPED
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View view = inflater.inflate(R.layout.activity_fragment, container, false);
        ButterKnife.bind(this, view);
        mButtonStartTimer.setOnClickListener(this);
        return view;
    }

    @Override
    public void startCountdownTimer() {
        mCountDownTimer = new CountDownTimer(timeCountInMilliSeconds, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                mTextViewTime.setText(hmsTimeFormatter(millisUntilFinished));
                mProgressBar.setProgress((int) (millisUntilFinished / 1000));
            }

            @Override
            public void onFinish() {
                stopCountdownTimer();
                setDogsFragment(new DogsFragment());
            }
        }.start();
        mCountDownTimer.start();
    }

    @Override
    public void stopCountdownTimer() {
        mCountDownTimer.cancel();
    }

    @Override
    public void setProgressbarValues() {
        mProgressBar.setMax((int) timeCountInMilliSeconds / 1000);
        mProgressBar.setProgress((int) timeCountInMilliSeconds / 1000);
    }

    @Override
    public void startStop() {
        if (timerStatus == TimerStatus.STOPPED) {
            setTimerValue(1);
            setProgressbarValues();
            timerStatus = TimerStatus.STARTED;
            startCountdownTimer();

        } else {
            timerStatus = TimerStatus.STOPPED;
            stopCountdownTimer();
        }

    }

    @Override
    public void setTimerValue(int time) {
        timeCountInMilliSeconds = time * 60 * 1000;
    }

    @SuppressLint("DefaultLocale")
    private String hmsTimeFormatter(long milliSeconds) {

        return String.format("%02d:%02d:%02d",
                TimeUnit.MILLISECONDS.toHours(milliSeconds),
                TimeUnit.MILLISECONDS.toMinutes(milliSeconds) - TimeUnit.HOURS.
                        toMinutes(TimeUnit.MILLISECONDS.toHours(milliSeconds)),
                TimeUnit.MILLISECONDS.toSeconds(milliSeconds) - TimeUnit.MINUTES.
                        toSeconds(TimeUnit.MILLISECONDS.toMinutes(milliSeconds)));


    }

    private void setDogsFragment(Fragment fragment) {
        getActivity().getSupportFragmentManager().
                beginTransaction()
                .replace(R.id.container, fragment)
                .addToBackStack(null)
                .commit();
    }

    @Override
    public void onClick(View view) {
        startStop();
    }
}
