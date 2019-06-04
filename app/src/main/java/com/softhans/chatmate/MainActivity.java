package com.softhans.chatmate;

import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {

    private Toolbar mtoolbar;
    private ViewPager myViewPAger;
    private TabLayout myTabLayout;
    private TabsAccessorAdapter myTabAccessorAdapter;

    private FirebaseUser currentUser;
    private FirebaseAuth mAtuh;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        mAtuh = FirebaseAuth.getInstance();
        currentUser = mAtuh.getCurrentUser();

        mtoolbar = (Toolbar) findViewById(R.id.main_page_toolbar);
        setSupportActionBar(mtoolbar);
        getSupportActionBar().setTitle("ChatMate");


        myViewPAger = (ViewPager) findViewById(R.id.main_tabs_pager);
        myTabAccessorAdapter = new TabsAccessorAdapter(getSupportFragmentManager());
        myViewPAger.setAdapter(myTabAccessorAdapter);

        myTabLayout = (TabLayout) findViewById(R.id.main_tabs);
        myTabLayout.setupWithViewPager(myViewPAger);
    }

    @Override
    protected void onStart() {

        super.onStart();

        if(currentUser ==null)
        {
            sendUserToLoginActivity();
        }
    }




    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);

        getMenuInflater().inflate(R.menu.options_menu, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
         super.onOptionsItemSelected(item);

         if(item.getItemId() == R.id.main_logout_option)
         {
            mAtuh.signOut();
            sendUserToLoginActivity();

         }

        if(item.getItemId() == R.id.main_settings_option)
        {
            sendUserToSettingsActivity();
        }

        if(item.getItemId() == R.id.main_find_friends_option)
        {

        }

        return true;
    }


    private void sendUserToLoginActivity() {
        Intent LoginIntent = new Intent(MainActivity.this,LoginActivity.class);
        startActivity(LoginIntent);
    }

    private void sendUserToSettingsActivity() {
        Intent SettingsIntent = new Intent(MainActivity.this,SettingsActivity.class);
        startActivity(SettingsIntent);
    }

}
