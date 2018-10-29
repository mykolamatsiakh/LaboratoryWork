package laboratorywork.screens;

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
import android.widget.ImageView;

import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;
import iot.nulp.com.laboratorywork.R;
import laboratorywork.view.TouchImageView;

public class ImageViewerActivity extends AppCompatActivity{
    private static final String EXTRA_IMAGE_PATH = "IMAGE_PATH";

    @BindView(R.id.add_to_favourite)
    Button mAddToFavouriteButton;
    @BindView(R.id.image_view)
    TouchImageView mTouchImageView;

    public static Intent getStartIntent(Context context, String path) {
        Intent intent = new Intent(context, ImageViewerActivity.class);
        intent.putExtra(EXTRA_IMAGE_PATH, path);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_viewer);
        ButterKnife.bind(this);
        mTouchImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        mAddToFavouriteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String path = getIntent().getStringExtra(EXTRA_IMAGE_PATH);
                SharedPreferences prefs = PreferenceManager.
                        getDefaultSharedPreferences(ImageViewerActivity.this);
                String imagePath = prefs.getString("IMAGE_PATH", "");

                Editor prefEditor = PreferenceManager.
                        getDefaultSharedPreferences(ImageViewerActivity.this).edit();
                prefEditor.putString("IMAGE_PATH", imagePath+"&"+ path);
                prefEditor.apply();
            }
        });
        ActivityCompat.postponeEnterTransition(ImageViewerActivity.this);
        String path = getIntent().getStringExtra(EXTRA_IMAGE_PATH);
        loadImage(path);
    }

    private void loadImage(String path) {

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
}
