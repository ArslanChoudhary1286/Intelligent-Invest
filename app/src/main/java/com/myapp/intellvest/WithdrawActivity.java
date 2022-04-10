package com.myapp.intellvest;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class WithdrawActivity extends AppCompatActivity {
    Button doneBtn;
    EditText withDrawl, withDrawlAmount;
    DatabaseReference mDatabaseRef;
    DatabaseReference recordRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_withdraw);
        getSupportActionBar().hide();


        doneBtn = findViewById(R.id.doneBtn);
        withDrawl = findViewById(R.id.withDrawls);
        withDrawlAmount = findViewById(R.id.withDrawlsAmount);

        mDatabaseRef = FirebaseDatabase.getInstance().getReference("myWithDrawls");




        doneBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (withDrawlAmount.getText().toString().trim().length() > 0 && withDrawl.getText().toString().trim().length() > 0){
                    int amount = Integer.parseInt(withDrawlAmount.getText().toString());
                    if (MyAccountFragment.userWithDrawlAmount == 0){
                        if (MyAccountFragment.afterInvest == MyAccountFragment.total){
                            if (amount <= MyAccountFragment.afterInvest){

                                mDatabaseRef = FirebaseDatabase.getInstance().getReference("Users");
                                HashMap<String,Object> hashMap = new HashMap<>();
                                hashMap.put("userWithDrawlsAmount",Float.parseFloat(withDrawlAmount.getText().toString()));
                                hashMap.put("userWithDrawlsAddress",withDrawl.getText().toString());
                                hashMap.put("earning",(MyAccountFragment.total-Float.parseFloat(withDrawlAmount.getText().toString())));
//                                hashMap.put("teamCommission",0);
//                                hashMap.put("bonus",0);
//                                Toast.makeText(withdrawMoney.this, "a" + (MyAccountFragment.total-Float.parseFloat(withDrawlAmount.getText().toString())) , Toast.LENGTH_SHORT).show();

                                mDatabaseRef.child(LoginActivity.phoneNumber).updateChildren(hashMap).addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void aVoid) {
                                        Toast.makeText(WithdrawActivity.this, "you will get amount in 24hrs", Toast.LENGTH_SHORT).show();
                                        // set message of login
                                        recordRef = FirebaseDatabase.getInstance().getReference("Records");
                                        RecordsModel recordsModel = new RecordsModel();
                                        recordsModel.setMessage("You have successfully applied for Withdraw of "+ Float.parseFloat(withDrawlAmount.getText().toString()) +"$. You will receive a confirmation message according to withdraw policies.");
                                        recordsModel.setTime(RecordsFragment.getDateTime());
                                        withDrawl.setText("");
                                        withDrawlAmount.setText("");
                                        recordRef.child(LoginActivity.phoneNumber).push().setValue(recordsModel).addOnFailureListener(new OnFailureListener() {
                                            @Override
                                            public void onFailure(@NonNull Exception e) {

                                                Toast.makeText(WithdrawActivity.this, "Error:" + e.getMessage(), Toast.LENGTH_SHORT).show();
                                            }
                                        });

                                    }
                                });

                            }
                            else {
                                Toast.makeText(WithdrawActivity.this, "you entered invalid amount", Toast.LENGTH_SHORT).show();
                            }

                        }else {
                            Toast.makeText(WithdrawActivity.this, "First recover all the invest", Toast.LENGTH_SHORT).show();
                            // jump to the recycler view
                        }
                    }else {
                        Toast.makeText(WithdrawActivity.this, "your with Drawl already in pending", Toast.LENGTH_SHORT).show();
                    }
                }else {
                    Toast.makeText(WithdrawActivity.this, "fill the blanks", Toast.LENGTH_SHORT).show();
                }

//                mDatabaseRef = FirebaseDatabase.getInstance().getReference("Users");
//                HashMap<String,Object> hashMap = new HashMap<>();
//                hashMap.put("userWithDrawls",withDrawl.getText().toString());
//
//                mDatabaseRef.child("+923011247964").updateChildren(hashMap).addOnSuccessListener(new OnSuccessListener<Void>() {
//                    @Override
//                    public void onSuccess(Void aVoid) {
//                        Toast.makeText(getContext(), "you will get amount in 24hrs", Toast.LENGTH_SHORT).show();
//
//                    }
//                });

            }
        });

    }
}