package com.codrox.myapp.fragments;


import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.codrox.myapp.Activity.LoginActivity;
import com.codrox.myapp.Activity.MainActivity;
import com.codrox.myapp.Adapter.ProfileAdapter;
import com.codrox.myapp.Database.DB_Handler;
import com.codrox.myapp.Database.PrefManger;
import com.codrox.myapp.Models.UserInfo;
import com.codrox.myapp.R;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class AccountFragment extends Fragment {


    public AccountFragment() {
        // Required empty public constructor
    }

    PrefManger prefManger;
    DB_Handler db;

    Button btn_logout;
    public static TextView txt_name;
    ListView list_editProfile;

    public static List<UserInfo> l;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_account, container, false);
        prefManger = new PrefManger(getContext());
        db = new DB_Handler(getContext());

        btn_logout = v.findViewById(R.id.btn_logout);
        txt_name = v.findViewById(R.id.txt_name);
        list_editProfile = v.findViewById(R.id.list_editProfile);

        l = new ArrayList<>();

        btn_logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                prefManger.setLoggedIn(PrefManger.USER_LOGIN, false);
                startActivity(new Intent(getContext(), LoginActivity.class));
                getActivity().finish();
            }
        });
        return v;
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.d("FragmentPauseResume", AccountFragment.class.getSimpleName());
        Cursor c = db.getUserData(String.valueOf(db.getUserId(prefManger.getStringValues(DB_Handler.USER_EMAIL))));

        c.moveToFirst();

        UserInfo info = new UserInfo(
                c.getString(c.getColumnIndex(DB_Handler.USER_ID)),
                c.getString(c.getColumnIndex(DB_Handler.USER_NAME)),
                c.getString(c.getColumnIndex(DB_Handler.USER_EMAIL)),
                c.getString(c.getColumnIndex(DB_Handler.USER_PASSWORD))
        );

        l.add(info);

        ProfileAdapter ad = new ProfileAdapter(getContext(), l);

        list_editProfile.setAdapter(ad);

        txt_name.setText(l.get(0).getName());
    }
}
