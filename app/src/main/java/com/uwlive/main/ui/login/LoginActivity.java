package com.uwlive.main.ui.login;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.text.TextUtils;
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
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.FirebaseFirestore;
import com.uwlive.main.MainActivity;
import com.uwlive.main.R;
import com.uwlive.main.logic.Myhouses;
import com.uwlive.main.logic.User;
import com.uwlive.main.ui.register.SignupActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

public class LoginActivity extends AppCompatActivity {

    //private LoginViewModel loginViewModel;
    private FirebaseAuth mAuth;
    private static final int REQUEST_SIGNUP = 0;
    private static final String TAG = "EmailPassword";
    FirebaseFirestore db; //Fire Store Instance
    ProgressDialog progressDialog;

    @BindView(R.id.login_email) EditText emailEditText;
    @BindView(R.id.login_password) EditText passwordEditText;
    @BindView(R.id.login_button) Button loginButton;
    @BindView(R.id.link_signup) TextView RegisterLink;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mAuth = FirebaseAuth.getInstance();
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        db = FirebaseFirestore.getInstance();
        initProgressDialog();
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //loadingProgressBar.setVisibility(View.VISIBLE);
                //loginViewModel.login(usernameEditText.getText().toString(), passwordEditText.getText().toString());
                if(validateForm())
                    signIn();
            }
        });
        RegisterLink.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // Start the Signup activity
                Intent intent = new Intent(getApplicationContext(), SignupActivity.class);
                startActivityForResult(intent, REQUEST_SIGNUP);

                overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
                finish();
            }
        });

        StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder()
                .detectDiskReads().detectDiskWrites().detectNetwork()
                .penaltyLog().build());
        StrictMode.setVmPolicy(new StrictMode.VmPolicy.Builder()
                .detectLeakedSqlLiteObjects().penaltyLog().penaltyDeath()
                .build());



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

    public void initProgressDialog(){
        progressDialog = new ProgressDialog(LoginActivity.this,
                R.style.AppTheme_Dark_Dialog);
        progressDialog.setIndeterminate(true);
        progressDialog.setMessage("Logging In...");
    }
    @Override
    public void onStart() {
        super.onStart();
    }


    public void onLoginSuccess() {
        loginButton.setEnabled(true);
        //User.Email = emailEditText.getText().toString();
        User.UID = mAuth.getUid();
        User.Email = mAuth.getCurrentUser().getEmail();
        User.LoginStatus = true;
        User.Username = "";
        setResult(RESULT_OK, null);
        progressDialog.dismiss();
        startActivity(new Intent(getApplicationContext(), MainActivity.class));
        overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
        User.LoginStatus = true;
        //UserProfile.updateProfile();
        Myhouses.myhousesinitialize();
        Myhouses.update();
        finish();
    }
    public void onLoginFailed() {
        Toast.makeText(getBaseContext(), "Login failed", Toast.LENGTH_LONG).show();
        loginButton.setEnabled(true);
    }
    private void signIn() {
        String email = emailEditText.getText().toString();
        String password = passwordEditText.getText().toString();
        Log.d(TAG, "signIn:" + email);
        /*DocumentReference docRef = db.collection("User").document(User.UID);
        docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot document = task.getResult();
                    if (document.exists()) {
                        Log.d(TAG, "DocumentSnapshot data: " + document.getData().get("Username"));
                        User.Username = (String)document.getData().get("Username");
                    } else {
                        Log.d(TAG, "No such document");
                    }
                } else {
                    Log.d(TAG, "get failed with ", task.getException());
                }
            }
        });*/
        // Setup Progress Dialog
        loginButton.setEnabled(false);
        progressDialog.show();
        new android.os.Handler().postDelayed(
                new Runnable() {
                    public void run() {
                            if(User.LoginStatus)
                                onLoginSuccess();
                            else
                                onLoginFailed();
                    }
                }, 3000);
        // [START sign_in_with_email]
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "signInWithEmail:success");
                            User.LoginStatus = true;
                            //User.Username = mAuth.getCurrentUser().getDisplayName();
                            User.UID = mAuth.getUid();
                            User.Email = mAuth.getCurrentUser().getEmail();
                            // TODO: Test FireStore get data


                            DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference();
                            Toast.makeText(LoginActivity.this, "Welcome, "+ User.Username,
                                    Toast.LENGTH_SHORT).show();

                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "signInWithEmail:failure", task.getException());
                            Toast.makeText(LoginActivity.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                            User.LoginStatus = false;
                        }
                    }
                });
        // [END sign_in_with_email]
    }
    private boolean validateForm() {
        boolean valid = true;

        String email = emailEditText.getText().toString();
        if (TextUtils.isEmpty(email)) {
            emailEditText.setError("Required.");
            valid = false;
        } else {
            emailEditText.setError(null);
        }

        String password = passwordEditText.getText().toString();
        if (TextUtils.isEmpty(password)) {
            passwordEditText.setError("Required.");
            valid = false;
        } else {
            passwordEditText.setError(null);
        }

        return valid;
    }
    private void signOut() {
        mAuth.signOut();
    }

}
