package laboratorywork.preview;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TabHost;

import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;
import iot.nulp.com.laboratorywork.R;
import laboratorywork.view.TouchImageView;

public class ImageViewerFragment extends Fragment implements ImageViewerView {

    @BindView(R.id.add_to_favourite)
    Button mAddToFavouriteButton;
    @BindView(R.id.image_view)
    TouchImageView mTouchImageView;
    ImageViewerPresenter mImageViewerPresenter;
    private static final String EXTRA_IMAGE_PATH = "EXTRA_IMAGE_PATH";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View view = inflater.inflate(R.layout.activity_image_viewer, container, false);
        ButterKnife.bind(this, view);
        mImageViewerPresenter = new ImageViewerPresenterImpl(this);
        mAddToFavouriteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addImagePathToPreferences();
            }
        });
        ActivityCompat.postponeEnterTransition(getActivity());
        Bundle bundle = getArguments();
        String imagePath = bundle.getString(EXTRA_IMAGE_PATH);
        loadImage(imagePath);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    public void loadImage(String path) {
        Picasso.with(mTouchImageView.getContext())
                .load(path)
                .into(mTouchImageView, new Callback() {
                    @Override
                    public void onSuccess() {
                        ActivityCompat.startPostponedEnterTransition(getActivity());
                    }

                    @Override
                    public void onError() {
                        ActivityCompat.startPostponedEnterTransition(getActivity());
                    }
                });

    }

    public void addImagePathToPreferences() {
        String pathKey = Objects.requireNonNull(getActivity().getIntent().getExtras()).getParcelable("IMAGE_PATH");
        SharedPreferences prefs = PreferenceManager.
                getDefaultSharedPreferences(getActivity());
        String imagePath = prefs.getString(pathKey, "");

        Editor prefEditor = PreferenceManager.
                getDefaultSharedPreferences(getActivity()).edit();
        prefEditor.putString(pathKey, imagePath + "&" + pathKey);
        prefEditor.apply();
    }

    @Override
    public void setDog(String path) {
        loadImage(path);
    }
}
