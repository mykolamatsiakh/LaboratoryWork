package laboratorywork.screens;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;

import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout.OnRefreshListener;

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

public class DogsActivity extends AppCompatActivity {
    private static final String BASE_URL = "https://dog.ceo/api/";
    ArrayList<Dog> dogsImages = new ArrayList<>();
    Call<ResponseModel> callToRetrofit;

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

    }

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

    void getRetrofitImage() {

        RetrofitImageApi retrofitImageApi = RetrofitSingleton.getInstance().getUserService();
        callToRetrofit = retrofitImageApi.getImages();
        callToRetrofit.enqueue(new Callback<ResponseModel>() {
            @Override
            public void onResponse(Call<ResponseModel> call, Response<ResponseModel> response) {
                if(response.isSuccessful()) {
                    for (int counter = 0; counter < response.body().getMessage().size(); counter++) {
                        Dog dog = new Dog(response.body().getMessage().get(counter));
                        dogsImages.add(dog);
                    }
                    RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
                    mRecyclerView.setLayoutManager(layoutManager);
                    DogAdapter adapter = new DogAdapter(getApplicationContext(), dogsImages);
                    mRecyclerView.setAdapter(adapter);
                }
                return;
            }

            @Override
            public void onFailure(Call<ResponseModel> call, Throwable t) {
                setContentView(R.layout.activity_no_data);
            }
        });

    }
}
