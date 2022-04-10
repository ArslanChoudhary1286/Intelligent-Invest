package com.myapp.intellvest;

import android.content.ContentResolver;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.StorageTask;
import com.google.firebase.storage.UploadTask;

public class DisplayActivity extends AppCompatActivity {
    ImageView displayImageView, displayImageSend;
    DatabaseReference mDatabaseRef,nDatabaseRef;
    private StorageTask mUploadTask;
    private StorageReference mStorageRef;
    ChattingModel chattingModel;
    Uri img;
    TextView progressTxt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display);
        getSupportActionBar().setTitle("Upload Image");

        displayImageView = findViewById(R.id.displayImageView);
        displayImageSend = findViewById(R.id.displayImageSend);
        progressTxt = findViewById(R.id.progressBar);

        img = Uri.parse(getIntent().getStringExtra("uri"));

        displayImageView.setImageURI(img);

        displayImageSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                uploadFile();
            }
        });

    }

    private String getFileExtension(Uri uri) {
        ContentResolver cR = getContentResolver();
        MimeTypeMap mime = MimeTypeMap.getSingleton();
        return mime.getExtensionFromMimeType(cR.getType(uri));
    }
    private void uploadFile() {
        mStorageRef = FirebaseStorage.getInstance().getReference("screenShots");

        StorageReference fileReference = mStorageRef.child(System.currentTimeMillis()
                + "." + getFileExtension(img));
        mUploadTask = fileReference.putFile(img)
                .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                        fileReference.getDownloadUrl().addOnCompleteListener(new OnCompleteListener<Uri>() {
                            @Override
                            public void onComplete(@NonNull Task<Uri> task) {
                                String profileImageUrl=task.getResult().toString();

                                mDatabaseRef = FirebaseDatabase.getInstance().getReference("chatting");
                                chattingModel = new ChattingModel();
                                chattingModel.setMessageUser(MyAccountFragment.name);
                                chattingModel.setMessageTime(RecordsFragment.getDateTime());
                                chattingModel.setImageUri(img.toString());
                                chattingModel.setImageUrl(profileImageUrl);

                                mDatabaseRef.child(LoginActivity.phoneNumber).push().setValue(chattingModel).addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void aVoid) {
                                        finish();

                                    }
                                }).addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure(@NonNull Exception e) {
                                        Toast.makeText(DisplayActivity.this, "Error:" + e.getMessage(), Toast.LENGTH_SHORT).show();
                                    }
                                });
                            }
                        });


                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(DisplayActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                })
                .addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {
                        double progress = (100.0 * taskSnapshot.getBytesTransferred() / taskSnapshot.getTotalByteCount());
                        progressTxt.setText(String.valueOf(progress) + " %");
                    }
                });
    }
}