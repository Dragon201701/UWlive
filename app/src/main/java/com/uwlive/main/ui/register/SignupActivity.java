package com.uwlive.main.ui.register;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.uwlive.main.MainActivity;
import com.uwlive.main.R;
import com.uwlive.main.logic.House;
import com.uwlive.main.logic.User;
import com.uwlive.main.ui.login.LoginActivity;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SignupActivity extends AppCompatActivity {
    private static final String TAG = "SignupActivity";
    private FirebaseAuth mAuth;
    FirebaseFirestore db; //Fire Store Instance
    ProgressDialog progressDialog = null;
    @BindView(R.id.register_name) EditText _nameText;
    @BindView(R.id.register_email) EditText _emailText;
    @BindView(R.id.register_phone) EditText _mobileText;
    @BindView(R.id.register_password) EditText _passwordText;
    @BindView(R.id.register_passwordreenter) EditText _reEnterPasswordText;
    @BindView(R.id.register_button) Button _signupButton;
    @BindView(R.id.link_login) TextView _loginLink;

    /*
        Initialize Progress Dialog, the red pop up view shows system is working.
     */
    public void initProgressDialog(){
        progressDialog = new ProgressDialog(SignupActivity.this,
                R.style.AppTheme_Dark_Dialog);
        progressDialog.setIndeterminate(true);
        progressDialog.setMessage("Creating Account...");
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initProgressDialog();
        setContentView(R.layout.activity_signup);
        ButterKnife.bind(this);
        mAuth = FirebaseAuth.getInstance();

        _signupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signup();
            }
        });

        _loginLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Finish the registration screen and return to the Login activity
                Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(intent);
                finish();
                overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
            }
        });
        // Initial Firestore instance
        db = FirebaseFirestore.getInstance();
        // Add back button
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
    public boolean onOptionsItemSelected (MenuItem item){
        int id = item.getItemId();
        if(id == android.R.id.home){
            this.finish();
        }
        return super.onOptionsItemSelected(item);
    }

    public void signup() {
        Log.d(TAG, "Signup");
        if (!validate()) {
            onSignupFailed();
            //return false;
        }
        _signupButton.setEnabled(false);
        progressDialog.show();
        String email = _emailText.getText().toString();
        String password = _passwordText.getText().toString();
        String name = _nameText.getText().toString();
        String mobile = _mobileText.getText().toString();


        createAccount();



        new android.os.Handler().postDelayed(
                new Runnable() {
                    public void run() {
                        // On complete call either onSignupSuccess or onSignupFailed
                        // depending on success
                        if(User.LoginStatus){
                            onSignupSuccess();
                        }
                        else{
                            onSignupFailed();
                        }
                        //progressDialog.dismiss();
                    }
                }, 3000);
    }


    public void onSignupSuccess() {

        User.Username = _nameText.getText().toString();;
        User.Email = _emailText.getText().toString();;
        User.Phone = _mobileText.getText().toString();;
        User.UID = mAuth.getUid();
        User.LoginStatus = true;
        CollectionReference users = db.collection("User");
        Map<String, Object> newuser = new HashMap<>();
        List<String> myhouses = null;
        newuser.put("Username", User.Username);
        newuser.put("Email",  User.Email);
        newuser.put("Phone", User.Phone);
        newuser.put("Myhouses", myhouses);
        users.document(User.UID).set(newuser);
        progressDialog.dismiss();
        _signupButton.setEnabled(true);
        setResult(RESULT_OK, null);
        startActivity(new Intent(getApplicationContext(), MainActivity.class));
        overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
        finish();
    }

    public void onSignupFailed() {
        progressDialog.dismiss();
        Toast.makeText(getBaseContext(), "Signup failed", Toast.LENGTH_LONG).show();
        _signupButton.setEnabled(true);
    }

    public boolean validate() {
        boolean valid = true;

        String name = _nameText.getText().toString();
        //String address = _addressText.getText().toString();
        String email = _emailText.getText().toString();
        String mobile = _mobileText.getText().toString();
        String password = _passwordText.getText().toString();
        String reEnterPassword = _reEnterPasswordText.getText().toString();

        if (name.isEmpty() || name.length() < 3) {
            _nameText.setError("at least 3 characters");
            valid = false;
        } else {
            _nameText.setError(null);
        }

        if (email.isEmpty() || !android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            _emailText.setError("enter a valid email address");
            valid = false;
        } else {
            _emailText.setError(null);
        }

        if (mobile.isEmpty() || mobile.length()!=10) {
            _mobileText.setError("Enter Valid Mobile Number");
            valid = false;
        } else {
            _mobileText.setError(null);
        }

        if (password.isEmpty() || password.length() < 4 || password.length() > 10) {
            _passwordText.setError("between 4 and 10 alphanumeric characters");
            valid = false;
        } else {
            _passwordText.setError(null);
        }

        if (reEnterPassword.isEmpty() || reEnterPassword.length() < 4 || reEnterPassword.length() > 10 || !(reEnterPassword.equals(password))) {
            _reEnterPasswordText.setError("Password Do not match");
            valid = false;
        } else {
            _reEnterPasswordText.setError(null);
        }

        return valid;
    }

    /*
        Call Firebase Authenticate with email and password. If success, setup user info and update firestore.
     */
    private void createAccount() {
        String email = _emailText.getText().toString();;
        String password = _passwordText.getText().toString();
        Log.d(TAG, "createAccount:" + email);
        // [START create_user_with_email]
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "createUserWithEmail:success");
                            User.LoginStatus = true;
                            Toast.makeText(SignupActivity.this, "Welcome, "+_nameText.getText().toString(),
                                    Toast.LENGTH_SHORT).show();
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "createUserWithEmail:failure", task.getException());
                            User.LoginStatus = false;
                            Toast.makeText(SignupActivity.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                        }

                    }
                });
    }
}