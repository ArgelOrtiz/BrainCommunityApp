package com.example.braincommunity.Drawer;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.braincommunity.Post.CreatePostActivity;
import com.example.braincommunity.Post.PostRecyclerViewAdapter;
import com.example.braincommunity.Profile.LoginActivity;
import com.example.braincommunity.Profile.ProfileActivity;
import com.example.braincommunity.Post.MyPostActivity;
import com.example.braincommunity.R;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    DrawerLayout drawer;
    NavigationView navigationView;
    RecyclerView postRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("Home");


        Toolbar toolbar =  findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();


        navigationView = findViewById(R.id.nav_view);
        postRecyclerView    = findViewById(R.id.postMainRecyclerView);

        postRecyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext(),LinearLayoutManager.VERTICAL, false));
        navigationView.setNavigationItemSelectedListener(this);


        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());

        if (preferences.getString("key","").equalsIgnoreCase("yes")) navigationView.inflateMenu(R.menu.activity_main_login);

        else navigationView.inflateMenu(R.menu.activity_main_whitout_login);



        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i = new Intent(getApplicationContext(), CreatePostActivity.class);
                startActivity(i);
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
            }
        });

        getData();

    }

    protected void getData(){

        @SuppressLint("UseSparseArrays") Map<Integer,Map<String,String>> post = new HashMap<>();


        Map<String,String> currencyPost1 = new HashMap<String,String>();
        Map<String,String> currencyPost2 = new HashMap<String,String>();
        Map<String,String> currencyPost3 = new HashMap<String,String>();
        Map<String,String> currencyPost4 = new HashMap<String,String>();

        currencyPost1.put("title","Funciones php");
        currencyPost1.put("description","Descripcion de como crear y utilizar una funcion de php en conjunto con html");
        currencyPost1.put("status","0");

        currencyPost2.put("title","¿como hacer una aplicacion android?");
        currencyPost2.put("description","_Descripcion__Descripcion__Descripcion__Descripcion__Descripcion__Descripcion__Descripcion__Descripcion__Descripcion__Descripcion__Descripcion__Descripcion_");
        currencyPost2.put("status","0");

        currencyPost3.put("title","Como calcular impuestos");
        currencyPost3.put("description","_Descripcion__Descripcion__Descripcion__Descripcion__Descripcion__Descripcion__Descripcion__Descripcion__Descripcion__Descripcion__Descripcion__Descripcion__Descripcion__Descripcion_");
        currencyPost3.put("status","1");

        currencyPost4.put("title","Cual es el numero del sat");
        currencyPost4.put("description","_Descripcion__Descripcion__Descripcion__Descripcion__Descripcion__Descripcion__Descripcion__Descripcion__Descripcion__Descripcion__Descripcion__Descripcion__Descripcion__Descripcion__Descripcion_");
        currencyPost4.put("status","1");


        post.put(0,currencyPost1);
        post.put(1,currencyPost2);
        post.put(2,currencyPost3);
        post.put(3,currencyPost4);

        post.put(4,currencyPost1);
        post.put(5,currencyPost2);
        post.put(6,currencyPost3);
        post.put(7,currencyPost4);


        postRecyclerView.setAdapter(new PostRecyclerViewAdapter(getApplicationContext(),post));
    }

    @Override
    protected void onResume() {
        super.onResume();
        navigationView.getMenu().getItem(0).setChecked(true);
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            Toast.makeText(getApplicationContext(),"texto",Toast.LENGTH_LONG).show();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();


        if (id == R.id.nav_home) {

        } else if (id == R.id.nav_Post) {
            Intent i = new Intent(getApplicationContext(), MyPostActivity.class);
            startActivity(i);

        } else if (id == R.id.nav_profile) {
            Intent i = new Intent(getApplicationContext(), ProfileActivity.class);
            startActivity(i);

        } else if (id == R.id.nav_logout) {

            SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
            SharedPreferences.Editor editor = preferences.edit();

            editor.putString("key","no");
            editor.apply();

            Intent i = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(i);
            finish();

        } else if (id == R.id.nav_login){

            Intent i = new Intent(getApplicationContext(), LoginActivity.class);
            startActivity(i);

        }


        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


    @Override
    public void onBackPressed() {

        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }
}
