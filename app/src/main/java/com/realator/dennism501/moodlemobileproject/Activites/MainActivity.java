package com.realator.dennism501.moodlemobileproject.Activites;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.messaging.FirebaseMessaging;
import com.realator.dennism501.moodlemobileproject.Fragments.CalenderFragment;
import com.realator.dennism501.moodlemobileproject.Fragments.ChatFragment;
import com.realator.dennism501.moodlemobileproject.Fragments.ContactsFragment;
import com.realator.dennism501.moodlemobileproject.Fragments.PrivateFilesFragment;
import com.realator.dennism501.moodlemobileproject.Fragments.SiteHomeFragment;
import com.realator.dennism501.moodlemobileproject.R;
import com.realator.dennism501.moodlemobileproject.app.Config;
import com.realator.dennism501.moodlemobileproject.util.NotificationUtils;

public class MainActivity extends AppCompatActivity {

    private DrawerLayout drawerLayout;
    private NavigationView navigationView;
    private Toolbar toolbar;
    private TextView txtRegId;
    private ActionBarDrawerToggle drawerToggle;
    private SiteHomeFragment siteHomeFragment = new SiteHomeFragment();

    private static final String TAG = MainActivity.class.getSimpleName();
    private BroadcastReceiver mRegistrationBroadcastReceiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        drawerLayout = (DrawerLayout) findViewById(R.id.drawerlayoutid);
        navigationView = (NavigationView) findViewById(R.id.navigationviewid);
        drawerToggle = setupDrawerToggle();
        setSupportActionBar(toolbar);
        //setups toggle button
        drawerLayout.addDrawerListener(drawerToggle);
        setupDrawerContent(navigationView);

        /*TelephonyManager tManager = (TelephonyManager)getSystemService(Context.TELEPHONY_SERVICE);
        String uuid = tManager.getDeviceId();

        Log.d("device uuid", uuid);*/
        mRegistrationBroadcastReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {

                // checking for type intent filter
                if (intent.getAction().equals(Config.REGISTRATION_COMPLETE)) {
                    // gcm successfully registered
                    // now subscribe to `global` topic to receive app wide notifications
                    FirebaseMessaging.getInstance().subscribeToTopic(Config.TOPIC_GLOBAL);

                    displayFirebaseRegId();

                } else if (intent.getAction().equals(Config.PUSH_NOTIFICATION)) {
                    // new push notification is received

                    String message = intent.getStringExtra("message");

                    Toast.makeText(getApplicationContext(), "Push notification: " + message, Toast.LENGTH_LONG).show();

                }
            }
        };

        displayFirebaseRegId();
        loadFirstFragment();
        colorNavIcons();


    }


    // Fetches reg id from shared preferences
    // and displays on the screen
    private void displayFirebaseRegId() {
        SharedPreferences pref = getApplicationContext().getSharedPreferences(Config.SHARED_PREF, 0);
        String regId = pref.getString("regId", null);

        Log.e(TAG, "Firebase reg id: " + regId);
/*
        if (!TextUtils.isEmpty(regId))
            txtRegId.setText("Firebase Reg Id: " + regId);
        else
            txtRegId.setText("Firebase Reg Id is not received yet!");*/
    }

    @Override
    protected void onResume() {
        super.onResume();

        // register GCM registration complete receiver
        LocalBroadcastManager.getInstance(this).registerReceiver(mRegistrationBroadcastReceiver,
                new IntentFilter(Config.REGISTRATION_COMPLETE));

        // register new push message receiver
        // by doing this, the activity will be notified each time a new message arrives
        LocalBroadcastManager.getInstance(this).registerReceiver(mRegistrationBroadcastReceiver,
                new IntentFilter(Config.PUSH_NOTIFICATION));

        // clear the notification area when the app is opened
        NotificationUtils.clearNotifications(getApplicationContext());
    }

    @Override
    protected void onPause() {
        super.onPause();

        LocalBroadcastManager.getInstance(this).unregisterReceiver(mRegistrationBroadcastReceiver);
    }

    private ActionBarDrawerToggle setupDrawerToggle() {
        return new ActionBarDrawerToggle(getParent(), drawerLayout, toolbar, R.string.drawer_open, R.string.drawer_close);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (drawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);

        drawerToggle.syncState();
    }

    private void setupDrawerContent(NavigationView navigationView1) {

        navigationView1.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {
                selectDrawerItem(item);
                return true;
            }
        });
    }

    private void colorNavIcons() {
        // color of the va drawar icons
        try {
            navigationView.setItemIconTintList(null);
        } catch (NullPointerException e) {

            Log.d("e", e.getLocalizedMessage());

        }
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        drawerToggle.onConfigurationChanged(newConfig);

    }

    private void selectDrawerItem(MenuItem menuItem) {
        Fragment fragment = null;

        switch (menuItem.getItemId()){

            case R.id.site_home:
                fragment = new SiteHomeFragment();
                break;
            case R.id.calender:
                fragment = new CalenderFragment();
                break;
            case R.id.my_files:
                fragment = new PrivateFilesFragment();
                break;
            case R.id.chat:
                fragment = new ChatFragment();
                break;
            case R.id.my_courses:
                fragment = new ContactsFragment();

        }

        if (fragment != null) {

            removeFragment();
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.add(R.id.flContent, fragment);
            fragmentTransaction.commitAllowingStateLoss();


        }

        menuItem.setChecked(true);
        setTitle(menuItem.getTitle());
        drawerLayout.closeDrawers();

    }

    private void removeFragment() {

        try {
            getSupportFragmentManager()
                    .beginTransaction()
                    .remove(getSupportFragmentManager()
                            .findFragmentById(R.id.flContent))
                    .commitAllowingStateLoss();
            System.gc();


        } catch (NullPointerException e) {
            System.out.println(e);
        }
    }

    public void loadFirstFragment(){


        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.flContent, siteHomeFragment);
        fragmentTransaction.commitAllowingStateLoss();

    }
}
