package iot.nulp.com.laboratorywork.screens;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout.OnRefreshListener;

import iot.nulp.com.laboratorywork.R;
import iot.nulp.com.laboratorywork.screens.Model.Dog;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class FourthLabActivity extends AppCompatActivity{
    private static final String BASE_URL = "https://dog.ceo/api/";
    ArrayList<Dog> dogs_urls = new ArrayList<>();
    RecyclerView recyclerView;
    SwipeRefreshLayout swipeLayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fourth_lab);
         recyclerView = findViewById(R.id.users);
        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        getRetrofitImage();
        swipeLayout = findViewById(R.id.swipe_container);
        swipeLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh() {
                new Handler().postDelayed(new Runnable() {
                    @Override public void run() {
                        swipeLayout.setRefreshing(false);
                    }
                }, 4000); // Delay in millis
            }
        });

        swipeLayout.setColorSchemeColors(
                getResources().getColor(android.R.color.holo_blue_bright),
                getResources().getColor(android.R.color.holo_green_light),
                getResources().getColor(android.R.color.holo_orange_light),
                getResources().getColor(android.R.color.holo_red_light)
        );

    }

    void getRetrofitImage(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        RetrofitImageApi retrofitImageApi = retrofit.create(RetrofitImageApi.class);
        Call<ResponseBody> call = retrofitImageApi.getImages();
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if(response.isSuccessful()) {

                    try {
                        String qwer = response.body().string();
                        try {
                            JSONObject jsonObject = new JSONObject(qwer);
                            JSONArray jsonArray = jsonObject.getJSONArray("message");
                            for(int counter = 0; counter < jsonArray.length(); counter++){
                                Dog dog = new Dog();
                                dog.setImageUrl(jsonArray.getString(counter));
                                dogs_urls.add(dog);
                            }
                            DogAdapter adapter = new DogAdapter(getApplicationContext(), dogs_urls);
                            recyclerView.setAdapter(adapter);
                            Log.e("QWERTY", dogs_urls.toString());
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                }

            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                        Log.e("ERROR",t.getMessage());
            }
        });
    }
}
