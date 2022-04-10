package com.myapp.intellvest;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class RecordsAdapter extends RecyclerView.Adapter<RecordsAdapter.imageViewHolder> {

    private Context context;
    private List<RecordsModel> list;

    public RecordsAdapter(Context context, List<RecordsModel> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public imageViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.records_item,parent,false);
        return new RecordsAdapter.imageViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull imageViewHolder holder, int position) {

        holder.recordTime.setText(list.get(position).getTime());
        holder.recordMessage.setText(list.get(position).getMessage());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class imageViewHolder extends RecyclerView.ViewHolder{
        TextView recordTime, recordMessage;

        public imageViewHolder(@NonNull View itemView) {
            super(itemView);

            recordTime = itemView.findViewById(R.id.recordTime);
            recordMessage = itemView.findViewById(R.id.recordMessageTxt);

        }

    }}
