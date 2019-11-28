package com.codrox.myapp.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.MenuItem;

import com.codrox.myapp.R;
import com.codrox.myapp.fragments.AccountFragment;
import com.codrox.myapp.fragments.CartFragment;
import com.codrox.myapp.fragments.HomeFragment;
import com.codrox.myapp.fragments.LibraryFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;


import static androidx.fragment.app.FragmentManager.POP_BACK_STACK_INCLUSIVE;

public class MainActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {

    private static final String BACK_STACK_ROOT_TAG = "root_fragment";

    BottomNavigationView navigationItemView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        navigationItemView = findViewById(R.id.navigation);
        navigationItemView.setOnNavigationItemSelectedListener(this);

        HomeFragment f = new HomeFragment();

        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(R.id.fragment_container, f);
        transaction.commit();

    }

    @Override
    public void onBackPressed() {
        FragmentManager manager = getSupportFragmentManager();

        if (manager.getBackStackEntryCount() > 0) {
            manager.popBackStack();
        } else {
            finish();
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        Fragment fragment;
        switch (menuItem.getItemId()) {
            case R.id.nav_home:
                fragment = new HomeFragment();
                loadFragment(fragment,"Home");
                break;
            case R.id.nav_cart:
                fragment = new CartFragment();
                loadFragment(fragment,"Cart");
                break;
            case R.id.nav_lib:
                fragment = new LibraryFragment();
                loadFragment(fragment,"Library");
                break;
            case R.id.nav_account:
                fragment = new AccountFragment();
                loadFragment(fragment,"Account");
                break;
        }
        return true;
    }

    private void loadFragment(Fragment fragment, String TAG) {
        // load fragment
        final FragmentManager manager = getSupportFragmentManager();
//        manager.popBackStack(BACK_STACK_ROOT_TAG, FragmentManager.POP_BACK_STACK_INCLUSIVE);
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(R.id.fragment_container, fragment, TAG);
        transaction.addToBackStack(BACK_STACK_ROOT_TAG);
        transaction.commit();
    }
}
