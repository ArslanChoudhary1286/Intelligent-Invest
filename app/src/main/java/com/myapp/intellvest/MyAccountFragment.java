package com.myapp.intellvest;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.CompositePageTransformer;
import androidx.viewpager2.widget.MarginPageTransformer;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MyAccountFragment extends Fragment {

    DatabaseReference mDatabaseRef, nDatabaseRef;
    private ValueEventListener mDBListener;
    public static float  bonus = 0;
    public static float userWithDrawlAmount, earning = 0, total = 0, afterInvest = 0;
    public static float sp0 = 0, sp1 = 0, sp2 = 0, sp3 = 0, sp4 = 0, sp5 = 0, sp6 = 0,sp7 = 0, sp8 = 0,sp9 = 0, sp10 = 0, sp11 = 0, sp12 = 0, sp13 = 0, sp14 = 0, sp15 = 0, sp16 = 0,sp17 = 0, sp18 = 0,sp19 = 0, sp20 = 0, sp21 = 0, sp22 = 0, sp23 = 0, sp24 = 0, sp25 = 0, sp26 = 0,sp27 = 0, sp28 = 0,sp29 = 0;
    public static boolean p0,p1,p2,p3,p4,p5,p6,p7,p8,p9,p10,p11,p12,p13,p14,p15,p16,p17,p18,p19,p20,p21,p22,p23,p24,p25,p26,p27,p28,p29;
    ImageView investMoney, drawMoney, inviteFriend, setting, logout;
    TextView totalEarningTex, afterInvestTxt, myName;
    public static List<UserModel> list;
    public static String name,email,password,referenceCode;

    ViewPager2 viewPager2;
    SliderAdapter adapter;
    List<String> sliderList;
    TextView[] dots;
    LinearLayout layout;
    private Handler sliderHandler = new Handler();
    static int counter = 0;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_my_account, container, false);

        totalEarningTex = view.findViewById(R.id.earningText);
        afterInvestTxt = view.findViewById(R.id.afterInvest);
        myName = view.findViewById(R.id.myName);
        investMoney = view.findViewById(R.id.investMoney);
        drawMoney = view.findViewById(R.id.drawMoney);
        inviteFriend = view.findViewById(R.id.inviteFriend);
        setting = view.findViewById(R.id.setting);
        logout = view.findViewById(R.id.logout);

        // intialize the Image slider

        viewPager2 = view.findViewById(R.id.image_container);
        layout = view.findViewById(R.id.dots_container);

        mDatabaseRef = FirebaseDatabase.getInstance().getReference("Users");
        nDatabaseRef = FirebaseDatabase.getInstance().getReference("productInformation");

        dots = new TextView[5];
        sliderList = new ArrayList<>();
        sliderList.clear();
        list = new ArrayList<>();


        for (int i = 0; i < 5; i++){

            sliderList.add(counter, LoginActivity.adsList.get(0));
            counter++;
            sliderList.add(counter, LoginActivity.adsList.get(1));
            counter++;
            sliderList.add(counter, LoginActivity.adsList.get(2));
            counter++;
            sliderList.add(counter, LoginActivity.adsList.get(3));
            counter++;
            sliderList.add(counter, LoginActivity.adsList.get(4));
            counter++;
        }
        counter = 0;

        adapter = new SliderAdapter(sliderList);
        viewPager2.setAdapter(adapter);
        viewPager2.setClipToPadding(false);
        viewPager2.setClipChildren(false);
        viewPager2.setOffscreenPageLimit(1);
        viewPager2.getChildAt(0).setOverScrollMode(RecyclerView.OVER_SCROLL_ALWAYS);

        CompositePageTransformer compositePageTransformer = new CompositePageTransformer();
        compositePageTransformer.addTransformer(new MarginPageTransformer(40));
        compositePageTransformer.addTransformer(new ViewPager2.PageTransformer() {
            @Override
            public void transformPage(@NonNull View page, float position) {
                float r = 1-Math.abs(position);
                page.setScaleY(0.8f + r * 0.15f);
            }
        });

        viewPager2.setPageTransformer(compositePageTransformer);
        setIndicators();
        viewPager2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {

                super.onPageSelected(position);
                selectedDots(position);
                sliderHandler.removeCallbacks(sliderRunnable);
                sliderHandler.postDelayed(sliderRunnable, 4000); // slide duration 3 seconds
            }
        });

        investMoney.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(), RechargeActivity.class));
            }
        });
        drawMoney.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(), WithdrawActivity.class));
            }
        });
        inviteFriend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(), InviteActivity.class));
            }
        });
        setting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(), SettingActivity.class));
            }
        });
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LoginActivity.editor.putString("number","1");
                LoginActivity.editor.putString("password","1");
                LoginActivity.editor.apply();
                MainActivity.fa.finish();
                startActivity(new Intent(getContext(), LoginActivity.class));
            }
        });

        getData();

        return view;
    }

    private void getData(){

        mDBListener = mDatabaseRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                list.clear();
                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                    UserModel userModel = postSnapshot.getValue(UserModel.class);
                    userModel.setKey(postSnapshot.getKey());
                    list.add(userModel);
                }

                String userno = LoginActivity.phoneNumber;
                for (int i = 0; i<list.size(); i++){
                    String noChecking = list.get(i).getNumber();
                    if (noChecking.equals(userno)){
                        earning = list.get(i).getEarning();
                        bonus = list.get(i).getBonus();
                        userWithDrawlAmount = list.get(i).getUserWithDrawlsAmount();
                        name = list.get(i).getName();
                        email = list.get(i).getEmail();
                        password = list.get(i).getPassword();

                        total = list.get(i).getTotalEarning();
                        afterInvest= list.get(i).getPp0()+list.get(i).getPp1()+list.get(i).getPp2()+list.get(i).getPp3()+list.get(i).getPp4()+list.get(i).getPp5()+list.get(i).getPp6()+list.get(i).getPp7()+list.get(i).getPp8()+list.get(i).getPp9()+list.get(i).getPp10()+list.get(i).getPp11()+list.get(i).getPp12()+list.get(i).getPp13()+list.get(i).getPp14()+list.get(i).getPp15()+list.get(i).getPp16()+list.get(i).getPp17()+list.get(i).getPp18()+list.get(i).getPp19()+list.get(i).getPp20()+list.get(i).getPp21()+list.get(i).getPp22()+list.get(i).getPp23()+list.get(i).getPp24()+list.get(i).getPp25()+list.get(i).getPp26()+list.get(i).getPp27()+list.get(i).getPp28()+list.get(i).getPp29();

                        referenceCode = list.get(i).getReferenceCode();

                        sp0 = list.get(i).getSp0();
                        sp1 = list.get(i).getSp1();
                        sp2 = list.get(i).getSp2();
                        sp3 = list.get(i).getSp3();
                        sp4 = list.get(i).getSp4();
                        sp5 = list.get(i).getSp5();
                        sp6 = list.get(i).getSp6();
                        sp7 = list.get(i).getSp7();
                        sp8 = list.get(i).getSp8();
                        sp9 = list.get(i).getSp9();
                        sp10 = list.get(i).getSp10();
                        sp11 = list.get(i).getSp11();
                        sp12 = list.get(i).getSp12();
                        sp13 = list.get(i).getSp13();
                        sp14 = list.get(i).getSp14();
                        sp15 = list.get(i).getSp15();
                        sp16 = list.get(i).getSp16();
                        sp17 = list.get(i).getSp17();
                        sp18 = list.get(i).getSp18();
                        sp19 = list.get(i).getSp19();
                        sp20 = list.get(i).getSp20();
                        sp21 = list.get(i).getSp21();
                        sp22 = list.get(i).getSp22();
                        sp23 = list.get(i).getSp23();
                        sp24 = list.get(i).getSp24();
                        sp25 = list.get(i).getSp25();
                        sp26 = list.get(i).getSp26();
                        sp27 = list.get(i).getSp27();
                        sp28 = list.get(i).getSp28();
                        sp29 = list.get(i).getSp29();


                        p0 = list.get(i).isP0();
                        p1 = list.get(i).isP1();
                        p2 = list.get(i).isP2();
                        p3 = list.get(i).isP3();
                        p4 = list.get(i).isP4();
                        p5 = list.get(i).isP5();
                        p6 = list.get(i).isP6();
                        p7 = list.get(i).isP7();
                        p8 = list.get(i).isP8();
                        p9 = list.get(i).isP9();
                        p10 = list.get(i).isP10();
                        p11 = list.get(i).isP11();
                        p12 = list.get(i).isP12();
                        p13 = list.get(i).isP13();
                        p14 = list.get(i).isP14();
                        p15 = list.get(i).isP15();
                        p16 = list.get(i).isP16();
                        p17 = list.get(i).isP17();
                        p18 = list.get(i).isP18();
                        p19 = list.get(i).isP19();
                        p20 = list.get(i).isP20();
                        p21 = list.get(i).isP21();
                        p22 = list.get(i).isP22();
                        p23 = list.get(i).isP23();
                        p24 = list.get(i).isP24();
                        p25 = list.get(i).isP25();
                        p26 = list.get(i).isP26();
                        p27 = list.get(i).isP27();
                        p28 = list.get(i).isP28();
                        p29 = list.get(i).isP29();
                    }
                }


                total = earning;
                afterInvest = total - afterInvest;

                myName.setText(name);
                totalEarningTex.setText(String.valueOf(total) +" $");
                afterInvestTxt.setText(String.valueOf(afterInvest) + " $");

                HashMap<String,Object> hashMap = new HashMap();
                hashMap.put("totalEarning",total);

                mDatabaseRef.child(userno).updateChildren(hashMap).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
