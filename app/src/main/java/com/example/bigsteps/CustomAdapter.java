package com.example.bigsteps;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.database.Cursor;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.MyViewHolder> {

    private Context context;
    private ArrayList DAYS, STEPS;

    CustomAdapter(Context context, ArrayList DAYS, ArrayList STEPS){
        this.context = context;
        this.DAYS = DAYS;
        this.STEPS = STEPS;
    }

    @NonNull
    @Override
    public CustomAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.my_row, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CustomAdapter.MyViewHolder holder, int position) {
        holder.day_ids.setText(String.valueOf(DAYS.get(position)));
        holder.steps_ids.setText(String.valueOf(STEPS.get(position)));
    }

    @Override
    public int getItemCount() {
        return DAYS.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{

        TextView steps_ids, day_ids;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            steps_ids = itemView.findViewById(R.id.steps_id);
            day_ids = itemView.findViewById(R.id.day_id);
        }
    }
}

