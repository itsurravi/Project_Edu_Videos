package com.codrox.myapp.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.codrox.myapp.R;

import java.util.List;

public class ChaptersAdapter extends RecyclerView.Adapter<ChaptersAdapter.MyViewHolder> {

    Context c;
    List<String> chapters;
    ItemClickListener listener;

    public ChaptersAdapter(Context c, List<String> chapters, ItemClickListener listener) {
        this.c = c;
        this.chapters = chapters;
        this.listener=listener;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater li = LayoutInflater.from(c);
        View v = li.inflate(R.layout.adapter_chapters_list, parent, false);

        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, final int position) {
        holder.tv.setText(chapters.get(position));
        holder.lv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onItemClick(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return chapters.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder{

        TextView tv;
        LinearLayout lv;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            tv = itemView.findViewById(R.id.txt_chapters);
            lv = itemView.findViewById(R.id.layout_chapter);
        }
    }

    public interface ItemClickListener
    {
        void onItemClick(int position);
    }
}
