package iot.nulp.com.laboratorywork.screens;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import iot.nulp.com.laboratorywork.R;
import iot.nulp.com.laboratorywork.screens.Model.Dog;


public class DogAdapter extends RecyclerView.Adapter<DogAdapter.ViewHolder> {

    private ArrayList<Dog> dogsURLs;
    private Context context;

    public DogAdapter(Context context, ArrayList<Dog> dogsURLs) {
        this.context = context;
        this.dogsURLs = dogsURLs;

    }

    @Override
    public DogAdapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.activity_list_view,
                viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int i) {

        Picasso.with(context).
                load(dogsURLs.get(i).getImageUrl()).
                resize(400, 400).
                into(viewHolder.img_android);
    }

    @Override
    public int getItemCount() {
        return dogsURLs.size();
    }



    public class ViewHolder extends RecyclerView.ViewHolder{
        ImageView img_android;
        public ViewHolder(View view) {
            super(view);

            img_android = view.findViewById(R.id.dog_image);
        }

    }
}


