package com.example.practicaexamen.data.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.practicaexamen.R;
import com.example.practicaexamen.data.model.Offer;
import com.example.practicaexamen.data.repository.OfferRepository;

import java.util.ArrayList;
import java.util.Collections;

public class OfferAdapter extends RecyclerView.Adapter<OfferAdapter.ViewHolder> {
    private ArrayList<Offer> list;
    private OnManageOfferListener listener;

    public interface OnManageOfferListener{
        void onEditOffer(Offer offer);
        void onDeleteOffer(Offer offer);
    }

    public OfferAdapter(OnManageOfferListener listener) {
        this.list = new ArrayList<>();
        this.listener = listener;
    }

    @NonNull
    @Override
    public OfferAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_offer, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull OfferAdapter.ViewHolder holder, int position) {
        holder.tvName.setText(list.get(position).getName());
        switch (list.get(position).getType()){
            case HOME:
                holder.imgImage.setImageResource(R.drawable.home);
                break;
            case ELECTRONIC:
                holder.imgImage.setImageResource(R.drawable.electronic);
                break;
            case GAMES:
                holder.imgImage.setImageResource(R.drawable.games);
                break;
        }
        holder.bind(listener, list.get(position));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView imgImage;
        private TextView tvName;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            imgImage = itemView.findViewById(R.id.imgImage);
            tvName = itemView.findViewById(R.id.tvName);
        }

        public void bind(OnManageOfferListener listener, Offer offer){
            itemView.setOnClickListener(v -> listener.onEditOffer(offer));
            itemView.setOnLongClickListener(v -> {
                listener.onDeleteOffer(offer);
                return true;
            });
        }
    }

    public void update(ArrayList<Offer> data) {
        list.clear();
        list.addAll(data);
        Collections.sort(list);

        notifyDataSetChanged();
    }

    public void orderBy(ArrayList<Offer> data) {
        list.clear();
        list.addAll(data);

        notifyDataSetChanged();
    }
}
