package app.doordash.demo.util;

import android.app.Activity;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import app.doordash.demo.R;

/**
 * Created by Vladi on 2/22/17.
 */

public class FragmentLoader {

    public static void loadFragment(Fragment fragment, AppCompatActivity activity, int id){ // load fragment
        FragmentTransaction fragmentTransaction = activity.getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(id,fragment);
        fragmentTransaction.commit();
    }

    public static void addFragment(Fragment fragment, AppCompatActivity activity, int id) { // load fragment
        FragmentTransaction fragmentTransaction = activity.getSupportFragmentManager().beginTransaction();
        fragmentTransaction.add(id, fragment);
        fragmentTransaction.commit();
    }

    public static void showFragment(Fragment fragment, AppCompatActivity activity) { // show fragment
        FragmentTransaction fragmentTransaction = activity.getSupportFragmentManager().beginTransaction();
        fragmentTransaction.show(fragment);
        fragmentTransaction.commit();
    }

    public static void hideFragment(Fragment fragment, AppCompatActivity activity) { // hide fragment
        FragmentTransaction fragmentTransaction = activity.getSupportFragmentManager().beginTransaction();
        fragmentTransaction.hide(fragment);
        fragmentTransaction.commit();
    }


    public static void loadFragment(Fragment fragment, FragmentActivity activity, int id){ // load fragment
        FragmentTransaction fragmentTransaction = activity.getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(id,fragment);
        fragmentTransaction.commit();
    }
}
