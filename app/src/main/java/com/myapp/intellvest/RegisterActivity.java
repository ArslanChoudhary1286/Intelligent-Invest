package com.myapp.intellvest;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.hbb20.CountryCodePicker;

public class RegisterActivity extends AppCompatActivity {

    CountryCodePicker ccp;

    EditText name, gmail, address, number, password_text;
    private FirebaseAuth mAuth;
    FirebaseUser currentUser;
    private DatabaseReference mDatabaseRef;
    private ValueEventListener mEventListener;
    String name1, gmail1, address1, number1;
    public static String password1;
    public static SharedPreferences.Editor editor;

    public static Activity fa;

    EditText reference;
    String reference1;
    String referenceNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();

        fa = this;
        changeStatusBarColor();

        name = findViewById(R.id.editTextName);
        gmail = findViewById(R.id.editTextEmail);
        address = findViewById(R.id.editTextAddress);
        number = findViewById(R.id.editTextMobile);
        password_text = findViewById(R.id.editTextPassword);
        ccp = findViewById(R.id.registerccp);
        ccp.registerCarrierNumberEditText(number);
        reference = findViewById(R.id.editTextReference);

        mDatabaseRef = FirebaseDatabase.getInstance().getReference("Users");

        mAuth = FirebaseAuth.getInstance();

        SharedPreferences sharedPreferences = getSharedPreferences("number",MODE_PRIVATE);
        editor = sharedPreferences.edit();

//        SharedPreferences sharedPreferences2 = getSharedPreferences("password",MODE_PRIVATE);
//        editor2 = sharedPreferences2.edit();



//        editor2.putString("pasa","-1");
//        editor2.apply();


    }
    private void changeStatusBarColor() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(Color.TRANSPARENT);
            window.setStatusBarColor(getResources().getColor(R.color.register_bk_color));
        }
    }

    public void onLoginClick(View view){
        startActivity(new Intent(this, LoginActivity.class));
        overridePendingTransition(R.anim.slide_in_left,android.R.anim.slide_out_right);

    }

    public void ShowHidePass(View view){

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
    public void registerDone(View view) {

        mDatabaseRef.orderByChild("number").equalTo(ccp.getFullNumberWithPlus()).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists()){

                    Toast.makeText(RegisterActivity.this, "user already exist", Toast.LENGTH_SHORT).show();
                    //  jump to sign in activity
                    startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
                }

                else{
                    name1 = name.getText().toString();
                    gmail1 = gmail.getText().toString();
                    address1 = address.getText().toString();
                    number1 = ccp.getFullNumberWithPlus();
                    password1 = password_text.getText().toString();
                    reference1 = reference.getText().toString();

                    if (!name1.isEmpty() && !gmail1.isEmpty() && !address1.isEmpty() && !number1.isEmpty() && !password1.isEmpty() && !reference1.isEmpty()){
                        if (name1.length()<=20){
                            if (number1.length()==13){
                                if (password1.length()<=8){

                                    mDatabaseRef.orderByChild("referenceCode").equalTo(reference1).addChildEventListener(new  ChildEventListener() {


                                        @Override
                                        public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                                            if (snapshot.exists()){

                                                referenceNumber = snapshot.getKey();
//                                                Toast.makeText(RegisterActivity.this, " The key is " + referenceNumber, Toast.LENGTH_SHORT).show();


                                                LoginActivity.phoneNumber = number1;
                                                Intent intent1 = new Intent(RegisterActivity.this,OTPActivity.class);
                                                intent1.putExtra("name",name1);
                                                intent1.putExtra("email",gmail1);
                                                intent1.putExtra("address",address1);
                                                intent1.putExtra("phoneNumber",number1);
                                                intent1.putExtra("password",password1);
                                                intent1.putExtra("refNumber",referenceNumber);
                                                startActivity(intent1);
                                            }
                                            else {
                                                Toast.makeText(RegisterActivity.this, "You Entered Invalid Reference Code", Toast.LENGTH_SHORT).show();
                                            }

                                        }

                                        @Override
                                        public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

                                        }

                                        @Override
                                        public void onChildRemoved(@NonNull DataSnapshot snapshot) {

                                        }

                                        @Override
                                        public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

                                        }

                                        @Override
                                        public void onCancelled(@NonNull DatabaseError error) {
                                            Toast.makeText(RegisterActivity.this, "Error:" + error.getMessage(), Toast.LENGTH_SHORT).show();
                                        }
                                    });


                                }
                                else{
                                    Toast.makeText(RegisterActivity.this, "enter 8 character password ", Toast.LENGTH_SHORT).show();
                                }


                            }
                            else{
                                Toast.makeText(RegisterActivity.this, "Enter the correct number", Toast.LENGTH_SHORT).show();
                            }
                        }
                        else {
                            Toast.makeText(RegisterActivity.this, "Enter the 20 character name", Toast.LENGTH_SHORT).show();
                        }


                    }else {
                        Toast.makeText(RegisterActivity.this, "please fill the blanks", Toast.LENGTH_SHORT).show();
                    }


                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(RegisterActivity.this, databaseError.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }


}