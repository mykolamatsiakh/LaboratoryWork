package iot.nulp.com.laboratorywork.screens;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;

public interface RetrofitImageApi {
    @GET("breed/hound/images")
    Call<ResponseBody> getImages();
}
