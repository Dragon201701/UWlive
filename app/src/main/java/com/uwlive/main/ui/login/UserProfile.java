package com.uwlive.main.ui.login;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ActionBar;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.material.snackbar.Snackbar;
import com.uwlive.main.MainActivity;
import com.uwlive.main.R;
import com.uwlive.main.logic.User;
import com.uwlive.main.ui.login.ui.userprofile.UserProfileFragment;
import org.w3c.dom.Text;

public class UserProfile extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_profile_activity);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.container, UserProfileFragment.newInstance())
                    .commitNow();
        }
        //ActionBar actionBar = getActionBar();
        //actionBar.setDisplayHomeAsUpEnabled(true);

        TextView view_username = findViewById(R.id.username);
        view_username.setText(User.Username);
        TextView view_email = findViewById(R.id.email);
        view_username.setText(User.Email);
        TextView view_phone = findViewById(R.id.phone);
        view_username.setText(User.Phone);
        Button signout = findViewById(R.id.signout);
        signout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
                finish();
                overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
            }
        });
    }
    public boolean signout(){
        return true;
    }
}
