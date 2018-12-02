package laboratorywork.timer;

public class TimerPresenterImpl implements TimerPresenter {

    private TimerModel mModel;
    private TimerView mTimerView;

    TimerPresenterImpl(TimerView view, TimerModel model) {
        mTimerView = view;
        mModel = model;
    }

    @Override
    public void onCreate() {
        mTimerView.startCountdownTimer();
    }
}
