package com.kp.ebana;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

    ArrayList<Record> mList;
    Context context;

    public MyAdapter (Context context, ArrayList<Record> mList)
    {
        this.mList = mList;
        this.context = context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from (context).inflate(R.layout.event_list_item, parent, false);
        return  new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        Record record = mList.get (position);
        holder.name.setText (record.getNazwa_zdarzenia ());
        holder.place.setText (record.getMiejsce_zdarzenia ());
        holder.data.setText (record.getData_zdarzenia ());
        holder.hour.setText (record.getGodzina_zdarzenia ());


    }

    @Override
    public int getItemCount() {
        return mList.size ();
    }

    public static class MyViewHolder extends  RecyclerView.ViewHolder{

        TextView name, place, data, hour;

        public MyViewHolder(@NonNull View itemView) {
            super (itemView);

            name = itemView.findViewById (R.id.name_of_event);
            place = itemView.findViewById (R.id.place_of_event);
            data = itemView.findViewById (R.id.date_of_event);
            hour = itemView.findViewById (R.id.hour_of_value);
        }
    }
}
