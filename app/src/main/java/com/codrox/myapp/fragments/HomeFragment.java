package com.codrox.myapp.fragments;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.codrox.myapp.R;

/**
 * A simple {@link Fragment} subclass.
 */
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
                switch (position)
                {
                    case 0:
                        ArrayAdapter aa = new ArrayAdapter(getContext(),android.R.layout.simple_spinner_item,ar);
                        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        sp_cities.setAdapter(aa);
                        break;
                    case 1:
                        ArrayAdapter hr = new ArrayAdapter(getContext(),android.R.layout.simple_spinner_item,getContext().getResources().getStringArray(R.array.haryana_states));
                        hr.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        sp_cities.setAdapter(hr);
                        break;
                    case 2:
                        ArrayAdapter rj = new ArrayAdapter(getContext(),android.R.layout.simple_spinner_item,getContext().getResources().getStringArray(R.array.rj_states));
                        rj.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        sp_cities.setAdapter(rj);
                        break;
                    case 3:
                        ArrayAdapter up = new ArrayAdapter(getContext(),android.R.layout.simple_spinner_item,getContext().getResources().getStringArray(R.array.up_states));
                        up.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        sp_cities.setAdapter(up);
                        break;
                    case 4:
                        ArrayAdapter pb = new ArrayAdapter(getContext(),android.R.layout.simple_spinner_item,getContext().getResources().getStringArray(R.array.pb_states));
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

                if (st.equals(ar[0]))
                {
                    Toast.makeText(getContext(), "Please Choose a City", Toast.LENGTH_SHORT).show();
                    return;
                }

            }
        });

        return v;
    }

}
