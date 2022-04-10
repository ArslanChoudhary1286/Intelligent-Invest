package com.myapp.intellvest;


import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.pm.PackageManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DatabaseReference;
import com.squareup.picasso.Picasso;

import java.util.List;

public class ChattingAdapter extends RecyclerView.Adapter<ChattingAdapter.imageViewHolder> {

    private Context context;
    private List<ChattingModel> list;
    private ChattingAdapter.OnItemClickListener mListener;
    DatabaseReference databaseReference;
    private static final int MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE = 1;

    public ChattingAdapter(Context context, List< ChattingModel> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public ChattingAdapter.imageViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.chatting_item,parent,false);
        return new imageViewHolder(v);
    }

    @SuppressLint("ResourceAsColor")
    @Override
    public void onBindViewHolder(@NonNull ChattingAdapter.imageViewHolder holder, int position) {


        ViewGroup.MarginLayoutParams marginParams = new ViewGroup.MarginLayoutParams(holder.constraintLayout.getLayoutParams());

        holder.nameText.setText(list.get(position).getMessageUser());
        holder.dateText.setText(list.get(position).getMessageTime());
        holder.textView.setText(list.get(position).getMessageText());


        if (list.get(position).getImageUri() == null){
            holder.screenShotImg.getLayoutParams().height = 0;

        }else {

            holder.screenShotImg.getLayoutParams().height = 600;
            holder.screenShotImg.getLayoutParams().width = 450;

            if (LoginActivity.Password.equals("123456789")){
                            Picasso.get()
                    .load(list.get(position).getImageUrl())
                    .into(holder.screenShotImg);
            }else {

                if (ContextCompat.checkSelfPermission(context,
                        Manifest.permission.READ_EXTERNAL_STORAGE)
                        != PackageManager.PERMISSION_GRANTED) {
                } else {
//                    holder.screenShotImg.setImageURI(Uri.parse(list.get(position).getImageUri()));
                    Picasso.get().load(list.get(position).getImageUri()).placeholder(R.drawable.empty_img).into(holder.screenShotImg);
                }

            }

        }


        if (list.get(position).getMessageUser().equals("Admin")){

            holder.img.setImageResource(R.drawable.person);
            marginParams.setMargins(250, 1, 4, 24);
        }
        else {
            holder.img.setImageResource(R.drawable.male);
            marginParams.setMargins(4, 1, 250, 24);
        }

        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(marginParams);
        holder.constraintLayout.setLayoutParams(layoutParams);


    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class imageViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView textView, nameText, dateText;
        ImageView img, screenShotImg;
        ConstraintLayout constraintLayout;


        public imageViewHolder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.textView);
            img = itemView.findViewById(R.id.imageView);
            nameText = itemView.findViewById(R.id.nameText);
            constraintLayout = itemView.findViewById(R.id.view);
            dateText = itemView.findViewById(R.id.dateText);

            screenShotImg = itemView.findViewById(R.id.chatImg);


        }
        public void requestRead() {

        }


        @Override
        public void onClick(View v) {
            if (mListener != null) {
                int position = getAdapterPosition();
                if (position != RecyclerView.NO_POSITION) {
                    mListener.onBtnClick(position);
                }
            }
        }

    }


    public interface OnItemClickListener {
        void onBtnClick(int position);
    }

    public void setOnItemClickListener(ChattingAdapter.OnItemClickListener listener) {
        mListener = listener;
    }
}
