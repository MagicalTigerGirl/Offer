package com.example.practicaexamen.data.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.practicaexamen.R;
import com.example.practicaexamen.data.model.TypeOffer;
import com.example.practicaexamen.data.repository.OfferRepository;
import com.example.practicaexamen.data.repository.TypeOfferRepository;

public class TypeOfferAdapter extends ArrayAdapter<TypeOffer> {

    public TypeOfferAdapter(@NonNull Context context, int resource) {
        super(context, resource, TypeOfferRepository.getInstance().getList());
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        ViewHolder viewHolder;

        if (convertView == null) {
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_offer, parent, false);
            viewHolder = new ViewHolder();

            viewHolder.tvName = convertView.findViewById(R.id.tvName);
            viewHolder.imgImage = convertView.findViewById(R.id.imgImage);
        } else
            viewHolder = (ViewHolder) convertView.getTag();

        viewHolder.tvName.setText(getItem(position).getName());
        switch (getItem(position).getType()) {
            case HOME:
                viewHolder.imgImage.setImageResource(R.drawable.home);
                break;
            case ELECTRONIC:
                viewHolder.imgImage.setImageResource(R.drawable.electronic);
                break;
            case GAMES:
                viewHolder.imgImage.setImageResource(R.drawable.games);
                break;
        }

        return convertView;
    }

    public class ViewHolder{
        public TextView tvName;
        public ImageView imgImage;
    }
}
