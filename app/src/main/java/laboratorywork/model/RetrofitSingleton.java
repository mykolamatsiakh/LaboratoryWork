package laboratorywork.model;


import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitSingleton {
    private static RetrofitSingleton instance = null;
    private static final String BASE_URL = "https://dog.ceo/api/";

    private RetrofitImageApi mUserService;

    public static RetrofitSingleton getInstance() {
        if (instance == null) {
            instance = new RetrofitSingleton();
        }

        return instance;
    }

    private RetrofitSingleton() {
        buildRetrofit();
    }

    private void buildRetrofit() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        this.mUserService = retrofit.create(RetrofitImageApi.class);

    }

    public RetrofitImageApi getUserService() {
        return this.mUserService;
    }
}
