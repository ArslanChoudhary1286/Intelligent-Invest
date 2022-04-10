package com.myapp.intellvest;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class RecordsFragment extends Fragment {

    DatabaseReference recordRef;
    RecyclerView recyclerView;
    private RecordsAdapter mAdapter;
    List<RecordsModel> list;
    private ValueEventListener rDBListener;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_records, container, false);

        recordRef = FirebaseDatabase.getInstance().getReference("Records");

        recyclerView = view.findViewById(R.id.recordRecycler);

        recyclerView.setHasFixedSize(true);
        final LinearLayoutManager linearLayoutManger = new LinearLayoutManager(getContext());

        recyclerView.setLayoutManager(linearLayoutManger);
        list = new ArrayList<>();




        getData();

        mAdapter = new RecordsAdapter(getContext(),list);


        recyclerView.setAdapter(mAdapter);


        return view;
    }

    public static String getDateTime() {
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yy  hh:mm a");
        Date date = new Date();
        return dateFormat.format(date);
    }

    public void getData(){

        rDBListener = recordRef.child(LoginActivity.phoneNumber).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                list.clear();
                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                    RecordsModel recordsModel = postSnapshot.getValue(RecordsModel.class);
                    recordsModel.setKey(postSnapshot.getKey());
                    list.add(recordsModel);
                }
                recyclerView.scrollToPosition(list.size()-1);
                mAdapter.notifyDataSetChanged();


            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
                Toast.makeText(getContext(), databaseError.getMessage(), Toast.LENGTH_SHORT).show();
//                mProgressCircle.setVisibility(View.INVISIBLE);
            }
        });
    }

}