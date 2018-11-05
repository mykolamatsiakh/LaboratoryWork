package laboratorywork;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import iot.nulp.com.laboratorywork.R;
import laboratorywork.model.Dog;


public class DogAdapter extends RecyclerView.Adapter<DogAdapter.ViewHolder> {
    private List<Dog> mDogsUrls;
    private OnItemClickListener mOnItemClickListener;

    public interface OnItemClickListener {
        void onItemClick(Dog dogsURL, View view);
    }


    public DogAdapter(List<Dog> dogsURLs) {
        this.mDogsUrls = dogsURLs;
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        mOnItemClickListener = onItemClickListener;
    }

    @NonNull
    @Override
    public DogAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).
                inflate(R.layout.activity_list_item,
                        viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        Picasso.with(viewHolder.mDogImage.getContext()).
                load(mDogsUrls.get(i).getImageUrl())
                .into(viewHolder.mDogImage);
    }

    @Override
    public int getItemCount() {
        return Dog.getCounter();
    }

    public void clear() {
        mDogsUrls.clear();

    }


    class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.dog_image)
        ImageView mDogImage;
        Dog dog;

        ViewHolder(View view) {
            super(view);
            view.setOnClickListener(mOnClickListener);
            ButterKnife.bind(this, view);
        }

        final View.OnClickListener mOnClickListener
                = new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (mOnItemClickListener != null) mOnItemClickListener.onItemClick(dog, view);
            }
        };
    }
}


