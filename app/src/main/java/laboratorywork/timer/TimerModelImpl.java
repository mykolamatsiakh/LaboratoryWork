package laboratorywork.timer;

import java.util.ArrayList;
import java.util.List;

import laboratorywork.LaboratoryWorkApplication;
import laboratorywork.model.DogModel;
import laboratorywork.model.ResponseModel;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TimerModelImpl implements TimerModel{
    private List<DogModel> mDogsImagesUrl = new ArrayList<>();

    @Override
    public void requestDataFromServer(final Result result) {
        Call<ResponseModel> call = LaboratoryWorkApplication.getImageApi().getImages();
        call.enqueue(new Callback<ResponseModel>() {
            @Override
            public void onResponse(Call<ResponseModel> call, Response<ResponseModel> response) {
                if (response.isSuccessful()) {
                    for (int counter = 0; counter < response.body().getMessage().size(); counter++) {
                        DogModel dog = new DogModel(response.body().getMessage().get(counter));
                        mDogsImagesUrl.add(dog);
                    }
                    result.onSuccess(mDogsImagesUrl);
                }
            }

            @Override
            public void onFailure(Call<ResponseModel> call, Throwable t) {
                result.onFailure(t);
            }
        });
    }
}
