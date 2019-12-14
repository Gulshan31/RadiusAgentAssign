package com.example.radiusagent;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class DataAdapter extends RecyclerView.Adapter<DataAdapter.Viewholder> {
    private List<DataModel> mDataModels;

    public DataAdapter(List<DataModel> dataModels) {
        mDataModels = dataModels;
    }
    @NonNull
    @Override
    public Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        // Inflate the custom layout
        View contactView = inflater.inflate(R.layout.recycler_view_item, parent, false);

        // Return a new holder instance
        Viewholder viewHolder = new Viewholder(contactView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull Viewholder holder, int position) {
            // here getting the data from array of datamodels
            DataModel dataModel = mDataModels.get(position);
            // now here set the data from data model to various views
            holder.from.setText(dataModel.getFrom());
            holder.to.setText(dataModel.getTo());
            holder.fromTime.setText(dataModel.getFromTime());
            holder.toTime.setText(dataModel.getToTime());
            holder.travelTimeDuration.setText("Travel Time: "+dataModel.getTotalTimeDuration());
            holder.fairValue.setText(dataModel.getCostValue());


    }

    @Override
    public int getItemCount() {
        //Log.v("Size", String.valueOf(mDataModels.size()));
        return mDataModels.size();
    }
    public void addItems(List<DataModel> data) {
        mDataModels.addAll(data);
        notifyDataSetChanged();
    }

    // to hold views
    public class Viewholder extends  RecyclerView.ViewHolder {
        public TextView from,to,fromTime,toTime,fairValue,travelTimeDuration;

        public Viewholder(@NonNull View itemView) {
            super(itemView);
            from = itemView.findViewById(R.id.from);
            to = itemView.findViewById(R.id.to);
            fromTime = itemView.findViewById(R.id.from_time);
            toTime  = itemView.findViewById(R.id.to_time);
            fairValue = itemView.findViewById(R.id.fair_value);
            travelTimeDuration = itemView.findViewById(R.id.trip_duration_in_minutes);
        }
    }
}
