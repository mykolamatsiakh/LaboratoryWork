package laboratorywork.dogList;

import android.content.Context;
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
import java.util.List;
import java.util.regex.Pattern;

import android.support.v4.widget.SwipeRefreshLayout.OnRefreshListener;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Toast;

import butterknife.BindColor;
import butterknife.BindView;
import butterknife.ButterKnife;
import iot.nulp.com.laboratorywork.R;
import laboratorywork.LaboratoryWorkApplication;
import laboratorywork.preview.ImageViewerActivity;
import laboratorywork.model.DogModel;

public class DogsActivity extends AppCompatActivity implements DogListView  {
    private List<DogModel> mDogsImagesUrl = new ArrayList<>();
    private DogAdapter mDogAdapter;

    private DogListPresenter mDogListPresenter;

    @BindView(R.id.toolbar_dogs)
    Toolbar mToolbar;
    @BindView(R.id.recycler_view_users)
    protected RecyclerView mRecyclerView;
    @BindView(R.id.swipe_container)
    SwipeRefreshLayout mSwipeLayout;
    @BindColor(R.color.colorAccent)
    int mBlueBright;
    @BindColor(R.color.colorGreen)
    int mGreenLight;
    @BindColor(R.color.colorGrey)
    int mOrangeLight;
    @BindColor(R.color.colorPrimary)
    int mRedLight;

    public static Intent getStartIntent(Context context) {
        Intent intent = new Intent(context, DogsActivity.class);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fourth_lab);
        ButterKnife.bind(this);
        mDogListPresenter = new DogListPresenterI(this);
        mDogListPresenter.getDogsFromServer(false);
        initView();
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
                public void onItemClick(DogModel dog, View view) {
                    startImageViewerActivity(view);
                }
            };


    public void startImageViewerActivity(View view) {
        Intent startIntent = ImageViewerActivity.getStartIntent(DogsActivity.this,
                LaboratoryWorkApplication.getDogModel().getImageUrl());
        ActivityOptionsCompat activityOptionsCompat = ActivityOptionsCompat
                .makeSceneTransitionAnimation(DogsActivity.this, view,
                        "transition");
        startActivity(startIntent, activityOptionsCompat.toBundle());
    }

    public void initView() {
        mSwipeLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh() {
                mDogListPresenter.getDogsFromServer(true);
            }
        });

        mSwipeLayout.setColorSchemeColors(
                mBlueBright,
                mGreenLight,
                mOrangeLight,
                mRedLight
        );
    }

    public void replaceOldListWithNewList() {
        mDogsImagesUrl.clear();
        ArrayList<DogModel> dogsFavourites = new ArrayList<>();
        SharedPreferences prefs = PreferenceManager.
                getDefaultSharedPreferences(DogsActivity.this);
        String imagePath = prefs.getString(LaboratoryWorkApplication.getExtraImagePath(), "");
        String finalString = imagePath.substring(1, imagePath.length());
        String[] paths = finalString.split(Pattern.quote("&"));
        for (String path : paths) {
            DogModel favouriteDog = new DogModel(path);
            dogsFavourites.add(favouriteDog);
        }
    }

    @Override
    public void setAdapterData(List<DogModel> dogimagesUrl) {
        mDogAdapter = new DogAdapter(DogsActivity.this, dogimagesUrl);
        mRecyclerView.setAdapter(mDogAdapter);
        mDogAdapter.setOnItemClickListener(mOnItemClickListener);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    public void refreshData(List<DogModel> dogimagesUrl) {
        mDogAdapter.clear();
        mDogAdapter.addAll(dogimagesUrl);
        mSwipeLayout.setRefreshing(false);
    }

    @Override
    public void onFailureResponse(Throwable throwable) {
        Toast.makeText(this, "Response failed", Toast.LENGTH_LONG).show();
    }

}
