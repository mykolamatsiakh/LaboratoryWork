package laboratorywork.dogList;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import laboratorywork.LaboratoryWorkApplication;
import laboratorywork.model.DogModel;
import laboratorywork.model.ResponseModel;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DogListModelImpl implements DogListModel{
    private List<DogModel> mDogsImagesUrl = new ArrayList<>();

    @Override
    public void getDogsList(final OnFinishedListener onFinishedListener,final boolean isChange) {
        Call<ResponseModel> call = LaboratoryWorkApplication.getImageApi().getImages();
        call.enqueue(new Callback<ResponseModel>() {
            @Override
            public void onResponse(Call<ResponseModel> call, Response<ResponseModel> response) {
                if (response.isSuccessful()) {
                    for (int counter = 0; counter < response.body().getMessage().size(); counter++) {
                        DogModel dogModel = new DogModel(response.body().getMessage().get(counter));
                        mDogsImagesUrl.add(dogModel);
                    }
                    onFinishedListener.onFinished(mDogsImagesUrl, isChange);
                }
    }

            @Override
            public void onFailure(Call<ResponseModel> call, Throwable t) {
                onFinishedListener.onFailure(t);
            }
        });
    }

}