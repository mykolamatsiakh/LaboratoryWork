package laboratorywork.dogList;

import java.util.List;

import laboratorywork.model.DogModel;

public class DogListPresenterI implements DogListPresenter, DogListModel.OnFinishedListener{
    private DogListView mDogListView;
    private DogListModel mDogModel;

    public DogListPresenterI(DogListView dogListView){
        this.mDogListView = dogListView;
        this.mDogModel = new DogListModelImpl();
    }


    @Override
    public void onFinished(List<DogModel> dogsList, boolean isChange) {
        if (mDogListView != null) {
            if (!isChange) {
                mDogListView.setAdapterData(dogsList);
            } else {
                mDogListView.refreshData(dogsList);
            }
        }
    }

    @Override
    public void onFailure(Throwable t) {
        mDogListView.onFailureResponse(t);
    }

    @Override
    public void getDogsFromServer(boolean isChange) {
        mDogModel.getDogsList(this, isChange);
    }
}
