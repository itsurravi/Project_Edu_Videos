package com.codrox.myapp.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.codrox.myapp.Database.PrefManger;
import com.codrox.myapp.R;
import com.codrox.myapp.fragments.AccountFragment;
import com.codrox.myapp.fragments.CartFragment;
import com.codrox.myapp.fragments.HomeFragment;
import com.codrox.myapp.fragments.LibraryFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;


public class MainActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {

    public static final String BACK_STACK_ROOT_TAG = "root_fragment";

    public static BottomNavigationView navigationItemView;
    private PrefManger prefManger;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        prefManger = new PrefManger(this);

        navigationItemView = findViewById(R.id.navigation);
        navigationItemView.setOnNavigationItemSelectedListener(this);

        HomeFragment f = new HomeFragment();
        loadFragment(f, HomeFragment.class.getSimpleName());

    }

    /*
    log out option must be in accounts fragment
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.options_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId()==R.id.logout){
            prefManger.setLoggedIn(PrefManger.USER_LOGIN, false);
            startActivity(new Intent(MainActivity.this,LoginActivity.class));
            finish();
        }
        return super.onOptionsItemSelected(item);
    }*/

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
