package com.uwlive.main.ui.login;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.uwlive.main.MainActivity;
import com.uwlive.main.R;
import com.uwlive.main.logic.User;

public class UserProfile extends AppCompatActivity {

    private static TextView view_username;
    private static TextView view_email;
    private static TextView view_phone;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_profile_activity);
        if (savedInstanceState == null) {
            /*getSupportFragmentManager().beginTransaction()
                    .replace(R.id.container, UserProfileFragment.newInstance())
                    .commitNow();*/
        }
        //ActionBar actionBar = getActionBar();
        //actionBar.setDisplayHomeAsUpEnabled(true);

        view_username = findViewById(R.id.username);
        //view_username.setText("");
        view_email = findViewById(R.id.email);
        //view_username.setText(User.Email);
        view_phone = findViewById(R.id.phone);
        //view_username.setText(User.Phone);
        final Button signout = findViewById(R.id.signout);
        signout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                signout();
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
                finish();
                overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
            }
        });
    }
    public void signout(){
        User.signout();
    }
    public static void updateProfile(){
        view_username.setText(User.Username);
        view_email.setText(User.Email);
        view_phone.setText(User.Phone);
    }
}
