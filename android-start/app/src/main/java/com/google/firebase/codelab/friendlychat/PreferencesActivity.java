package com.google.firebase.codelab.friendlychat;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.net.Uri;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

public class PreferencesActivity extends AppCompatActivity {

    public final int PICK_CONTACT = 2015;
    private int numberOfToasts = 1;

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
        ArrayAdapter<CharSequence> notificationNumAdapter = ArrayAdapter.createFromResource(this,R.array.numOfNotifcations,android.R.layout.simple_spinner_item);
        notificationNumAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        notificationsNumSpinner.setAdapter(notificationNumAdapter);

        notificationsNumSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String itemSelectedSpinner = parent.getItemAtPosition(position).toString();
                numberOfToasts = Integer.parseInt(itemSelectedSpinner);
                addToSharedPreferences(numberOfToasts);
                displayToast();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                    numberOfToasts = 1;
                addToSharedPreferences(numberOfToasts);
            }

        });
    }


    public void addToSharedPreferences (int numOfNotifications)
    {
        SharedPreferences sharedPreferences = getSharedPreferences("NumOfToasts",Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt("Toasts",numOfNotifications);
        editor.commit();
    }

    public void displayToast ()
    {
        Toast.makeText(this,"Your preference was saved successfully ",Toast.LENGTH_LONG).show();
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
            SharedPreferences sharedPreferences = getSharedPreferences("MyFavorites", Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString("Email Address",cursor.getString(column));

            editor.commit();
            Toast.makeText(this,"Contact was added successfully ", Toast.LENGTH_LONG).show();
        }
    }


}
