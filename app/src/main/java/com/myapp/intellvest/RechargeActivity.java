package com.myapp.intellvest;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class RechargeActivity extends AppCompatActivity {

    DatabaseReference mDatabaseRef;
    EditText withdrawText;
    ImageView copyWithDrawTxt;
    String copyWithDraw;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recharge);
        getSupportActionBar().hide();

        withdrawText = findViewById(R.id.withDrawCopyTxt);
        copyWithDrawTxt = findViewById(R.id.copyTextImg);

        mDatabaseRef = FirebaseDatabase.getInstance().getReference("myWithDrawls");

//        mDatabaseRef.child("withDrawl").setValue("My withdrawl Account");

        mDatabaseRef.child("withDrawl").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                copyWithDraw = (String) snapshot.getValue();
                withdrawText.setText(copyWithDraw);
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
                Toast.makeText(RechargeActivity.this, "Error:" + databaseError.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });



        copyWithDrawTxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                setClipboard(RechargeActivity.this,copyWithDraw);
                Toast.makeText(RechargeActivity.this, "Data copied to clipboard", Toast.LENGTH_SHORT).show();


            }
        });




    }

    public static void setClipboard(Context context, String text) {
        if(android.os.Build.VERSION.SDK_INT < android.os.Build.VERSION_CODES.HONEYCOMB) {
            android.text.ClipboardManager clipboard = (android.text.ClipboardManager) context.getSystemService(Context.CLIPBOARD_SERVICE);
            clipboard.setText(text);
        } else {
            android.content.ClipboardManager clipboard = (android.content.ClipboardManager) context.getSystemService(Context.CLIPBOARD_SERVICE);
            android.content.ClipData clip = android.content.ClipData.newPlainText("Copied Text", text);
            clipboard.setPrimaryClip(clip);
        }
    }
}