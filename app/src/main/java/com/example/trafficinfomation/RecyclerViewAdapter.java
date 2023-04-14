package com.example.trafficinfomation;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {

    private List<Cctv> cctvList = new ArrayList<>();
    private Context context;

    public void addCctvList(Cctv cctv) {
        cctvList.add(cctv);
    }

    public void setContext(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.recycler_view_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Cctv cctv = cctvList.get(position);
        holder.name.setText(cctv.getName());
        holder.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, CctvPlayer.class);
                Bundle bundle = new Bundle();
                bundle.putString("rtspUrl", cctv.getRtspUri());
                intent.putExtra("bundle", bundle);

                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return cctvList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView name;
        Button button;
        ViewHolder(View view) {
            super(view);
            name = view.findViewById(R.id.textView);
            button = view.findViewById(R.id.button);
        }
    }

}
