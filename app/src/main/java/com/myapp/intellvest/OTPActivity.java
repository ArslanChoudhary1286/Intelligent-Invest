package com.myapp.intellvest;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.chaos.view.PinView;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthOptions;
import com.google.firebase.auth.PhoneAuthProvider;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.concurrent.TimeUnit;

public class OTPActivity extends AppCompatActivity {

    //    EditText getOtp;
//    SimpleOTP otp;
    PinView otp;
    Button verify;
    TextView otpTxt;
    private FirebaseAuth mAuth;
    String otpId;
    private DatabaseReference mDatabaseRef;
    DatabaseReference recordRef;
    String referenceNumber, referenceEarning;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_o_t_p);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();

        otp = findViewById(R.id.edit_Text);
        verify = findViewById(R.id.verify);
        otpTxt = findViewById(R.id.otpTxt);
        mAuth = FirebaseAuth.getInstance();
        mDatabaseRef = FirebaseDatabase.getInstance().getReference("Users");

        referenceNumber = getIntent().getStringExtra("refNumber");
        getReferenceEarning();

        intiateotp();

        verify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (otp.getText().toString().isEmpty()){
                    Toast.makeText(OTPActivity.this, "Blank Field can not be processed", Toast.LENGTH_SHORT).show();
                }
                else if (otp.getText().toString().length() <= 5 ){
                    Toast.makeText(OTPActivity.this, "Invalid otp", Toast.LENGTH_SHORT).show();
                }
                else {
                    if (otpId != null){
                        PhoneAuthCredential credential = PhoneAuthProvider.getCredential(otpId,otp.getText().toString());
                        signInWithPhoneAuthCredential(credential);
                    }
                    else {
                        Toast.makeText(OTPActivity.this, "Invalid otp", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }

    private void intiateotp() {
//        Toast.makeText(this, "no is " + getIntent().getStringExtra("phoneNumber"), Toast.LENGTH_SHORT).show();

        PhoneAuthOptions options =
                PhoneAuthOptions.newBuilder(mAuth)
                        .setPhoneNumber(getIntent().getStringExtra("phoneNumber"))       // Phone number to verify
                        .setTimeout(60L, TimeUnit.SECONDS) // Timeout and unit
                        .setActivity(this)                 // Activity (for callback binding)
                        .setCallbacks(new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
                            @Override
                            public void onCodeSent(@NonNull String s, @NonNull PhoneAuthProvider.ForceResendingToken forceResendingToken) {
                                Toast.makeText(OTPActivity.this,"code sent",Toast.LENGTH_SHORT).show();
                                otpTxt.setText("We Sent,\n You a OTP\n on your Phone Number");
                                otpId = s;
                            }

                            @Override
                            public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential) {
                                Toast.makeText(OTPActivity.this,"success",Toast.LENGTH_SHORT).show();
                                otpTxt.setText("Verification Completed");
                                signInWithPhoneAuthCredential(phoneAuthCredential);

                            }

                            @Override
                            public void onVerificationFailed(@NonNull FirebaseException e) {
                                Toast.makeText(OTPActivity.this,e.getMessage(),Toast.LENGTH_SHORT).show();
                                Toast.makeText(OTPActivity.this,"Failed",Toast.LENGTH_SHORT).show();

                            }
                        })          // OnVerificationStateChangedCallbacks
                        .build();
        PhoneAuthProvider.verifyPhoneNumber(options);

    }
    private void signInWithPhoneAuthCredential(PhoneAuthCredential credential) {
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            dataSetToFirebase();
                            startActivity(new Intent(OTPActivity.this, MainActivity.class));
                            finish();
                            RegisterActivity.fa.finish();

                        } else {
                            Toast.makeText(OTPActivity.this,task.getException().getMessage(),Toast.LENGTH_SHORT).show();
                            Log.d("error",task.getException().getMessage());
                        }
                    }
                });
    }
    private void dataSetToFirebase(){
        // Read from the database
        UserModel userModel = new UserModel();
        FirebaseUser currentUser = mAuth.getCurrentUser();

        userModel.setName(getIntent().getStringExtra("name"));
        userModel.setEmail(getIntent().getStringExtra("email"));
        userModel.setAddress(getIntent().getStringExtra("address"));
        userModel.setNumber(getIntent().getStringExtra("phoneNumber"));
        userModel.setPassword(getIntent().getStringExtra("password"));
        userModel.setEarning(0);
//        userModel.setTeamCommission(3);
        userModel.setBonus(0);
        userModel.setTotalEarning(0);
        userModel.setReferenceCode(currentUser.getUid());


        mDatabaseRef.child(currentUser.getPhoneNumber()).setValue(userModel);
        Toast.makeText(OTPActivity.this, "uploaded", Toast.LENGTH_SHORT).show();

        //  set reference earning

        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put("earning",Float.valueOf(referenceEarning)+3);
        mDatabaseRef.child(referenceNumber).updateChildren(hashMap).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
//                Toast.makeText(OTPActivity.this, "commission is set", Toast.LENGTH_SHORT).show();
                // set message of login
                recordRef = FirebaseDatabase.getInstance().getReference("Records");
                RecordsModel recordsModel = new RecordsModel();
                recordsModel.setMessage("Dear User you get a commission of 3$ by increasing your team member");
                recordsModel.setTime(RecordsFragment.getDateTime());
                recordRef.child(referenceNumber).push().setValue(recordsModel).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(OTPActivity.this, "Error:" + e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });



        // set message of login
        recordRef = FirebaseDatabase.getInstance().getReference("Records");
        RecordsModel recordsModel = new RecordsModel();
        recordsModel.setMessage("Welcome, You have successfully Registered Account on " + currentUser.getPhoneNumber() + ". For mor information, Please contact us in chatting section.");
        recordsModel.setTime(RecordsFragment.getDateTime());
        recordRef.child(currentUser.getPhoneNumber()).push().setValue(recordsModel).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(OTPActivity.this, "Error:" + e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

        LoginActivity.editor.putString("number",currentUser.getPhoneNumber());
        LoginActivity.editor.putString("password",getIntent().getStringExtra("password"));
        LoginActivity.editor.apply();

    }

    private void getReferenceEarning(){
        mDatabaseRef.child(referenceNumber).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
//                referenceEarning = snapshot.child("earning").getValue().toString();
                Toast.makeText(OTPActivity.this, "earn is " + referenceEarning, Toast.LENGTH_SHORT).show();
            }


            @Override
            public void onCancelled(@NonNull DatabaseError error) {

                Toast.makeText(OTPActivity.this, "" + error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

}