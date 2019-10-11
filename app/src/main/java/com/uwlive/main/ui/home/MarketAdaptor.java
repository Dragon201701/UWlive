package com.uwlive.main.ui.home;

import android.content.Context;
import android.graphics.drawable.PictureDrawable;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestBuilder;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.uwlive.main.R;
import com.uwlive.main.logic.House;
import com.uwlive.main.logic.Market;
import com.uwlive.main.logic.Myhouses;
import com.uwlive.main.logic.User;


public class MarketAdaptor extends RecyclerView.Adapter {
    Context context;
    int type;
    private RequestBuilder<PictureDrawable> requestBuilder;
    //HouseHolder householder;
    public class HouseHolder extends RecyclerView.ViewHolder {
        ImageView HouseImageView;
        TextView PriceView;
        TextView HousenameView;
        TextView DescriptionView;
        public HouseHolder(View itemView) {
            super(itemView);

            this.HouseImageView = itemView.findViewById(R.id.houseimg_view);
            this.PriceView = itemView.findViewById(R.id.price_view);
            this.HousenameView = itemView.findViewById(R.id.housename_view);
            this.DescriptionView = itemView.findViewById(R.id.description_view);
        }
    }
    public MarketAdaptor(Context context, int type){
        this.context = context;
        this.type = type; // 0 Market Adapter, 1 Myhouse Adapter
        if(this.type == 0){
            Market.marketinitialize();
            Market.updatemarket();
        }
        else if(User.LoginStatus){
            Myhouses.myhousesinitialize();
            Myhouses.update();
        }
        //while(!Globals.marketstatus); //Wait until the market is initialized;
    }
    @NonNull
    @Override
    public MarketAdaptor.HouseHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.card_house, parent, false);
        return new HouseHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        HouseHolder householder = (HouseHolder)holder;
        House house;
        if(this.type == 0)
            house = Market.market.get(position);
        else
            house = Myhouses.myhouses.get(position);
        String uribase = "gs://uwlivewell-74840.appspot.com/houseimg/";
        String hid = house.getHID();
        String uriaddr = uribase + hid + ".jpg";
        Uri uri = Uri.parse(uriaddr);
        Log.d("uri:",uriaddr.toString());

        FirebaseStorage storage = FirebaseStorage.getInstance();
        StorageReference storageRef = storage.getReference();
        StorageReference pathReference = storageRef.child("houseimg/"+house.getHID()+".jpg");
        String url = "https://firebasestorage.googleapis.com/v0/b/uwlivewell-74840.appspot.com/o/Lucky.jpg?alt=media&token=f7cff3f2-248b-43ba-ba60-ce5071b2e520";

        Glide.with(context)
                .load(url)
                .into(householder.HouseImageView);
        //householder.HouseImageView.setImageURI(uri);
        householder.PriceView.setText(Double.toString(house.getPrice()));
        householder.DescriptionView.setText(house.getdescription());
        householder.HousenameView.setText(house.getHousename());
    }
    @Override
    public int getItemCount() {
        if(this.type == 0)
            return Market.market.size();
        else if(User.LoginStatus)
            return Myhouses.myhouses.size();
        else
            return 0;
    }

}
