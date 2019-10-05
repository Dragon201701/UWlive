package com.uwlive.main.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ScrollView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.uwlive.main.R;

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;
    ScrollView scrollview;
    LinearLayout horizontal;
    LinearLayout vertical1;
    LinearLayout vertical2;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                ViewModelProviders.of(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home, container, false);
        //final TextView textView = root.findViewById(R.id.text_home);
        /*homeViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });*/
        scrollview = root.findViewById(R.id.scroll_view);
        horizontal = scrollview.findViewById(R.id.horizontal_layout);
        vertical1 = horizontal.findViewById(R.id.horizontal_layout);
        vertical2 = horizontal.findViewById(R.id.horizontal_layout);
        updateMarketView();
        return root;
    }
    public void updateMarketView(){
        //vertical1.addView(new HouseView());
    }
}