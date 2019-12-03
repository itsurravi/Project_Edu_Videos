package com.codrox.myapp.Adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.codrox.myapp.Database.DB_Handler;
import com.codrox.myapp.Database.PrefManger;
import com.codrox.myapp.Models.UserInfo;
import com.codrox.myapp.R;

import java.util.ArrayList;
import java.util.List;

import static com.codrox.myapp.fragments.AccountFragment.l;
import static com.codrox.myapp.fragments.AccountFragment.txt_name;

public class ProfileAdapter extends BaseAdapter {

    Context c;

    List<String> list;
    List<UserInfo> userInfo;

    public ProfileAdapter(Context c, List<UserInfo> l) {
        this.c = c;
        this.userInfo = l;
        list = new ArrayList<>();
        list.add("Edit Name: ");
        list.add("Edit Email: ");
        list.add("Edit Password: ");
    }

    @Override
    public int getCount() {
        return 3;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        LayoutInflater li = LayoutInflater.from(c);
        convertView = li.inflate(R.layout.adapter_profile, null);

        TextView edit = convertView.findViewById(R.id.txt_edit);
        final TextView user_info = convertView.findViewById(R.id.txt_user_info);
        ImageButton btn = convertView.findViewById(R.id.btn_edit);

        edit.setText(list.get(position));

        switch (position) {
            case 0:
                user_info.setText(userInfo.get(0).getName());
                break;
            case 1:
                user_info.setText(userInfo.get(0).getEmail());
                break;
            case 2:
                user_info.setText("**********");
                break;
        }

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showAlertDialog(position);
            }
        });

        return convertView;
    }

    private void showAlertDialog(final int position) {
        LayoutInflater li = LayoutInflater.from(c);
        View v = li.inflate(R.layout.alert_edit_profile, null);
        final AlertDialog.Builder builder = new AlertDialog.Builder(c);
        builder.setView(v);

        final EditText ed = v.findViewById(R.id.edit_value);
        Button btn = v.findViewById(R.id.btn_submit);

        final AlertDialog ad = builder.create();
        ad.show();

        String value = "";

        switch (position) {
            case 0:
                value = userInfo.get(0).getName();
                break;
            case 1:
                value = userInfo.get(0).getEmail();
                break;
            case 2:
                value = userInfo.get(0).getPassword();
                break;
        }

        ed.setText(value);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String finalValue = ed.getText().toString();
                DB_Handler db = new DB_Handler(c);
                switch (position) {
                    case 0:
                        db.updateUserName(userInfo.get(0).getId(), finalValue);
                        break;
                    case 1:
                        db.updateUserEmail(userInfo.get(0).getId(), finalValue);
                        break;
                    case 2:
                        db.updateUserPassword(userInfo.get(0).getId(), finalValue);
                        break;
                }
                updateData();
                ad.dismiss();
            }
        });
    }

    private void updateData() {
        PrefManger prefManger = new PrefManger(c);
        DB_Handler db = new DB_Handler(c);

        Cursor c = db.getUserData(String.valueOf(db.getUserId(prefManger.getStringValues(DB_Handler.USER_EMAIL))));

        c.moveToFirst();

        UserInfo info = new UserInfo(
                c.getString(c.getColumnIndex(DB_Handler.USER_ID)),
                c.getString(c.getColumnIndex(DB_Handler.USER_NAME)),
                c.getString(c.getColumnIndex(DB_Handler.USER_EMAIL)),
                c.getString(c.getColumnIndex(DB_Handler.USER_PASSWORD))
        );

        l.set(0, info);
        prefManger.setStringValues(DB_Handler.USER_EMAIL, info.getEmail());
        txt_name.setText(l.get(0).getName());
        notifyDataSetChanged();
    }
}
