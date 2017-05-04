package com.example.sebastian.demonsphinx;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;
    private SharedPreferences sharedPreferences;
    EditText text;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Main main = new Main();
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.frame, main).commit();
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        setTitle("DŻASTALK");
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);



        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        int id = item.getItemId();

        TextView text = (TextView) findViewById(R.id.info);
        if (id == R.id.connections) {
            setTitle("Połączenia");
            Intent intent=new Intent(MainActivity.this,ConnectionView.class);
            startActivity(intent);
        }
        if (id == R.id.addconections) {
            setTitle("Dodaj Połączenie");

            Intent intent=new Intent(MainActivity.this,AddConection.class);
            startActivity(intent);
        }else if (id == R.id.main) {
            setTitle("Panel Główny");
            Main main = new Main();
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.
                    setCustomAnimations(R.anim.screen_anim, R.anim.screen_anim).
                    replace(R.id.frame, main,"Main").commit();

        } else if (id == R.id.voice) {
            setTitle("Ustawienia Głosowe");

            Intent intent=new Intent(MainActivity.this,DisplayOptions.class);
            startActivity(intent);
        } else if (id == R.id.about) {
            setTitle("O Aplikacji");
            About about = new About();
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.
                    setCustomAnimations(R.anim.screen_anim, R.anim.screen_anim).
                    replace(R.id.frame, about).commit();

        } else if (id == R.id.instruction) {
            setTitle("Pomoc");
            Instructions instructions = new Instructions();
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.
                    setCustomAnimations(R.anim.screen_anim, R.anim.screen_anim).
                    replace(R.id.frame, instructions).commit();

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


    public void Back(MenuItem item) {
        Main main = new Main();

        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.
                setCustomAnimations(R.anim.screen_anim, R.anim.screen_anim).
                replace(R.id.frame, main).commit();
    }

    @Override
    protected void onDestroy() {
        sharedPreferences= getSharedPreferences("Settings", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor= sharedPreferences.edit();
        editor.putString("IP"," ");
        editor.commit();
        super.onDestroy();
    }


}
