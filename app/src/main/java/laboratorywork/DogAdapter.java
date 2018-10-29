package laboratorywork;

import android.content.Context;
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
    private List<Dog> dogsURLs;
    private Context context;

    public interface OnItemClickListener {
        void onItemClick(Dog dogsURL, View view);
    }

    OnItemClickListener mOnItemClickListener;

    public DogAdapter(Context context, List<Dog> dogsURLs) {
        this.context = context;
        this.dogsURLs = dogsURLs;
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        mOnItemClickListener = onItemClickListener;
    }

    @Override
    public DogAdapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).
                inflate(R.layout.activity_list_view,
                        viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int i) {
        Picasso.with(viewHolder.img_android.getContext()).
                load(dogsURLs.get(i).getImageUrl())
                .into(viewHolder.img_android);
    }

    @Override
    public int getItemCount() {
        return Dog.getCounter();
    }

    public void clear() {
        // TODO Auto-generated method stub
        dogsURLs.clear();

    }



   class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.dog_image)
        ImageView img_android;
        private Dog dog;

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


