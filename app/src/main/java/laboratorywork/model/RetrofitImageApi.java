package laboratorywork.model;

import java.util.List;

import laboratorywork.model.ResponseModel;
import retrofit2.Call;
import retrofit2.http.GET;

public interface RetrofitImageApi {
    @GET("breed/hound/images")
    Call<ResponseModel> getImages();
}
