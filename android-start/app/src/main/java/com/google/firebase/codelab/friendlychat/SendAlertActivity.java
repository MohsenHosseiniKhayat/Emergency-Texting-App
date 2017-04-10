package com.google.firebase.codelab.friendlychat;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.media.audiofx.BassBoost;
import android.os.Build;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.google.android.gms.ads.MobileAds;
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
    LocationListener locListener;
    LocationManager myManager;
    private String locationCordinates = "";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send_alert);

        myManager = (LocationManager) getSystemService(LOCATION_SERVICE);
        locListener = new LocationListener() {
            @Override
            public void onLocationChanged(Location location) {
                //locationCordinates += "\n" + location.getLatitude() + " " + location.getLongitude();
            }

            @Override
            public void onStatusChanged(String s, int i, Bundle bundle) {
            }

            @Override
            public void onProviderEnabled(String s) {
            }

            @Override
            public void onProviderDisabled(String s) {
                Intent i = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                startActivity(i);
            }
        };


        //
        mFirebaseAuth = FirebaseAuth.getInstance();
        mFirebaseUser = mFirebaseAuth.getCurrentUser();
        Button accidentButton = (Button) findViewById(R.id.buttonAccident);
        Button assaultButton = (Button) findViewById(R.id.buttonAssault);
        Button disasterButton = (Button) findViewById(R.id.buttonDisaster);
        Button safeButton = (Button) findViewById(R.id.buttonSafe);

        mFirebaseDatabaseReference = FirebaseDatabase.getInstance().getReference();
        //final String dummyText = "Dummy";
        final String ACCIDENT = "is involved in an ACCIDENT";
        final String ASSAULT = "is being Robbed/Assaulted";
        final String NATURALDISASTER = "is affected by a NATURAL DISASTER";
        final String SAFE = "is now SAFE";
        final ArrayList<String> dummyContacts = new ArrayList<>();

        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        myManager.requestLocationUpdates("gps", 1000, 5, locListener);
        accidentButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Display dialog fragment after success

                String accidentMessage = mUsername + " " + ACCIDENT;
                try {
                FriendlyMessage friendlyMessage = new FriendlyMessage(accidentMessage, mUsername, mPhotoUrl,dummyContacts,myManager.getLastKnownLocation(LocationManager.GPS_PROVIDER));
                mFirebaseDatabaseReference.child(MESSAGES_CHILD).push().setValue(friendlyMessage);
                }
                catch (SecurityException se)
                {
                    Log.wtf("SendAlertActivity", "User should already have given permission: " + se.toString());
                }
            }
        });

        assaultButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Display dialog fragment after success

                String assaultMessage = mUsername + " " + ASSAULT;
                try {
                    FriendlyMessage friendlyMessage = new FriendlyMessage(assaultMessage, mUsername, mPhotoUrl, dummyContacts, myManager.getLastKnownLocation(LocationManager.GPS_PROVIDER));
                    mFirebaseDatabaseReference.child(MESSAGES_CHILD).push().setValue(friendlyMessage);
                }
                catch (SecurityException se)
                {
                    Log.wtf("SendAlertActivity", "User should already have given permission: " + se.toString());
                }
            }
        });

        disasterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Display dialog fragment after success

                String disasterMessage = mUsername + " " + NATURALDISASTER;
                try {
                    FriendlyMessage friendlyMessage = new FriendlyMessage(disasterMessage, mUsername, mPhotoUrl, dummyContacts, myManager.getLastKnownLocation(LocationManager.GPS_PROVIDER));
                    mFirebaseDatabaseReference.child(MESSAGES_CHILD).push().setValue(friendlyMessage);
                }
                catch (SecurityException se)
                {
                    Log.wtf("SendAlertActivity", "User should already have given permission: " + se.toString());
                }
            }
        });

        safeButton.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                String safetyMessage = mUsername + " " + SAFE;
                try {
                    FriendlyMessage friendlyMessage = new FriendlyMessage(safetyMessage, mUsername, mPhotoUrl, dummyContacts, myManager.getLastKnownLocation(LocationManager.GPS_PROVIDER));
                    mFirebaseDatabaseReference.child(MESSAGES_CHILD).push().setValue(friendlyMessage);
                }
                catch (SecurityException se)
                {
                    Log.wtf("SendAlertActivity", "User should already have given permission: " + se.toString());
                }
            }
        });
    }
}
