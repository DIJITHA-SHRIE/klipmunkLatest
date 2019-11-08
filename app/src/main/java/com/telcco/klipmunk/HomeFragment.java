package com.telcco.klipmunk;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.NavDestination;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import static com.telcco.klipmunk.UtilConstants.USER_ID;

public class HomeFragment extends AppCompatActivity {
    String userId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_fragment);
        BottomNavigationView navView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_home_folder,R.id.navigation_home_clip,R.id.navigation_home_search,R.id.navigation_home_notify,R.id.navigation_home_profile)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(navView, navController);

        userId =getIntent().getStringExtra(USER_ID);

        Bundle arguments = new Bundle();
        arguments.putString(UtilConstants.USER_ID,userId);
        navController.setGraph(navController.getGraph(),arguments);

        //Navigation.findNavController(navView).navigate(R.id.navigation_home_folder,arguments);


        navView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

                int id = menuItem.getItemId();
                switch (id){
                    case R.id.navigation_profile :
                        startActivity(new Intent(HomeFragment.this,UpdateProfile.class));
                        break;
                }


                return false;
            }
        });

        navController.addOnDestinationChangedListener((controller, destination, arguments1) -> {
            if(destination.getId() == R.id.navigation_home_folder){


            }

            if(destination.getId() == R.id.navigation_home_profile){
                startActivity(new Intent(HomeFragment.this,UpdateProfile.class));
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.view_articles,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }

}
