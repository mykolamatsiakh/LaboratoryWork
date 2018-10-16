package iot.nulp.com.laboratorywork.screens;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout.OnRefreshListener;

import butterknife.BindColor;
import butterknife.BindView;
import butterknife.ButterKnife;
import iot.nulp.com.laboratorywork.R;
import iot.nulp.com.laboratorywork.screens.Model.Dog;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class FourthLabActivity extends AppCompatActivity {

    private static final String BASE_URL = "https://dog.ceo/api/";
    ArrayList<Dog> dogsImages = new ArrayList<>();
    @BindView(R.id.users)
    protected RecyclerView mRecyclerView;
    @BindView(R.id.swipe_container)
    SwipeRefreshLayout mSwipeLayout;
    @BindColor(R.color.colorAccent)
    int blue_bright;
    @BindColor(R.color.colorGreen)
    int green_light;
    @BindColor(R.color.colorGrey)
    int orange_light;
    @BindColor(R.color.colorPrimary)
    int red_light;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fourth_lab);
        ButterKnife.bind(this);
        sth();
        getRetrofitImage();

    }

    void sth(){
        mRecyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        mRecyclerView.setLayoutManager(layoutManager);
        getRetrofitImage();
        mSwipeLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh() {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        mSwipeLayout.setRefreshing(false);
                    }
                }, 4000);
            }
        });

        mSwipeLayout.setColorSchemeColors(
                blue_bright,
                green_light,
                orange_light,
                red_light
        );
    }

    void getRetrofitImage() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        RetrofitImageApi retrofitImageApi = retrofit.create(RetrofitImageApi.class);
        Call<ResponseBody> call = retrofitImageApi.getImages();
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(@NonNull Call<ResponseBody> call,
                                   @NonNull Response<ResponseBody> response) {
                if (response.isSuccessful()) {
                    try {
                        String qwer = response.body().string();
                        try {
                            JSONObject jsonObject = new JSONObject(qwer);
                            JSONArray jsonArray = jsonObject.getJSONArray("message");
                            for (int counter = 0; counter < jsonArray.length(); counter++) {
                                Dog dog = new Dog();
                                dog.setImageUrl(jsonArray.getString(counter));
                                dogsImages.add(dog);
                            }
                            DogAdapter adapter = new DogAdapter(getApplicationContext(), dogsImages);
                            mRecyclerView.setAdapter(adapter);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                }
                else{
                    setContentView(R.layout.activity_no_data);
                }

            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                setContentView(R.layout.activity_no_data);
            }
        });
    }
}
