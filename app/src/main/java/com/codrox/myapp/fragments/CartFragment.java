package com.codrox.myapp.fragments;


import android.os.AsyncTask;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.codrox.myapp.Activity.MainActivity;
import com.codrox.myapp.Adapter.CartListAdapter;
import com.codrox.myapp.Database.DB_Handler;
import com.codrox.myapp.Database.PrefManger;
import com.codrox.myapp.Models.CartItems;
import com.codrox.myapp.Models.TopicsInfo;
import com.codrox.myapp.R;

import java.util.ArrayList;
import java.util.List;

import static com.codrox.myapp.Activity.MainActivity.TAG;

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

    public List<CartItems> list=new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_cart, container, false);

        list = new ArrayList<>();

        initialize(v);

        new FetchCartData();

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

    class FetchCartData extends AsyncTask<Void, Void, Void> {

        @Override
        protected Void doInBackground(Void... voids) {
            PrefManger prefManger = new PrefManger(getContext());
            DB_Handler db = new DB_Handler(getContext());

            list.clear();

            list = db.getAllCartItems(prefManger.getStringValues(DB_Handler.USER_EMAIL));

            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            if(list.size()>0) {
                cart_layout.setVisibility(View.VISIBLE);
                no_item_layout.setVisibility(View.GONE);
                CartListAdapter cla = new CartListAdapter(getContext(), list);
                cart_list.setAdapter(cla);
            }
            else
            {
                cart_layout.setVisibility(View.GONE);
                no_item_layout.setVisibility(View.VISIBLE);
            }
        }
    }
}
