package laboratorywork.model;

import laboratorywork.model.ResponseModel;
import retrofit2.Call;
import retrofit2.http.GET;

public interface RetrofitImageApi {
    @GET("breed/hound/images")
    Call<ResponseModel> getImages();
}
