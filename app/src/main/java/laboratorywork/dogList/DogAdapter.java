package laboratorywork.dogList;

import android.content.Context;
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
import laboratorywork.LaboratoryWorkApplication;
import laboratorywork.model.DogModel;


public class DogAdapter extends RecyclerView.Adapter<DogAdapter.ViewHolder> {
    private List<DogModel> mDogsUrls;
    private OnItemClickListener mOnItemClickListener;
    private Context mContext;

    public interface OnItemClickListener {
        void onItemClick(DogModel dogsURL, View view);
    }

    public DogAdapter(Context context, List<DogModel> dogsURLs) {
        this.mDogsUrls = dogsURLs;
        this.mContext = context;

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
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, final int i) {
        Picasso.with(viewHolder.mDogImage.getContext()).
                load(mDogsUrls.get(i).getImageUrl())
                .into(viewHolder.mDogImage);

        viewHolder.mDogImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LaboratoryWorkApplication.setDogModel(mDogsUrls.get(i));
            }
        });
    }

    @Override
    public int getItemCount() {
        return DogModel.getCounter();
    }

    public void clear() {
        mDogsUrls.clear();
        notifyDataSetChanged();

    }

    public void addAll(List<DogModel> dogs){
        mDogsUrls.addAll(dogs);
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.dog_image)
        ImageView mDogImage;
        DogModel mDog;

        ViewHolder(View view) {
            super(view);
            view.setOnClickListener(mOnClickListener);
            ButterKnife.bind(this, view);
        }

        final View.OnClickListener mOnClickListener
                = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mOnItemClickListener != null) mOnItemClickListener.onItemClick(mDog, view);
            }
        };
    }
}


