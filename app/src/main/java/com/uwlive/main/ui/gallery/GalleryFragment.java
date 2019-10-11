package com.uwlive.main.ui.gallery;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.uwlive.main.R;
import com.uwlive.main.logic.Myhouses;
import com.uwlive.main.logic.User;
import com.uwlive.main.ui.home.MarketAdaptor;

import static com.uwlive.main.logic.Myhouses.myhousesinitialize;

public class GalleryFragment extends Fragment {

    private GalleryViewModel galleryViewModel;
    public static RecyclerView.Adapter adapter;
    public static RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        galleryViewModel =
                ViewModelProviders.of(this).get(GalleryViewModel.class);
        View root = inflater.inflate(R.layout.fragment_gallery, container, false);
        //final TextView textView = root.findViewById(R.id.text_gallery);
        galleryViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                //textView.setText(s);
            }
        });
        if(User.LoginStatus) myhousesinitialize();
        recyclerView = root.findViewById(R.id.myhouses_recycler_view);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        adapter = new MarketAdaptor(this.getContext(),1);
        recyclerView.setAdapter(adapter);
        if(User.LoginStatus) Myhouses.update();
        adapter.notifyDataSetChanged();
        return root;
    }
}