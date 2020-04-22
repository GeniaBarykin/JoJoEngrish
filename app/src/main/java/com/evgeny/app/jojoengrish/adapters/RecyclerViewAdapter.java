package com.evgeny.app.jojoengrish.adapters;

import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.evgeny.app.jojoengrish.R;
import com.evgeny.app.jojoengrish.audio.Player;
import com.evgeny.app.jojoengrish.models.SoundModel;

import java.util.ArrayList;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder> {
    private ArrayList<SoundModel> dataset;
    private Context context;
    private Player player = Player.getInstance();

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        TextView name,tags;
        ImageView picture;
        LinearLayout card;

        public MyViewHolder(View v) {
            super(v);
            name = v.findViewById(R.id.nameSoundPreviewTextView);
            tags = v.findViewById(R.id.tagsSoundPreviewTextView);
            picture = v.findViewById(R.id.pictureSoundPreviewImageView);
            card = v.findViewById(R.id.listCard);
            ////
        }
    }

    public RecyclerViewAdapter(Context context, ArrayList<SoundModel> sounds) {
        this.context = context;
        this.dataset = sounds;
    }

    @Override
    public RecyclerViewAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent,
                                                               int viewType) {
        // create a new view
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.recycler_sound_preview, parent,false);
        MyViewHolder vh = new MyViewHolder(view);
        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        holder.name.setText(dataset.get(position).getName());
        holder.tags.setText(dataset.get(position).getDescription());
        holder.picture.setImageResource(dataset.get(position).getPicture_adress());
        holder.card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(player.isPlaying()){
                    player.stop();
                } else {
                    try {
                        player.play(context,dataset.get(position).getSound_adress());
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        });


    }



    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return dataset.size();
    }


}
