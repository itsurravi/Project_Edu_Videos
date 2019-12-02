package com.codrox.myapp.fragments;


import android.content.Intent;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.codrox.myapp.Activity.LoginActivity;
import com.codrox.myapp.Activity.MainActivity;
import com.codrox.myapp.Database.PrefManger;
import com.codrox.myapp.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class AccountFragment extends Fragment {


    public AccountFragment() {
        // Required empty public constructor
    }

    Button btn_logout;
    PrefManger prefManger;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_account, container, false);
        btn_logout = v.findViewById(R.id.btn_logout);
        prefManger = new PrefManger(getContext());
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

}
