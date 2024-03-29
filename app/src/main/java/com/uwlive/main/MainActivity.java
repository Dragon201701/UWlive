package com.uwlive.main;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.snackbar.Snackbar;
import com.uwlive.main.logic.Market;
import com.uwlive.main.logic.User;
import com.uwlive.main.ui.home.HomeFragment;
import com.uwlive.main.ui.login.UserProfile;
import com.uwlive.main.ui.register.SignupActivity;

public class MainActivity extends AppCompatActivity {

    //public static User user;
    private AppBarConfiguration mAppBarConfiguration;
    View header;
    ImageButton userhead;
    private static String TAG = "MainActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //user = null;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
                Market.updatemarket();
                Log.d(TAG, User.LoginStatus.toString() + " " + User.UID);
                /*if(User.LoginStatus) {
                    Myhouses.update();
                    GalleryFragment.adapter.notifyDataSetChanged();
                }*/
                HomeFragment.adapter.notifyDataSetChanged();
            }
        });
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home, R.id.nav_gallery, R.id.nav_slideshow,
                R.id.nav_tools, R.id.nav_share, R.id.nav_send)
                .setDrawerLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);
        header=navigationView.getHeaderView(0);
        userhead = header.findViewById(R.id.userheadButton);
        userhead.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Finish the registration screen and return to the Login activity
                // TODO: Check if user is logged in and if not, navigate to LoginActivity. If logged in, navigate to User profile Activity.
                if(User.LoginStatus){
                    // User is logged in. Navigate to UserProfile Activity.
                    Intent intent = new Intent(getApplicationContext(), UserProfile.class);
                    startActivity(intent);
                    //finish();
                    overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
                }
                else{
                    // User is not logged in or not registered. Navigate to Sign in activity.
                    Intent intent = new Intent(getApplicationContext(), SignupActivity.class);
                    startActivity(intent);
                    //finish();
                    overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
                }
            }
        });


    }
    @Override
    public void onStart() {
        super.onStart();
        TextView username = header.findViewById(R.id.username);
        TextView useremail = header.findViewById(R.id.useremail);
        if(User.LoginStatus){
            username.setText(User.Username);
            useremail.setText(User.Email);
        }else{
            username.setText("Please log in or register.");
            useremail.setText(" ");
        }

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }
}
