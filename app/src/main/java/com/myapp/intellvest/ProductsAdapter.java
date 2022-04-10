package com.myapp.intellvest;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

public class ProductsAdapter extends RecyclerView.Adapter<ProductsAdapter.imageViewHolder> {

    private Context context;
    private List<ProductsModel> list;
    private ProductsAdapter.OnItemClickListener mListener;

    public ProductsAdapter(Context context, List<ProductsModel> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public ProductsAdapter.imageViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.items,parent,false);
        return new imageViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductsAdapter.imageViewHolder holder, int position) {


        holder.productName.setText(String.valueOf(list.get(position).getProductName()));
        Picasso.get()
                .load(list.get(position).getProductImageUrl())
                .into(holder.productImage);
        holder.investors.setText("Investors:  "+String.valueOf(list.get(position).getInvestors()));
        holder.commission.setText("Cmsn:  "+String.valueOf(list.get(position).getCommission())+"%");
        holder.price.setText("Price:  "+String.valueOf(list.get(position).getPrice())+"$");
        holder.saleProduct.setText("SaleProduct:  "+String.valueOf(list.get(position).getSaleProduct()));

        holder.setText(position);


    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class imageViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private ImageView productImage, backup;
        private TextView productName, investors, commission, price, saleProduct;
        private Button investBtn;
        public imageViewHolder(@NonNull View itemView) {
            super(itemView);


            productImage = itemView.findViewById(R.id.productImage);
            productName = itemView.findViewById(R.id.productName);
            investors = itemView.findViewById(R.id.investors);
            commission = itemView.findViewById(R.id.profit);
            price = itemView.findViewById(R.id.price);
            investBtn = itemView.findViewById(R.id.investButton);
            saleProduct = itemView.findViewById(R.id.saleProduct);
            backup = itemView.findViewById(R.id.backup);


            investBtn.setOnClickListener(this);
            backup.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    mListener.onBackupClick(getAdapterPosition());
                }
            });

        }
        private void setText(int position){
            if (MyAccountFragment.p0 == true && position == 0){
                investBtn.setText("Recover");
            }else if (MyAccountFragment.p1 == true && position == 1){
                investBtn.setText("Recover");
            }else if (MyAccountFragment.p2 == true && position == 2){
                investBtn.setText("Recover");
            }else if (MyAccountFragment.p3 == true && position == 3){
                investBtn.setText("Recover");
            }else if (MyAccountFragment.p4 == true && position == 4){
                investBtn.setText("Recover");
            }else if (MyAccountFragment.p5 == true && position == 5){
                investBtn.setText("Recover");
            }else if (MyAccountFragment.p6 == true && position == 6){
                investBtn.setText("Recover");
            }else if (MyAccountFragment.p7 == true && position == 7){
                investBtn.setText("Recover");
            }else if (MyAccountFragment.p8 == true && position == 8){
                investBtn.setText("Recover");
            }else if (MyAccountFragment.p9 == true && position == 9){
                investBtn.setText("Recover");
            }else if (MyAccountFragment.p10 == true && position == 10){
                investBtn.setText("Recover");
            }else if (MyAccountFragment.p11 == true && position == 11){
                investBtn.setText("Recover");
            }else if (MyAccountFragment.p12 == true && position == 12){
                investBtn.setText("Recover");
            }else if (MyAccountFragment.p13 == true && position == 13){
                investBtn.setText("Recover");
            }else if (MyAccountFragment.p14 == true && position == 14){
                investBtn.setText("Recover");
            }else if (MyAccountFragment.p15 == true && position == 5){
                investBtn.setText("Recover");
            }else if (MyAccountFragment.p16 == true && position == 16){
                investBtn.setText("Recover");
            }else if (MyAccountFragment.p17 == true && position == 17){
                investBtn.setText("Recover");
            }else if (MyAccountFragment.p18 == true && position == 18){
                investBtn.setText("Recover");
            }else if (MyAccountFragment.p19 == true && position == 19){
                investBtn.setText("Recover");
            }else if (MyAccountFragment.p20 == true && position == 20){
                investBtn.setText("Recover");
            }else if (MyAccountFragment.p21 == true && position == 21){
                investBtn.setText("Recover");
            }else if (MyAccountFragment.p22 == true && position == 22){
                investBtn.setText("Recover");
            }else if (MyAccountFragment.p23 == true && position == 23){
                investBtn.setText("Recover");
            }else if (MyAccountFragment.p24 == true && position == 24){
                investBtn.setText("Recover");
            }else if (MyAccountFragment.p25 == true && position == 25){
                investBtn.setText("Recover");
            }else if (MyAccountFragment.p26 == true && position == 26){
                investBtn.setText("Recover");
            }else if (MyAccountFragment.p27 == true && position == 27){
                investBtn.setText("Recover");
            }else if (MyAccountFragment.p28 == true && position == 28){
                investBtn.setText("Recover");
            }else if (MyAccountFragment.p29 == true && position == 29){
                investBtn.setText("Recover");
            }else {
                investBtn.setText("Invest");
            }

        }


        @Override
        public void onClick(View v) {
            if (mListener != null) {
                int position = getAdapterPosition();
                if (position != RecyclerView.NO_POSITION) {
                    mListener.onBtnClick(position, investBtn);
                }
            }
        }

    }

    public interface OnItemClickListener {
        void onBtnClick(int position, Button investBtn);
        void onBackupClick(int position);
    }

    public void setOnItemClickListener(ProductsAdapter.OnItemClickListener listener) {
        mListener = listener;
    }
}