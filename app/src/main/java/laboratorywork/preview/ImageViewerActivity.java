package laboratorywork.preview;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;
import iot.nulp.com.laboratorywork.R;
import laboratorywork.LaboratoryWorkApplication;
import laboratorywork.view.TouchImageView;
import retrofit2.http.PATCH;

public class ImageViewerActivity extends AppCompatActivity implements ImageViewerView
        {

    @BindView(R.id.add_to_favourite)
    Button mAddToFavouriteButton;
    @BindView(R.id.image_view)
    TouchImageView mTouchImageView;
    ImageViewerPresenter mImageViewerPresenter;

    public static Intent getStartIntent(Context context, String path) {
        Intent intent = new Intent(context, ImageViewerActivity.class);
        intent.putExtra("IMAGE_PATH", path);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_viewer);
        ButterKnife.bind(this);
        mImageViewerPresenter = new ImageViewerPresenterImpl(this);
        mImageViewerPresenter.getDog();
        mTouchImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
        mAddToFavouriteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              addImagePathToPreferences();
            }
        });
        ActivityCompat.postponeEnterTransition(ImageViewerActivity.this);
        String path = getIntent().getExtras().getParcelable("IMAGE_PATH");
        loadImage(path);
    }



    public void loadImage(String path) {
        Picasso.with(mTouchImageView.getContext())
                .load(path)
                .into(mTouchImageView, new Callback() {
                    @Override
                    public void onSuccess() {
                        ActivityCompat.startPostponedEnterTransition(ImageViewerActivity.this);
                    }

                    @Override
                    public void onError() {
                        ActivityCompat.startPostponedEnterTransition(ImageViewerActivity.this);
                    }
                });

    }

    public void addImagePathToPreferences() {
        String pathKey = Objects.requireNonNull(getIntent().getExtras()).getParcelable("IMAGE_PATH");
        SharedPreferences prefs = PreferenceManager.
                getDefaultSharedPreferences(ImageViewerActivity.this);
        String imagePath = prefs.getString(pathKey, "");

        Editor prefEditor = PreferenceManager.
                getDefaultSharedPreferences(ImageViewerActivity.this).edit();
        prefEditor.putString(pathKey, imagePath+"&"+ pathKey);
        prefEditor.apply();
    }

            @Override
            public void setDog(String path) {
                loadImage(path);
            }
        }
