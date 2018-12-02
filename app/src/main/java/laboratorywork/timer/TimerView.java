package laboratorywork.timer;

public interface TimerView {
    void startCountdownTimer();
    void stopCountdownTimer();
    void setProgressbarValues();
    void startStop();
    void setTimerValue(int time);
}
