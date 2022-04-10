package com.myapp.intellvest;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.view.WindowManager;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.hbb20.CountryCodePicker;

import java.util.ArrayList;
import java.util.List;

public class LoginActivity extends AppCompatActivity {

    CountryCodePicker ccp;

    public static SharedPreferences.Editor editor;
    DatabaseReference mDatabaseRef, nDatabaseRef;
    EditText number, password_text;

    private ValueEventListener nDBListener,adsDBListener;
    public static List<ProductsModel> productList;

    CheckBox checkBox;
    public static String phoneNumber,Password;
    DatabaseReference recordRef,adsReference;
    public static List<String> adsList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_login);

        ccp = (CountryCodePicker) findViewById(R.id.loginccp);
        number = findViewById(R.id.loginEditTextMobile);
        password_text = findViewById(R.id.loginEditTextPassword);
        ccp.registerCarrierNumberEditText(number);
        checkBox = findViewById(R.id.checkBox);


        productList = new ArrayList<>();
        mDatabaseRef = FirebaseDatabase.getInstance().getReference("Users");
        nDatabaseRef = FirebaseDatabase.getInstance().getReference("productInformation");
        recordRef = FirebaseDatabase.getInstance().getReference("Records");
        adsReference = FirebaseDatabase.getInstance().getReference("ADSInformation");


        SharedPreferences sharedPreferences = getSharedPreferences("loginInformation",MODE_PRIVATE);
        editor = sharedPreferences.edit();
        updateProducts();

//         set values to the ads
//        adsReference.push().setValue("i");
//        adsReference.push().setValue("i");
//        adsReference.push().setValue("i");
//        adsReference.push().setValue("i");
//        adsReference.push().setValue("i");

        getAdsData();



    }
    private void getAdsData(){

        adsDBListener = adsReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                adsList = new ArrayList<>();
                for (DataSnapshot postSnapshot : snapshot.getChildren()){
                    adsList.add(postSnapshot.getValue().toString());
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
    private void updateProducts(){
        nDBListener = nDatabaseRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                productList.clear();
                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                    ProductsModel productsModel = postSnapshot.getValue(ProductsModel.class);
                    productsModel.setKey(postSnapshot.getKey());
                    productList.add(productsModel);
                }
//                mAdapter.notifyDataSetChanged();
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
                Toast.makeText(LoginActivity.this, databaseError.getMessage(), Toast.LENGTH_SHORT).show();
//                mProgressCircle.setVisibility(View.INVISIBLE);
            }
        });
    }

    public void onLoginClick(View View){
        startActivity(new Intent(this,RegisterActivity.class));
        overridePendingTransition(R.anim.slide_in_right,R.anim.stay);

    }
    @Override
    public void onStart() {
        super.onStart();

//
        SharedPreferences sharedPreferences = getSharedPreferences("loginInformation",MODE_PRIVATE);
        String phoneNumber = sharedPreferences.getString("number","1");
        String password = sharedPreferences.getString("password","1");

        if (phoneNumber.equals("1") && password.equals("1")){

            checkBox.setChecked(false);
        }
        else {
            checkBox.setChecked(true);
            if (isInternetConnection()){
                registeredUser(phoneNumber,password);
            }
            else {
                Toast.makeText(this, "You have not an internet connection.", Toast.LENGTH_SHORT).show();
            }

        }

    }
    public  boolean isInternetConnection()
    {

        ConnectivityManager connectivityManager = (ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);
        if(connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState() == NetworkInfo.State.CONNECTED ||
                connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState() == NetworkInfo.State.CONNECTED) {
            //we are connected to a network
            return true;
        }
        else
            return false;
    }

    public void LoginDone(View view){

        String phoneNumber = ccp.getFullNumberWithPlus();
        String password = password_text.getText().toString();

        if (!phoneNumber.isEmpty() && !phoneNumber.isEmpty()){
            if (phoneNumber.length() == 13){
                if (password.length()<=8){

                    if (isInternetConnection()){
                        registeredUser(phoneNumber,password);
                    }
                    else {
                        Toast.makeText(this, "You have not an internet connection.", Toast.LENGTH_SHORT).show();
                    }

                }
                else{
                    Toast.makeText(this, "incorrect password", Toast.LENGTH_SHORT).show();
                }
            }
            else {
                Toast.makeText(this, "wrong number", Toast.LENGTH_SHORT).show();
            }
        }
        else {
            Toast.makeText(this, "Fill the blanks", Toast.LENGTH_SHORT).show();
        }
    }

    private void registeredUser(String number1,String password){

        mDatabaseRef.orderByChild("number").equalTo(number1).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists()){
                    mDatabaseRef.child(number1).addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {

                            String pass = snapshot.child("password").getValue().toString();

                            if (pass.equals(password)){

                                if (checkBox.isChecked()){
                                    editor.putString("number",number1);
                                    editor.putString("password",password);
                                    editor.apply();
                                }
                                phoneNumber = number1;
                                Password = password;
                                // set message of login
                                RecordsModel recordsModel = new RecordsModel();
                                recordsModel.setMessage("You have successfully logged on to Investing App.For mor information, Please contact us in chatting section.");
                                recordsModel.setTime(RecordsFragment.getDateTime());
                                recordRef.child(phoneNumber).push().setValue(recordsModel).addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure(@NonNull Exception e) {
                                        Toast.makeText(LoginActivity.this, "Error:" + e.getMessage(), Toast.LENGTH_SHORT).show();
                                    }
                                });


                                startActivity(new Intent(LoginActivity.this, MainActivity.class));
                            }
                            else {
                                Toast.makeText(LoginActivity.this, "password incorrect", Toast.LENGTH_SHORT).show();
                            }

                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }

                    });
                }

                else{
                    Toast.makeText(LoginActivity.this, "user not exist", Toast.LENGTH_SHORT).show();
                    //  jump to the register Activity
                    startActivity(new Intent(LoginActivity.this,RegisterActivity.class));
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(LoginActivity.this, databaseError.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
//
//    public void LoginShowHidePass(View view){
//
//        if(view.getId()==R.id.show_pass_btn){
//
//            if(password_text.getTransformationMethod().equals(PasswordTransformationMethod.getInstance())){
//                ((ImageView)(view)).setImageResource(R.drawable.hide_password);
//
//                //Show Password
//                password_text.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
//            }
//            else{
//                ((ImageView)(view)).setImageResource(R.drawable.show_password);
//
//                //Hide Password
//                password_text.setTransformationMethod(PasswordTransformationMethod.getInstance());
//
//            }
//        }
//    }

    public void ShowHidePass(View view) {

        if(view.getId()==R.id.show_pass_btn){

            if(password_text.getTransformationMethod().equals(PasswordTransformationMethod.getInstance())){
                ((ImageView)(view)).setImageResource(R.drawable.hide_password);

                //Show Password
                password_text.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
            }
            else{
                ((ImageView)(view)).setImageResource(R.drawable.show_password);

                //Hide Password
                password_text.setTransformationMethod(PasswordTransformationMethod.getInstance());

            }
        }
    }
}