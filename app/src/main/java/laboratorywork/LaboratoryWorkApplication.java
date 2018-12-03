package laboratorywork;

import android.app.Application;
import android.support.v7.app.AppCompatActivity;

import iot.nulp.com.laboratorywork.R;
import laboratorywork.model.DogModel;
import laboratorywork.model.RetrofitImageApi;
import laboratorywork.model.RetrofitSingleton;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class LaboratoryWorkApplication extends Application {
    private static RetrofitImageApi mRetrofitImageApi;
    private static DogModel mDogModel;

    public static RetrofitImageApi getImageApi() {
        return mRetrofitImageApi;
    }


    public static DogModel getDogModel() {
        return mDogModel;
    }

    public static void setDogModel(DogModel mDogModel) {
        LaboratoryWorkApplication.mDogModel = mDogModel;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        initRetrofit();
    }

    private void initRetrofit() {
        mRetrofitImageApi = RetrofitSingleton.getInstance().getUserService();

    }
}