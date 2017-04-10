package com.google.firebase.codelab.friendlychat;

import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Spinner;

public class PreferencesActivity extends AppCompatActivity {

    public final int PICK_CONTACT = 2015;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preferences);

        Button contactsButton = (Button) findViewById(R.id.buttonContacts);
        contactsButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick (View v)
            {
                Intent intent = new Intent (Intent.ACTION_PICK, ContactsContract.CommonDataKinds.Email.CONTENT_URI);
                startActivityForResult(intent, PICK_CONTACT);
            }
        });

        Spinner notificationsNumSpinner = (Spinner) findViewById(R.id.spinnerNumOfNotifications);
        int [] nums = {5,10,20,50};

    }

    @Override
    protected void onActivityResult (int requestCode, int resultCode, Intent data)
    {
        if (requestCode == PICK_CONTACT && resultCode == RESULT_OK)
        {
            Uri contractUri = data.getData();
            Cursor cursor = getContentResolver().query(contractUri,null, null,null, null);
            cursor.moveToFirst();
            int column = cursor.getColumnIndex(ContactsContract.CommonDataKinds.Email.ADDRESS);
            Log.d("Email Address", cursor.getString(column));
        }
    }


}
