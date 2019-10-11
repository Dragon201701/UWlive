package com.uwlive.main.data;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.uwlive.main.R;

public class HouseViewHolder extends RecyclerView.ViewHolder {
    ImageView HouseImageView;
    TextView PriceView;
    TextView HousenameView;
    TextView DescriptionView;
    public HouseViewHolder(@NonNull View itemView) {
        super(itemView);
        this.HouseImageView = itemView.findViewById(R.id.houseimg_view);
        this.PriceView = itemView.findViewById(R.id.price_view);
        this.HousenameView = itemView.findViewById(R.id.housename_view);

    }
}