//                        Toast.makeText(getContext(), "Data up", Toast.LENGTH_SHORT).show();
                    }
                });

            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
                Toast.makeText(getContext(), databaseError.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void selectedDots(int position) {
        for (int i = 0; i < dots.length; i++) {

            if (position >= 5 && position < 10)
                position = position-5;
            else if (position >= 10 && position < 15)
                position = position-10;
            else if (position >= 15 && position < 20)
                position = position-15;
            else if (position >= 20)
                position = position-20;

            if (i == position) {
                dots[i].setTextColor(getResources().getColor(R.color.black));
                dots[i].setAlpha((float) 0.3);
            }
            else {
                dots[i].setTextColor(getResources().getColor(R.color.black));
                dots[i].setAlpha((float) 1);
            }
        }
    }

    private void setIndicators() {
        for (int i = 0; i < dots.length; i++) {
            dots[i] = new TextView(getContext());
            dots[i].setText(Html.fromHtml("&#9679;"));
            dots[i].setTextSize(18);
            layout.addView(dots[i]);
        }

    }
    private Runnable sliderRunnable = new Runnable() {
        @Override
        public void run() {
            viewPager2.setCurrentItem(viewPager2.getCurrentItem() + 1,true);
        }
    };
    @Override
    public void onPause() {
        super.onPause();
        sliderHandler.removeCallbacks(sliderRunnable);
    }

    @Override
    public void onResume() {
        super.onResume();
        sliderHandler.postDelayed(sliderRunnable, 3000);
    }

}