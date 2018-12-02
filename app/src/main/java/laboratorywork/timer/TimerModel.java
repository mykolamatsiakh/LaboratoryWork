package laboratorywork.timer;

import java.util.List;

import laboratorywork.model.DogModel;

public interface TimerModel {
    interface Result {
        void onSuccess(List<DogModel> dogModels);
        void onFailure(Throwable throwable);
    }
    void requestDataFromServer(Result result);
}
