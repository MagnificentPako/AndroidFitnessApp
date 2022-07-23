package de.rub.cs.selab22.a14.navigation;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;

import android.content.Context;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.View;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import de.rub.cs.selab22.a14.R;
import de.rub.cs.selab22.a14.settings.I18nAppCompatActivity;

public class HostActivity extends I18nAppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_host);

        NavController nc = Navigation.findNavController(this, R.id.nav_host_fragment);
        BottomNavigationView bnv = findViewById(R.id.bottom_nav);

        nc.addOnDestinationChangedListener((navController, navDestination, bundle) -> {
            if(bnv.getMenu().findItem(navDestination.getId()) == null) {
                bnv.setVisibility(View.GONE);
            } else {
                bnv.setVisibility(View.VISIBLE);
            }
        });

        NavigationUI.setupWithNavController(bnv, nc);
    }
}