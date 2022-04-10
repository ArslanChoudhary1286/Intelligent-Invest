package com.myapp.intellvest;

import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class SettingActivity extends AppCompatActivity {
    EditText changeName, changePassword, changeEmail;
    Button changeDone;
    DatabaseReference mDatabaseRef;
    DatabaseReference recordRef;

    EditText password_text;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);

        changeName = findViewById(R.id.editName);
        changePassword = findViewById(R.id.editPassword);
        changeEmail = findViewById(R.id.editEmail);
        changeDone = findViewById(R.id.changeProfileBtn);
        password_text = findViewById(R.id.editPassword);
        mDatabaseRef = FirebaseDatabase.getInstance().getReference("Users");

        changeName.setText(MyAccountFragment.name);
        changePassword.setText(MyAccountFragment.password);
        changeEmail.setText(MyAccountFragment.email);

        changeDone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String name = changeName.getText().toString();
                String password = changePassword.getText().toString();
                String email = changeEmail.getText().toString();

                if (!name.isEmpty() && !password.isEmpty() && !email.isEmpty()){
                    if (name.length() <= 20){
                        if (password.length() <= 8 ){

                            HashMap<String, Object> hashMap = new HashMap<>();
                            hashMap.put("name",name);
                            hashMap.put("password",password);
                            hashMap.put("gmail",email);

                            mDatabaseRef.child(LoginActivity.phoneNumber).updateChildren(hashMap).addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void aVoid) {
                                    // set message of login
                                    recordRef = FirebaseDatabase.getInstance().getReference("Records");
                                    RecordsModel recordsModel = new RecordsModel();
                                    recordsModel.setMessage("You have successfully changed the settings.");
                                    recordsModel.setTime(RecordsFragment.getDateTime());
                                    recordRef.child(LoginActivity.phoneNumber).push().setValue(recordsModel).addOnFailureListener(new OnFailureListener() {
                                        @Override
                                        public void onFailure(@NonNull Exception e) {
                                            Toast.makeText(SettingActivity.this, "Error:" + e.getMessage(), Toast.LENGTH_SHORT).show();
                                        }
                                    });
                                    Toast.makeText(SettingActivity.this, "Data Successfully Changed", Toast.LENGTH_SHORT).show();
                                    finish();
                                }
                            }).addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    Toast.makeText(SettingActivity.this, "Error:" + e.getMessage(), Toast.LENGTH_SHORT).show();

                                }
                            });

                        }else {
                            Toast.makeText(SettingActivity.this, "password should be less than nine.", Toast.LENGTH_SHORT).show();
                        }
                    }else {
                        Toast.makeText(SettingActivity.this, "Enter the 20 letter name", Toast.LENGTH_SHORT).show();
                    }
                }else {
                    Toast.makeText(SettingActivity.this, "Please Fill the Blanks", Toast.LENGTH_SHORT).show();
                }


            }
        });



    }

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