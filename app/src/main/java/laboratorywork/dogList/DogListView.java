package laboratorywork.dogList;

import java.util.List;

import laboratorywork.model.DogModel;

public interface DogListView {

    void setAdapterData(List<DogModel> dogimagesUrl);

    void refreshData(List<DogModel> dogimagesUrl);

    void onFailureResponse(Throwable throwable);
}
