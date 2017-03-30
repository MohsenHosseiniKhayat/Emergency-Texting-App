package com.google.firebase.codelab.friendlychat;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

import static com.google.firebase.codelab.friendlychat.MainActivity.MESSAGES_CHILD;

public class SendAlertActivity extends AppCompatActivity {

    private DatabaseReference mFirebaseDatabaseReference;
    private String mUsername;
    private String mPhotoUrl;
    private FirebaseAuth mFirebaseAuth;
    private FirebaseUser mFirebaseUser;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send_alert);

        //
        mFirebaseAuth = FirebaseAuth.getInstance();
        mFirebaseUser = mFirebaseAuth.getCurrentUser();
        Button button1 = (Button) findViewById(R.id.buttonOne);
        Button button2 = (Button) findViewById(R.id.buttonTwo);
        Button button3 = (Button) findViewById(R.id.buttonThree);

        mFirebaseDatabaseReference = FirebaseDatabase.getInstance().getReference();
        final String dummyText = "Dummy";
        final ArrayList<String> dummyContacts = new ArrayList<>();

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Display dialog fragment

                FriendlyMessage friendlyMessage = new FriendlyMessage(dummyText, mUsername, mPhotoUrl,dummyContacts);
                mFirebaseDatabaseReference.child(MESSAGES_CHILD).push().setValue(friendlyMessage);
            }
        });

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Display dialog fragment

                FriendlyMessage friendlyMessage = new FriendlyMessage(dummyText, mUsername, mPhotoUrl,dummyContacts);
                mFirebaseDatabaseReference.child(MESSAGES_CHILD).push().setValue(friendlyMessage);
            }
        });

        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Display dialog fragment

                FriendlyMessage friendlyMessage = new FriendlyMessage(dummyText, mUsername, mPhotoUrl,dummyContacts);
                mFirebaseDatabaseReference.child(MESSAGES_CHILD).push().setValue(friendlyMessage);
            }
        });
    }
}
