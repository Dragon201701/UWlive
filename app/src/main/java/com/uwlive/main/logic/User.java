package com.uwlive.main.logic; /**
 * 
 */

/**
 * @author yz-li
 *
 */
import android.net.Uri;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;

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




}
