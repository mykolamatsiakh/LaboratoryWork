package laboratorywork.dogList;

import java.util.List;

import laboratorywork.model.DogModel;

public interface DogListModel {
    interface OnFinishedListener {
        void onFinished(List<DogModel> dogsList, boolean isChange);

        void onFailure(Throwable t);
    }

    void getDogsList(DogListModel.OnFinishedListener onFinishedListener,
                     boolean isChange);
}
