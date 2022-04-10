package com.myapp.intellvest;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class InvestFragment extends Fragment {

    RecyclerView mRecyclerView;
    private ProductsAdapter mAdapter;
    private DatabaseReference nDatabaseRef, mDatabaseRef;
    private List<ProductsModel> recyclerList;
    private ValueEventListener nDBListener;
    public static boolean check = false;
//    FloatingActionButton floatingActionButton;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_invest, container, false);

        mRecyclerView = view.findViewById(R.id.recyclerView);
//        floatingActionButton = view.findViewById(R.id.floatingActionButton);
        nDatabaseRef = FirebaseDatabase.getInstance().getReference("productInformation");
        mDatabaseRef = FirebaseDatabase.getInstance().getReference("Users");

//        floatingActionButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                ProductsModel productsModel = new ProductsModel();
//                productsModel.setProductName("shirt");
//                productsModel.setProductImageUrl("url");
//                productsModel.setInvestors(124);
//                productsModel.setCommission((float) 0.0003);
//                productsModel.setPrice(25);
//                productsModel.setSaleProduct(30000);
//                String selectedId = nDatabaseRef.push().getKey();
//                nDatabaseRef.child(selectedId).setValue(productsModel);
//                Toast.makeText(getContext(), "data uploaded", Toast.LENGTH_SHORT).show();
//            }
//        });


        mRecyclerView.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        mRecyclerView.setLayoutManager(layoutManager);

        recyclerList = new ArrayList<>();
        getProductsData();

        mAdapter = new ProductsAdapter(getContext(), recyclerList);
        mRecyclerView.setAdapter(mAdapter);

        mAdapter.setOnItemClickListener(new ProductsAdapter.OnItemClickListener() {
            @Override
            public void onBtnClick(int position, Button investBtn) {

                if (isCheck(position)){

                    backup(position);

                    investBtn.setText("Invest");

                    HashMap<String,Object> hashMap = new HashMap();
                    hashMap.put("pp"+position,0);
                    hashMap.put("p"+position, false);
                    hashMap.put("ps"+position,0);

                    mDatabaseRef.child(LoginActivity.phoneNumber).updateChildren(hashMap).addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void aVoid) {
//                        Toast.makeText(getContext(), "", Toast.LENGTH_SHORT).show();
                        }
                    });
                }
                else {
                    float newInvest = recyclerList.get(position).getPrice();
                    if (MyAccountFragment.afterInvest>= newInvest){
                        investBtn.setText("Recover");

                        HashMap<String,Object> hashMap = new HashMap();
                        hashMap.put("pp"+position,newInvest);
                        hashMap.put("p"+position, true);
                        hashMap.put("sp"+position,recyclerList.get(position).getSaleProduct());

                        mDatabaseRef.child(LoginActivity.phoneNumber).updateChildren(hashMap).addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {
//                        Toast.makeText(getContext(), "Data up", Toast.LENGTH_SHORT).show();
                            }
                        });

                    }else {
                        Toast.makeText(getContext(), "you dint have money to invest", Toast.LENGTH_SHORT).show();
                    }
                }
            }

            @Override
            public void onBackupClick(int position) {
                backup(position);
            }
        });

        return view;
    }
    public void backup(int position){

        float commission = (float) recyclerList.get(position).getPrice()/100*recyclerList.get(position).getCommission()*getCurrentSaleProduct(position);

        float earn = MyAccountFragment.earning+commission;

        HashMap<String,Object> hashMap = new HashMap();
        hashMap.put("earning",earn);
        hashMap.put("sp"+position,recyclerList.get(position).getSaleProduct());


        mDatabaseRef.child(LoginActivity.phoneNumber).updateChildren(hashMap).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                Toast.makeText(getContext(), "Backup finished", Toast.LENGTH_SHORT).show();
            }
        });

    }

    private float getCurrentSaleProduct(int pos){
        if (pos == 0){
            return recyclerList.get(pos).getSaleProduct()- MyAccountFragment.sp0;
        }else if (pos == 1){
            return recyclerList.get(pos).getSaleProduct()- MyAccountFragment.sp1;
        }else if (pos == 2){
            return recyclerList.get(pos).getSaleProduct()- MyAccountFragment.sp2;
        }else if (pos == 3){
            return recyclerList.get(pos).getSaleProduct()- MyAccountFragment.sp3;
        }else if (pos == 4){
            return recyclerList.get(pos).getSaleProduct()- MyAccountFragment.sp4;
        }else if (pos == 5){
            return recyclerList.get(pos).getSaleProduct()- MyAccountFragment.sp5;
        }else if (pos == 6){
            return recyclerList.get(pos).getSaleProduct()- MyAccountFragment.sp6;
        }else if (pos == 7){
            return recyclerList.get(pos).getSaleProduct()- MyAccountFragment.sp7;
        }else if (pos == 8){
            return recyclerList.get(pos).getSaleProduct()- MyAccountFragment.sp8;
        }else if (pos == 9){
            return recyclerList.get(pos).getSaleProduct()- MyAccountFragment.sp9;
        }
        else if (pos == 10){
            return recyclerList.get(pos).getSaleProduct()- MyAccountFragment.sp10;
        }else if (pos == 11){
            return recyclerList.get(pos).getSaleProduct()- MyAccountFragment.sp11;
        }else if (pos == 12){
            return recyclerList.get(pos).getSaleProduct()- MyAccountFragment.sp12;
        }else if (pos == 13){
            return recyclerList.get(pos).getSaleProduct()- MyAccountFragment.sp13;
        }else if (pos == 14){
            return recyclerList.get(pos).getSaleProduct()- MyAccountFragment.sp14;
        }else if (pos == 15){
            return recyclerList.get(pos).getSaleProduct()- MyAccountFragment.sp15;
        }else if (pos == 16){
            return recyclerList.get(pos).getSaleProduct()- MyAccountFragment.sp16;
        }else if (pos == 17){
            return recyclerList.get(pos).getSaleProduct()- MyAccountFragment.sp17;
        }else if (pos == 18){
            return recyclerList.get(pos).getSaleProduct()- MyAccountFragment.sp18;
        }else if (pos == 19){
            return recyclerList.get(pos).getSaleProduct()- MyAccountFragment.sp19;
        }
        else if (pos == 20){
            return recyclerList.get(pos).getSaleProduct()- MyAccountFragment.sp20;
        }else if (pos == 21){
            return recyclerList.get(pos).getSaleProduct()- MyAccountFragment.sp21;
        }else if (pos == 22){
            return recyclerList.get(pos).getSaleProduct()- MyAccountFragment.sp22;
        }else if (pos == 23){
            return recyclerList.get(pos).getSaleProduct()- MyAccountFragment.sp23;
        }else if (pos == 24){
            return recyclerList.get(pos).getSaleProduct()- MyAccountFragment.sp24;
        }else if (pos == 25){
            return recyclerList.get(pos).getSaleProduct()- MyAccountFragment.sp25;
        }else if (pos == 26){
            return recyclerList.get(pos).getSaleProduct()- MyAccountFragment.sp26;
        }else if (pos == 27){
            return recyclerList.get(pos).getSaleProduct()- MyAccountFragment.sp27;
        }else if (pos == 28){
            return recyclerList.get(pos).getSaleProduct()- MyAccountFragment.sp28;
        }else if (pos == 29){
            return recyclerList.get(pos).getSaleProduct()- MyAccountFragment.sp29;
        }

        return 0;
    }
    private boolean isCheck(int pos){
        if (pos == 0){
            return MyAccountFragment.p0;
        }else if (pos == 1){
            return MyAccountFragment.p1;
        }else if (pos == 2){
            return MyAccountFragment.p2;
        }else if (pos == 3){
            return MyAccountFragment.p3;
        }else if (pos == 4){
            return MyAccountFragment.p4;
        }else if (pos == 5){
            return MyAccountFragment.p5;
        }else if (pos == 6){
            return MyAccountFragment.p6;
        }else if (pos == 7){
            return MyAccountFragment.p7;
        }else if (pos == 8){
            return MyAccountFragment.p8;
        }else if (pos == 9){
            return MyAccountFragment.p9;
        }
        else if (pos == 10){
            return MyAccountFragment.p10;
        }else if (pos == 11){
            return MyAccountFragment.p11;
        }else if (pos == 12){
            return MyAccountFragment.p12;
        }else if (pos == 13){
            return MyAccountFragment.p13;
        }else if (pos == 14){
            return MyAccountFragment.p14;
        }else if (pos == 15){
            return MyAccountFragment.p15;
        }else if (pos == 16){
            return MyAccountFragment.p16;
        }else if (pos == 17){
            return MyAccountFragment.p17;
        }else if (pos == 18){
            return MyAccountFragment.p18;
        }else if (pos == 19){
            return MyAccountFragment.p19;
        }
        else if (pos == 20){
            return MyAccountFragment.p20;
        }else if (pos == 21){
            return MyAccountFragment.p21;
        }else if (pos == 22){
            return MyAccountFragment.p22;
        }else if (pos == 23){
            return MyAccountFragment.p23;
        }else if (pos == 24){
            return MyAccountFragment.p24;
        }else if (pos == 25){
            return MyAccountFragment.p25;
        }else if (pos == 26){
            return MyAccountFragment.p26;
        }else if (pos == 27){
            return MyAccountFragment.p27;
        }else if (pos == 28){
            return MyAccountFragment.p28;
        }else if (pos == 29){
            return MyAccountFragment.p29;
        }
        return false;
    }

    public void getProductsData(){
        nDBListener = nDatabaseRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                recyclerList.clear();
                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                    ProductsModel productsModel = postSnapshot.getValue(ProductsModel.class);
                    productsModel.setKey(postSnapshot.getKey());
                    recyclerList.add(productsModel);
                }
                mAdapter.notifyDataSetChanged();

            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
                Toast.makeText(getContext(), databaseError.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }


}