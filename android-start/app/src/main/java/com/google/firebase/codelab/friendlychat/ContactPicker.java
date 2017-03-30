package com.google.firebase.codelab.friendlychat;

import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;

public class ContactPicker extends AppCompatActivity {

    public final int PICK_CONTACT = 2015;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        (findViewById(R.id.buttonContacts)).setOnClickListener( new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent (Intent.ACTION_PICK, ContactsContract.CommonDataKinds.Email.CONTENT_URI);
                startActivityForResult(intent, PICK_CONTACT);
            }
        });


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
            Log.d("Email Address", cursor.getColumnName(column));
        }
    }

}
