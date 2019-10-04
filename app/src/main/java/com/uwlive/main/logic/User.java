package com.uwlive.main.logic; /**
 * 
 */

/**
 * @author yz-li
 *
 */
import android.app.Activity;
import android.net.Uri;
import android.util.Log;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.uwlive.main.ui.login.LoginActivity;

import java.lang.reflect.Array;
import java.util.*;
import java.net.URL;
import java.util.concurrent.Executor;

public class User {

	private static FirebaseAuth mAuth;
	//private FirebaseAuth mAuth;
	/**
	 * 
	 */
	public static String Username = "";
	public static String Email = "";
	public static ArrayList<House> Myhouses = new ArrayList<House>();
	public static String Phone = "";
	public static Boolean LoginStatus = false;
	public static String UID = "";
	private static final String TAG = "User";
	public static Uri PhotoURL;
	public static boolean signout(){
		Username = "";
		Email = "";
		Myhouses.clear();
		Phone = "";
		LoginStatus = false;
		UID = "";
		return true;
	}
	/*
		This method returns the FirebaseUser instance of current user.
	 */
	public FirebaseUser getUser(){
		FirebaseUser currentUser = mAuth.getCurrentUser();
		return currentUser;
	}
	/*public static boolean signIn(Activity activity, String email, String password) {
		Log.d(TAG, "signIn:" + email);

		// [START sign_in_with_email]
		mAuth.signInWithEmailAndPassword(email, password)
				.addOnCompleteListener(activity, new OnCompleteListener<AuthResult>() {

					@Override
					public void onComplete(@NonNull Task<AuthResult> task) {
						if (task.isSuccessful()) {
							// Sign in success, update UI with the signed-in user's information
							Log.d(TAG, "signInWithEmail:success");
							User.LoginStatus = true;
							//FirebaseUser user = mAuth.getCurrentUser();
							//updateUI(user);
						} else {
							// If sign in fails, display a message to the user.
							Log.w(TAG, "signInWithEmail:failure", task.getException());
							/*Toast.makeText(EmailPasswordActivity.this, "Authentication failed.",
									Toast.LENGTH_SHORT).show();
							updateUI(null);
							User.LoginStatus = false;
						}


					}
				});
			return User.LoginStatus;
		}
		// [END sign_in_with_email]
	}*/



}
