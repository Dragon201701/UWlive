package com.uwlive.main.logic;

import android.util.Log;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.uwlive.main.ui.home.HomeFragment;

import java.util.ArrayList;
import java.util.List;


// [START fs_include_dependencies]
// [END fs_include_dependencies]
public class Market {
    public static List<House> market;

    private static String TAG = "HoseMarket";
    private static FirebaseFirestore db;
    private static CollectionReference MarketDB;
    public static void marketinitialize(){
        db = FirebaseFirestore.getInstance();
        MarketDB = db.collection("HouseMarket");
    }
    public static void updatemarket(){
        if(market != null) market.clear();
        market = new ArrayList<>();
        MarketDB
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                Log.d(TAG, document.getId() + " => " + document.getData());
                                updatehouse(document);
                                //HomeFragment.updateMarketView();
                            }
                            Globals.marketstatus = true; //Signal other that we have updated the market.
                            HomeFragment.adapter.notifyDataSetChanged();
                        } else {
                            Log.d(TAG, "Error getting documents: ", task.getException());
                        }
                    }
                });
    }
    /*
        Get house info and add house to the market when initialize or update.
     */
    public static void updatehouse(QueryDocumentSnapshot document){
        House house = new House(document.getString("housename"), document.getString("description"), document.getDouble("price") ,document.getId(), document.getString("ownerID"));
        Log.d(TAG, "Adding new house: " + house.toString());
        market.add(house);
    }



}
