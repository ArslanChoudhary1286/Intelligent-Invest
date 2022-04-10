package com.myapp.intellvest;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;


public class PlansFragment extends Fragment {

    private ValueEventListener plansListener;
    DatabaseReference mDatabaseRef, plansRef, recReference;
    private FirebaseAuth mAuth;
    Button localBtn, superBtn, superiorBtn, mangerBtn,seniorBtn, goldenBtn, diamondBtn;
    TextView moreTxt;
    PlansModel plansModel;
    Button okBtn, cancelBtn;
    ImageView emojiImage;
    TextView emojiTxt;
    LinearLayout layout;
    int userBonus;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_plans, container, false);

        mDatabaseRef = FirebaseDatabase.getInstance().getReference("Users");
        plansRef = FirebaseDatabase.getInstance().getReference("Plans");
        recReference = FirebaseDatabase.getInstance().getReference("Records");

        localBtn = view.findViewById(R.id.localBtn);
        superBtn = view.findViewById(R.id.superBtn);
        superiorBtn = view.findViewById(R.id.superiorBtn);
        mangerBtn = view.findViewById(R.id.mangerBtn);
        seniorBtn = view.findViewById(R.id.seniorBtn);
        goldenBtn = view.findViewById(R.id.goldenBtn);
        diamondBtn = view.findViewById(R.id.diamondBtn);
        moreTxt = view.findViewById(R.id.moreTxt);

        okBtn = view.findViewById(R.id.emojiOkBtn);
        cancelBtn = view.findViewById(R.id.emojiCancelBtn);
        emojiImage = view.findViewById(R.id.emojiImageView);
        emojiTxt = view.findViewById(R.id.emojiText);

        layout = (LinearLayout)view.findViewById(R.id.Layout);

        okBtn.setEnabled(false);
        okBtn.setAlpha((float) 0.4);

