package com.uwlive.main.logic;

import android.util.Log;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.List;

public class Myhouses {
    public static List<House> myhouses;
    private static String TAG = "MyHouses";
    private static FirebaseFirestore db;
    private static CollectionReference UserDB;
    private static CollectionReference MarketDB;
    //String TAG = "My house List";
    public Myhouses(){
        this.myhouses = new ArrayList<House>();
        update();
    }
    public static void myhousesinitialize(){
        db = FirebaseFirestore.getInstance();
        UserDB = db.collection("User");
        MarketDB = db.collection("HouseMarket");
    }
    public static void update(){
        if(myhouses != null) myhouses.clear();
        myhouses = new ArrayList<>();
        Log.d(TAG, User.UID);
        UserDB.document(User.UID).get()
                .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                    @Override
                    public void onSuccess(DocumentSnapshot documentSnapshot) {
                        if (documentSnapshot.exists()) {
                            Log.d(TAG, documentSnapshot.getId() + " => " + documentSnapshot.getData());
                            /*List myhouse = (List)documentSnapshot.getData().get("Myhouses");
                            Log.d(TAG, documentSnapshot.getId() + " => " + myhouse.toString());
                            for(int i = 0; i<myhouse.size(); i++){
                                updatehousebyid((String)myhouse.get(i));
                            }*/
                            Log.d(TAG, "Update: " + myhouses);

                            //GalleryFragment.adapter.notifyDataSetChanged();
                            //HomeFragment.updateMarketView();
                            //Globals.marketstatus = true; //Signal other that we have updated the market.
                            //GalleryFragment.adapter.notifyDataSetChanged();
                        }
                    }


                });
    }
    public static void updatehousebyid(String hid){
        MarketDB.document(hid) .get()
                .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                    @Override
                    public void onSuccess(DocumentSnapshot documentSnapshot) {
                        if (documentSnapshot.exists()) {
                            Log.d(TAG, "Updatehousebyid: " + documentSnapshot.getId() + " => " + documentSnapshot.getData());
                            House house = new House(documentSnapshot.getString("housename"),
                                    documentSnapshot.getString("description"),
                                    documentSnapshot.getDouble("price"),
                                    documentSnapshot.getId(),
                                    documentSnapshot.getString("ownerID"));
                            Log.d(TAG, house.toString());
                            myhouses.add(house);
                        }
                    }
                });
    }

}
