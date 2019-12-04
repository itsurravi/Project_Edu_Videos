package com.codrox.myapp.fragments;



import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.codrox.myapp.Activity.MainActivity;
import com.codrox.myapp.R;

import static com.codrox.myapp.Activity.MainActivity.BACK_STACK_ROOT_TAG;
import static com.codrox.myapp.Activity.MainActivity.TAG;

public class HomeFragment extends Fragment {


    public HomeFragment() {
        // Required empty public constructor
    }


    Spinner sp_states, sp_cities;
    Button btn_go;

    String ar[] = {"Please Choose City"};


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_home, container, false);
        sp_states = v.findViewById(R.id.sp_states);
        sp_cities = v.findViewById(R.id.sp_city);
        btn_go = v.findViewById(R.id.btn_go);

        sp_states.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 0:
                        ArrayAdapter aa = new ArrayAdapter(getContext(), android.R.layout.simple_spinner_item, ar);
                        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        sp_cities.setAdapter(aa);
                        break;
                    case 1:
                        ArrayAdapter hr = new ArrayAdapter(getContext(), android.R.layout.simple_spinner_item, getContext().getResources().getStringArray(R.array.haryana_states));
                        hr.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        sp_cities.setAdapter(hr);
                        break;
                    case 2:
                        ArrayAdapter rj = new ArrayAdapter(getContext(), android.R.layout.simple_spinner_item, getContext().getResources().getStringArray(R.array.rj_states));
                        rj.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        sp_cities.setAdapter(rj);
                        break;
                    case 3:
                        ArrayAdapter up = new ArrayAdapter(getContext(), android.R.layout.simple_spinner_item, getContext().getResources().getStringArray(R.array.up_states));
                        up.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        sp_cities.setAdapter(up);
                        break;
                    case 4:
                        ArrayAdapter pb = new ArrayAdapter(getContext(), android.R.layout.simple_spinner_item, getContext().getResources().getStringArray(R.array.pb_states));
                        pb.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        sp_cities.setAdapter(pb);
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        btn_go.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String st = sp_cities.getSelectedItem().toString();

                if (st.equals(ar[0])) {
                    Toast.makeText(getContext(), "Please Choose a State", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (st.equals("---City---")) {
                    Toast.makeText(getContext(), "Please Choose a City", Toast.LENGTH_SHORT).show();
                    return;
                }

                loadFragment(new ClassFragment());

                sp_states.setSelection(0);
            }
        });

        return v;
    }

    private void loadFragment(Fragment fragment) {
        // load fragment
        final FragmentManager manager = getFragmentManager();
//        manager.popBackStack(BACK_STACK_ROOT_TAG, FragmentManager.POP_BACK_STACK_INCLUSIVE);
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.setCustomAnimations(R.anim.slide_right_enter, R.anim.slide_left_exit,R.anim.slide_pop_enter, R.anim.slide_pop_exit);
        transaction.add( R.id.fragment_container, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }


}