//        setPlans();
        getPlansData();

        cancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                layout.setVisibility(View.GONE);
                okBtn.setEnabled(false);
                okBtn.setAlpha((float) 0.4);
            }
        });
        okBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                plansUpdate(userBonus);
                okBtn.setEnabled(false);
                okBtn.setAlpha((float) 0.4);
                layout.setVisibility(View.GONE);
            }
        });



        localBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (MyAccountFragment.total >= plansModel.getNeedLocal() && MyAccountFragment.bonus == 0){  // 5
                    Toast.makeText(getContext(), "l is " + plansModel.getNeedLocal(), Toast.LENGTH_SHORT).show();
                    okBtn.setEnabled(true);
                    okBtn.setAlpha(1);
                    emojiTxt.setText(plansModel.getHappyTxt() + plansModel.getLocal() + "$");
                    emojiImage.setImageResource(R.drawable.happy_emoji);
                    userBonus = plansModel.getLocal();
                }else {
                    emojiTxt.setText(plansModel.getSadTxt() + plansModel.getNeedLocal()+ "$");
                    emojiImage.setImageResource(R.drawable.sad_emoji);
                }
                openLayout();
            }

        });
        superBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(), "so is " + plansModel.getNeedSoper(), Toast.LENGTH_SHORT).show();
                if (MyAccountFragment.total >= plansModel.getNeedSoper() && MyAccountFragment.bonus == plansModel.getLocal()){ // 7

                    okBtn.setEnabled(true);
                    okBtn.setAlpha( 1);
                    emojiTxt.setText(plansModel.getHappyTxt() + plansModel.getSoper() + "$");
                    emojiImage.setImageResource(R.drawable.happy_emoji);
                    userBonus = plansModel.getSoper();
                } else {
                    emojiTxt.setText(plansModel.getSadTxt() + plansModel.getNeedSoper()+ "$");
                    emojiImage.setImageResource(R.drawable.sad_emoji);
                }
                openLayout();
            }
        });
        superiorBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (MyAccountFragment.total >= plansModel.getNeedSuperior() && MyAccountFragment.bonus == plansModel.getSoper()){  // 10
                    Toast.makeText(getContext(), "su is " + plansModel.getNeedSuperior(), Toast.LENGTH_SHORT).show();
                    okBtn.setEnabled(true);
                    okBtn.setAlpha(1);
                    emojiTxt.setText(plansModel.getHappyTxt() + plansModel.getSuperior() + "$");
                    emojiImage.setImageResource(R.drawable.happy_emoji);
                    userBonus = plansModel.getSuperior();
                }else {
                    emojiTxt.setText(plansModel.getSadTxt() + plansModel.getNeedSuperior()+ "$");
                    emojiImage.setImageResource(R.drawable.sad_emoji);
                }
                openLayout();
            }
        });
        mangerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (MyAccountFragment.total >= plansModel.getNeedManger() && MyAccountFragment.bonus == plansModel.getSuperior()){ //  15
                    Toast.makeText(getContext(), "m is " + plansModel.getNeedManger(), Toast.LENGTH_SHORT).show();
                    okBtn.setEnabled(true);
                    okBtn.setAlpha(1);
                    emojiTxt.setText(plansModel.getHappyTxt() + plansModel.getManger() + "$");
                    emojiImage.setImageResource(R.drawable.happy_emoji);
                    userBonus = plansModel.getManger();
                }else {
                    emojiTxt.setText(plansModel.getSadTxt() + plansModel.getNeedManger()+ "$");
                    emojiImage.setImageResource(R.drawable.sad_emoji);
                }
                openLayout();
            }
        });
        seniorBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (MyAccountFragment.total >= plansModel.getNeedSenior() && MyAccountFragment.bonus == plansModel.getManger()){ //  25
                    Toast.makeText(getContext(), "s is " + plansModel.getNeedSenior(), Toast.LENGTH_SHORT).show();
                    okBtn.setEnabled(true);
                    okBtn.setAlpha(1);
                    emojiTxt.setText(plansModel.getHappyTxt() + plansModel.getSenior() + "$");
                    emojiImage.setImageResource(R.drawable.happy_emoji);
                    userBonus = plansModel.getSenior();
                }else {
                    emojiTxt.setText(plansModel.getSadTxt() + plansModel.getNeedSenior()+ "$");
                    emojiImage.setImageResource(R.drawable.sad_emoji);
                }
                openLayout();
            }
        });
        goldenBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (MyAccountFragment.total >= plansModel.getNeedGolden() && MyAccountFragment.bonus == plansModel.getSenior()){ //  35
                    Toast.makeText(getContext(), "g is " + plansModel.getNeedGolden(), Toast.LENGTH_SHORT).show();
                    okBtn.setEnabled(true);
                    okBtn.setAlpha(1);
                    emojiTxt.setText(plansModel.getHappyTxt() + plansModel.getGolden() + "$");
                    emojiImage.setImageResource(R.drawable.happy_emoji);
                    userBonus = plansModel.getGolden();
                }else {
                    emojiTxt.setText(plansModel.getSadTxt() + plansModel.getNeedGolden()+ "$");
                    emojiImage.setImageResource(R.drawable.sad_emoji);
                }
                openLayout();
            }
        });
        diamondBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (MyAccountFragment.total >= plansModel.getNeedDiamond() && MyAccountFragment.bonus == plansModel.getGolden()){  //  50
                    Toast.makeText(getContext(), "d is " + plansModel.getNeedDiamond(), Toast.LENGTH_SHORT).show();
                    okBtn.setEnabled(true);
                    okBtn.setAlpha(1);
                    emojiTxt.setText(plansModel.getHappyTxt() + plansModel.getDiamond() + "$");
                    emojiImage.setImageResource(R.drawable.happy_emoji);
                    userBonus = plansModel.getDiamond();
                }else {
                    emojiTxt.setText(plansModel.getSadTxt() + plansModel.getNeedDiamond()+ "$");
                    emojiImage.setImageResource(R.drawable.sad_emoji);
                }
                openLayout();
            }
        });
        moreTxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(), ""+ plansModel.getMoreTxt(), Toast.LENGTH_SHORT).show();
            }
        });


        return view;
    }
    private void openLayout(){
        Animation slideUp = AnimationUtils.loadAnimation(getActivity(), R.anim.slide_up);
//                Animation slideDown = AnimationUtils.loadAnimation(getActivity(), R.anim.slide_down);
        if(layout.getVisibility()==View.GONE) {
            layout.startAnimation(slideUp);
            layout.setVisibility(View.VISIBLE);
        }
    }


    public void plansUpdate(int bonus){
        HashMap<String,Object> hashMap = new HashMap();

        hashMap.put("bonus",bonus);
        hashMap.put("earning", MyAccountFragment.earning+bonus);

        mDatabaseRef.child(LoginActivity.phoneNumber).updateChildren(hashMap).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                Toast.makeText(getContext(), "you have received the bonus of " + bonus, Toast.LENGTH_SHORT).show();
                RecordsModel recordsModel = new RecordsModel();
                recordsModel.setMessage("Welcome Dear! " + MyAccountFragment.name + " ,you have received a bonus of " + bonus + "$. Now your total earning is " + MyAccountFragment.total);
                recordsModel.setTime(RecordsFragment.getDateTime());
                recReference.child(LoginActivity.phoneNumber).push().setValue(recordsModel).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(getContext(), "Error:" + e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }
    private void setPlans(){
        plansRef.child("needLocal").setValue("25");
        plansRef.child("needSuper").setValue("50");
        plansRef.child("needSuperior").setValue("100");
        plansRef.child("needManger").setValue("150");
        plansRef.child("needSenior").setValue("200");
        plansRef.child("needGolden").setValue("300");
        plansRef.child("needDiamond").setValue("500");
        plansRef.child("happyTxt").setValue("This is happy Text.");
        plansRef.child("sadTxt").setValue("This is sad Text.");
        plansRef.child("moreTxt").setValue("This is more Text.");

    }
    private void getPlansData(){
        plansListener = plansRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                plansModel = new PlansModel();
                plansModel.setLocal(Integer.valueOf(snapshot.child("local").getValue().toString()));
                plansModel.setSoper(Integer.valueOf(snapshot.child("super").getValue().toString()));
                plansModel.setSuperior(Integer.valueOf(snapshot.child("superior").getValue().toString()));
                plansModel.setManger(Integer.valueOf(snapshot.child("manger").getValue().toString()));
                plansModel.setSenior(Integer.valueOf(snapshot.child("senior").getValue().toString()));
                plansModel.setGolden(Integer.valueOf(snapshot.child("golden").getValue().toString()));
                plansModel.setDiamond(Integer.valueOf(snapshot.child("diamond").getValue().toString()));
                plansModel.setNeedLocal(Integer.valueOf(snapshot.child("needLocal").getValue().toString()));
                plansModel.setNeedSoper(Integer.valueOf(snapshot.child("needSuper").getValue().toString()));
                plansModel.setNeedSuperior(Integer.valueOf(snapshot.child("needSuperior").getValue().toString()));
                plansModel.setNeedManger(Integer.valueOf(snapshot.child("needManger").getValue().toString()));
                plansModel.setNeedSenior(Integer.valueOf(snapshot.child("needSenior").getValue().toString()));
                plansModel.setNeedGolden(Integer.valueOf(snapshot.child("needGolden").getValue().toString()));
                plansModel.setNeedDiamond(Integer.valueOf(snapshot.child("needDiamond").getValue().toString()));
                plansModel.setHappyTxt(snapshot.child("happyTxt").getValue().toString());
                plansModel.setSadTxt(snapshot.child("sadTxt").getValue().toString());
                plansModel.setMoreTxt(snapshot.child("moreTxt").getValue().toString());




            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(getContext(), "" + error.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });

    }

}