package com.google.firebase.codelab.friendlychat;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class PreferencesActivity extends AppCompatActivity {

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
                viewContents(v);
            }
        });
    }

    public void viewContents (View view)
    {
        Intent chooseContacts = new Intent (this, ContactPicker.class);
        startActivity(chooseContacts);
    }
}
