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
import android.widget.Toast;

import com.codrox.myapp.Adapter.CartListAdapter;
import com.codrox.myapp.Database.DB_Handler;
import com.codrox.myapp.Database.PrefManger;
import com.codrox.myapp.Models.CartItems;
import com.codrox.myapp.Models.SubTopicsInfo;
import com.codrox.myapp.R;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class CartFragment extends Fragment implements CartListAdapter.ItemRemoveClick {


    public CartFragment() {
        // Required empty public constructor
    }

    LinearLayout no_item_layout, cart_layout;
    ListView cart_list;
    TextView txt_total, txt_gst, txt_total_pay;
    Button btn_pay;

    public List<CartItems> list = new ArrayList<>();

    CartListAdapter cla;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_cart, container, false);

        list = new ArrayList<>();

        no_item_layout = v.findViewById(R.id.layout_no_item);
        cart_layout = v.findViewById(R.id.cart_layout);
        cart_list = v.findViewById(R.id.cart_list);
        txt_total = v.findViewById(R.id.txt_total);
        txt_gst = v.findViewById(R.id.txt_gst);
        txt_total_pay = v.findViewById(R.id.txt_total_pay);
        btn_pay = v.findViewById(R.id.btn_pay);

        btn_pay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DB_Handler db = new DB_Handler(getContext());
                PrefManger prefManger = new PrefManger(getContext());

                for(CartItems c : list)
                {
                    SubTopicsInfo t = c.getSubTopicsInfo();

                    db.saveToLibrary(t.getId(), prefManger.getStringValues(DB_Handler.USER_EMAIL));

                    db.removeToCart(c.getCartId());
                }

                list.clear();
                cart_layout.setVisibility(View.GONE);
                no_item_layout.setVisibility(View.VISIBLE);


                Toast.makeText(getContext(), "Your Purchased Items will be available in Library", Toast.LENGTH_SHORT).show();

            }
        });

        return v;
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.d("FragmentPauseResume", CartFragment.class.getSimpleName());
        new FetchCartData().execute();
    }

    @Override
    public void onItemRemoveClick(int position) {
        DB_Handler db = new DB_Handler(getContext());
        db.removeToCart(list.get(position).getCartId());
        list.remove(position);
        if (list.size() > 0) {
            cla.notifyDataSetChanged();
            calculateTotal();
        } else {
            cart_layout.setVisibility(View.GONE);
            no_item_layout.setVisibility(View.VISIBLE);
        }
    }

    class FetchCartData extends AsyncTask<Void, Void, Void> {

        @Override
        protected Void doInBackground(Void... voids) {
            Log.d("SQL_Query", "DOING");
            PrefManger prefManger = new PrefManger(getContext());
            DB_Handler db = new DB_Handler(getContext());

            list.clear();

            list = db.getAllCartItems(prefManger.getStringValues(DB_Handler.USER_EMAIL));

            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            if (list.size() > 0) {
                cart_layout.setVisibility(View.VISIBLE);
                no_item_layout.setVisibility(View.GONE);
                cla = new CartListAdapter(getContext(), list, CartFragment.this);
                cart_list.setAdapter(cla);
                calculateTotal();

            } else {
                cart_layout.setVisibility(View.GONE);
                no_item_layout.setVisibility(View.VISIBLE);
            }
        }
    }

    private void calculateTotal() {
        int t_price =0;
        double gst=0.0;
        for(CartItems c : list)
        {
            SubTopicsInfo t = c.getSubTopicsInfo();
            t_price += Integer.parseInt(t.getPrice());
        }

        gst = (t_price*18)/100;

        txt_total.setText(String.valueOf(t_price));
        txt_gst.setText(String.valueOf(gst));
        txt_total_pay.setText(String.valueOf(t_price+gst));
    }
}
