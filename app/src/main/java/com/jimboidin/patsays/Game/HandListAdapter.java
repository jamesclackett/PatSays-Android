package com.jimboidin.patsays.Game;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.jimboidin.patsays.GameActivity;
import com.jimboidin.patsays.R;

import java.util.ArrayList;
import java.util.LinkedList;

public class HandListAdapter extends RecyclerView.Adapter<HandListAdapter.HandListViewHolder> {
    private ArrayList<Card> mHandList, mSelectedList;
    private LayoutInflater mInflater;
    private Listener listener;

    public interface Listener {
        void itemSelected(ArrayList<Card> selectedList);
    };

    public HandListAdapter(Context context, ArrayList<Card> handList){
        this.mInflater = LayoutInflater.from(context);
        this.mHandList = handList;
        this.mSelectedList = new ArrayList<>();
    }


    @NonNull
    @Override
    public HandListAdapter.HandListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.item_hand, parent, false);
        this.listener = (Listener) mInflater.getContext();
        return new HandListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HandListAdapter.HandListViewHolder holder, int position) {
        Drawable face = mInflater.getContext().getResources().getDrawable(mHandList.get(position).getIconID());
        Drawable border = mInflater.getContext().getResources().getDrawable(R.drawable.border);

        ImageView cardImage = holder.imageView;
        cardImage.setImageDrawable(face);
        cardImage.setBackground(null);
        cardImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Card card = mHandList.get(position);
                if (!mSelectedList.contains(card)){
                    mSelectedList.add(card);
                    cardImage.setBackground(border);
                } else {
                    mSelectedList.remove(card);
                    cardImage.setBackground(null);
                }
                listener.itemSelected(mSelectedList);

            }
        });



    }

    @Override
    public int getItemCount() {
        return mHandList.size();
    }



    class HandListViewHolder extends RecyclerView.ViewHolder{
        ImageView imageView;

        public HandListViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.card);
        }
    }
}
