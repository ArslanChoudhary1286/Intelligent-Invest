package com.myapp.intellvest;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import static android.app.Activity.RESULT_OK;


public class ChattingFragment extends Fragment {

    EditText editText;
    ImageView sendMessage;
    List<ChattingModel> list;
    private ValueEventListener nDBListener;
    RecyclerView recyclerView;
    private ChattingAdapter mAdapter;
    ChattingModel chattingModel;
    ImageView uploadScreenShot;
    DatabaseReference myRef;
    int messageCounter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_chatting, container, false);

        editText = view.findViewById(R.id.editText);
        sendMessage = view.findViewById(R.id.sendMessage);
        recyclerView = view.findViewById(R.id.recyclerView);
        uploadScreenShot = view.findViewById(R.id.upload_screenshot);

        recyclerView.setHasFixedSize(true);
        final LinearLayoutManager linearLayoutManger = new LinearLayoutManager(getContext());

        recyclerView.setLayoutManager(linearLayoutManger);
        //Scroll item 2 to 20 pixels from the top
//        linearLayoutManager.scrollToPositionWithOffset(2, 20);

        myRef = FirebaseDatabase.getInstance().getReference("chatting");
        list = new ArrayList<>();


        sendMessage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chattingModel = new ChattingModel();
                chattingModel.setMessageText(editText.getText().toString());
                chattingModel.setMessageUser(MyAccountFragment.name);
                chattingModel.setMessageTime(getDateTime());
//                chattingModel.setMessageCounter(chattingModel.getMessageCounter()+1);

                myRef.child(LoginActivity.phoneNumber).push().setValue(chattingModel).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        editText.setText("");
                        HashMap<String, Object> hashMap = new HashMap<>();
                        hashMap.put("messageCounter",messageCounter+1);
                        myRef.child(LoginActivity.phoneNumber).updateChildren(hashMap).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(getContext(), "Error : " + e.getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(getContext(), "Error:" + e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
        editText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CountDownTimer countDownTimer = new CountDownTimer(500,500) {
                    @Override
                    public void onTick(long millisUntilFinished) {
                    }
                    @Override
                    public void onFinish() {

                        recyclerView.scrollToPosition(list.size()-1);
                    }
                }.start();
            }
        });

        uploadScreenShot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Build.VERSION.SDK_INT < Build.VERSION_CODES.KITKAT) {
                    Intent intent = new Intent();
                    intent.setType("image/*");
                    intent.setAction(Intent.ACTION_GET_CONTENT);
                    startActivityForResult(Intent.createChooser(intent, "Select Picture"), 999);
                } else {
                    Intent intent = new Intent(Intent.ACTION_OPEN_DOCUMENT);
                    intent.addCategory(Intent.CATEGORY_OPENABLE);
                    intent.setType("image/*");
                    startActivityForResult(intent,999);
                }
            }
        });

        getData();

        mAdapter = new ChattingAdapter(getContext(),list);

        recyclerView.setAdapter(mAdapter);

        return view;
    }
    public static String getDateTime() {
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yy  hh:mm a");
        Date date = new Date();
        return dateFormat.format(date);
    }

    public void getData(){

        nDBListener = myRef.child(LoginActivity.phoneNumber).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                list.clear();
                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {

                    if (postSnapshot.getKey().equals("messageCounter")){
                        messageCounter = Integer.valueOf(postSnapshot.getValue().toString());
                    }
                    else {
                        ChattingModel chattingModel = postSnapshot.getValue(ChattingModel.class);
                        chattingModel.setKey(postSnapshot.getKey());
                        list.add(chattingModel);
                    }

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
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 999 && resultCode == RESULT_OK) {



            Uri originalUri = null;
            if (Build.VERSION.SDK_INT < 19) {
                originalUri = data.getData();
            } else {
                originalUri = data.getData();
                final int takeFlags = data.getFlags()
                        & (Intent.FLAG_GRANT_READ_URI_PERMISSION
                        | Intent.FLAG_GRANT_WRITE_URI_PERMISSION);

                try {
                    getActivity().getContentResolver().takePersistableUriPermission(originalUri, takeFlags);
                }
                catch (SecurityException e){
                    e.printStackTrace();
                }

                try {
//                    mImageUri = data.getData();
                    Intent i = new Intent(getActivity(), DisplayActivity.class);
                    i.putExtra("uri",originalUri.toString());
                    startActivity(i);
                    ((Activity) getActivity()).overridePendingTransition(0, 0);

                }
                catch (Exception e){

                }
            }

        }
    }

}