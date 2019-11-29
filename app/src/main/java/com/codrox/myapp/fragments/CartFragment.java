package com.codrox.myapp.fragments;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.codrox.myapp.Models.Subjects;
import com.codrox.myapp.R;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class CartFragment extends Fragment {


    public CartFragment() {
        // Required empty public constructor
    }

    LinearLayout no_item_layout, cart_layout;
    ListView cart_list;
    TextView txt_total, txt_gst, txt_total_pay;
    Button btn_pay;

    List<Subjects> list;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_cart, container, false);

        list = new ArrayList<>();

        initialize(v);



        btn_pay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        return v;
    }

    private void initialize(View v) {
        no_item_layout = v.findViewById(R.id.layout_no_item);
        cart_layout = v.findViewById(R.id.cart_layout);
        cart_list = v.findViewById(R.id.cart_list);
        txt_total = v.findViewById(R.id.txt_total);
        txt_gst = v.findViewById(R.id.txt_gst);
        txt_total_pay = v.findViewById(R.id.txt_total_pay);
        btn_pay = v.findViewById(R.id.btn_pay);
    }
}
