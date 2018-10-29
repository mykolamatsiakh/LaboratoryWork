package laboratorywork.screens;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;

import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout.OnRefreshListener;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;

import butterknife.BindColor;
import butterknife.BindView;
import butterknife.ButterKnife;
import iot.nulp.com.laboratorywork.R;
import laboratorywork.DogAdapter;
import laboratorywork.model.ResponseModel;
import laboratorywork.api.RetrofitImageApi;
import laboratorywork.api.RetrofitSingleton;
import laboratorywork.model.Dog;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DogsActivity extends AppCompatActivity{
    List<Dog> dogsImagesURL = new ArrayList<>();
    Call<ResponseModel> callToRetrofit;
    DogAdapter mDogAdapter;

    @BindView(R.id.dogs_toolbar)
    Toolbar mToolbar;
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
        getRetrofitImage();
        initView();
        RecyclerView.LayoutManager layoutManager =
                new LinearLayoutManager(getApplicationContext());
        mRecyclerView.setLayoutManager(layoutManager);
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                replaceOldListWithNewList();
            }
        });



    }

    final DogAdapter.OnItemClickListener mOnItemClickListener =
            new DogAdapter.OnItemClickListener() {
                @Override
                public void onItemClick(Dog dog, View view) {
                    Intent startIntent = ImageViewerActivity.getStartIntent(DogsActivity.this,
                            dogsImagesURL.get(mRecyclerView.getLayoutManager().getPosition(view)).
                                    getImageUrl());
                    ActivityOptionsCompat activityOptionsCompat = ActivityOptionsCompat
                            .makeSceneTransitionAnimation(DogsActivity.this, view,
                                    "transition");
                    startActivity(startIntent, activityOptionsCompat.toBundle());
                }
            };

    void initView(){
        mSwipeLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh() {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        mSwipeLayout.setRefreshing(false);
                        getRetrofitImage();
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

    private void replaceOldListWithNewList() {
        dogsImagesURL.clear();
        ArrayList<Dog> dogsFavourites = new ArrayList<>();
        SharedPreferences prefs = PreferenceManager.
                getDefaultSharedPreferences(DogsActivity.this);
        String imagePath = prefs.getString("IMAGE_PATH", "");
        String finalString = imagePath.substring(1, imagePath.length());
        String[] paths = finalString.split(Pattern.quote("&"));
        for (String path : paths) {
            Dog favouriteDog = new Dog(path);
            dogsFavourites.add(favouriteDog);
        }
        dogsImagesURL.addAll(dogsFavourites);
        mDogAdapter.notifyDataSetChanged();
    }

    void getRetrofitImage() {
        RetrofitImageApi retrofitImageApi = RetrofitSingleton.getInstance().getUserService();
        callToRetrofit = retrofitImageApi.getImages();
        callToRetrofit.enqueue(new Callback<ResponseModel>() {
            @Override
            public void onResponse(Call<ResponseModel> call, Response<ResponseModel> response) {
                if(response.isSuccessful()) {
                    for (int counter = 0; counter < response.body().getMessage().size(); counter++) {
                        Dog dog = new Dog(response.body().getMessage().get(counter));
                        dogsImagesURL.add(dog);
                    }
                    mDogAdapter = new DogAdapter(getApplicationContext(), dogsImagesURL);
                    mRecyclerView.setAdapter(mDogAdapter);
                    mDogAdapter.setOnItemClickListener(mOnItemClickListener);

                }
            }

            @Override
            public void onFailure(Call<ResponseModel> call, Throwable t) {
                setContentView(R.layout.activity_no_data);
            }
        });

    }

}
