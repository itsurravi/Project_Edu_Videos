package com.codrox.myapp.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import com.codrox.myapp.R;
import com.codrox.myapp.fragments.AccountFragment;
import com.codrox.myapp.fragments.CartFragment;
import com.codrox.myapp.fragments.HomeFragment;
import com.codrox.myapp.fragments.LibraryFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;


public class MainActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {

    public static final String BACK_STACK_ROOT_TAG = "root_fragment";

    public static BottomNavigationView navigationItemView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        navigationItemView = findViewById(R.id.navigation);
        navigationItemView.setOnNavigationItemSelectedListener(this);

        HomeFragment f = new HomeFragment();
        loadFragment(f, HomeFragment.class.getSimpleName());

    }

    @Override
    public void onBackPressed() {
        FragmentManager manager = getSupportFragmentManager();
        if (manager.getBackStackEntryCount() > 1) {
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
                loadFragment(fragment, HomeFragment.class.getSimpleName());
                break;
            case R.id.nav_cart:
                fragment = new CartFragment();
                loadFragment(fragment, CartFragment.class.getSimpleName());
                break;
            case R.id.nav_lib:
                fragment = new LibraryFragment();
                loadFragment(fragment, LibraryFragment.class.getSimpleName());
                break;
            case R.id.nav_account:
                fragment = new AccountFragment();
                loadFragment(fragment, AccountFragment.class.getSimpleName());
                break;
        }
        return true;
    }

    private void loadFragment(Fragment fragment, String TAG) {

        FragmentManager mFragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = mFragmentManager.beginTransaction();

        Fragment currentFragment = mFragmentManager.getPrimaryNavigationFragment();
        if (currentFragment != null) {
            fragmentTransaction.hide(currentFragment);
        }

        Fragment f = mFragmentManager.findFragmentByTag(TAG);
        if (f != null) {
            fragmentTransaction.show(f);
        } else {
            f = fragment;
            fragmentTransaction.add(R.id.fragment_container, f, TAG);
            fragmentTransaction.addToBackStack(BACK_STACK_ROOT_TAG);
        }
        fragmentTransaction.setPrimaryNavigationFragment(f);
        fragmentTransaction.setReorderingAllowed(true);
        fragmentTransaction.commit();
    }
}
