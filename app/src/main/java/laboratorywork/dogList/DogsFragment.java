package laboratorywork.dogList;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Parcel;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import android.support.v4.widget.SwipeRefreshLayout.OnRefreshListener;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import butterknife.BindColor;
import butterknife.BindView;
import butterknife.ButterKnife;
import iot.nulp.com.laboratorywork.R;
import laboratorywork.LaboratoryWorkApplication;
import laboratorywork.model.DogModel;
import laboratorywork.preview.ImageViewerFragment;

public class DogsFragment extends Fragment implements DogListView {
    private List<DogModel> mDogsImagesUrl = new ArrayList<>();
    private DogAdapter mDogAdapter;
    private static final String EXTRA_IMAGE_PATH = "EXTRA_IMAGE_PATH";
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

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.onCreate(savedInstanceState);
        View view = inflater.inflate(R.layout.activity_fourth_lab, container, false);
        ButterKnife.bind(this, view);
        setupPresenter();
        mDogListPresenter.getDogsFromServer(false);
        initView();
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                replaceOldListWithNewList();
            }
        });

        return view;
    }

    final DogAdapter.OnItemClickListener mOnItemClickListener =
            new DogAdapter.OnItemClickListener() {
                @Override
                public void onItemClick(DogModel dog, View view) {
                    Bundle bundle = new Bundle();
                    bundle.putString(EXTRA_IMAGE_PATH, dog.getImageUrl());
                    ImageViewerFragment newFragment = new ImageViewerFragment();
                    newFragment.setArguments(bundle);
                    setDogsFragment(newFragment);
                }
            };

    private void setupPresenter(){
        mDogListPresenter = new DogListPresenterI(new DogListModelImpl(), this);
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
                getDefaultSharedPreferences(getActivity());
        String imagePath = prefs.getString(EXTRA_IMAGE_PATH, "");
        String finalString = imagePath.substring(1, imagePath.length());
        String[] paths = finalString.split(Pattern.quote("&"));
        for (String path : paths) {
            Parcel parcel = Parcel.obtain();
            parcel.writeString(path);
            DogModel favouriteDog = new DogModel(parcel);
            dogsFavourites.add(favouriteDog);
        }
    }

    private void setDogsFragment(Fragment fragment) {
        getActivity().getSupportFragmentManager().
                beginTransaction()
                .replace(R.id.container, fragment)
                .addToBackStack(null)
                .commit();
    }

    @Override
    public void setAdapterData(List<DogModel> dogimagesUrl) {
        mDogAdapter = new DogAdapter(getActivity(), dogimagesUrl);
        mRecyclerView.setAdapter(mDogAdapter);
        mDogAdapter.setOnItemClickListener(mOnItemClickListener);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
    }

    @Override
    public void refreshData(List<DogModel> dogimagesUrl) {
        mDogAdapter.clear();
        mDogAdapter.addAll(dogimagesUrl);
        mSwipeLayout.setRefreshing(false);
    }

    @Override
    public void onFailureResponse(Throwable throwable) {
        Toast.makeText(getActivity(), "Response failed", Toast.LENGTH_LONG).show();
    }

}
