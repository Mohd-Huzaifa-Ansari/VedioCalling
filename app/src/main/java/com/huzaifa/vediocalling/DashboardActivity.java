package com.huzaifa.vediocalling;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import org.jitsi.meet.sdk.JitsiMeet;
import org.jitsi.meet.sdk.JitsiMeetActivity;
import org.jitsi.meet.sdk.JitsiMeetConferenceOptions;

import java.net.MalformedURLException;
import java.net.URL;

public class DashboardActivity extends AppCompatActivity {

    EditText meetIdBox;
    Button joinbtn,sharebtn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        meetIdBox = findViewById(R.id.codeBox);
        joinbtn = findViewById(R.id.joinBtn);
        sharebtn = findViewById(R.id.shareBtn);

        URL serverUrl;

        try {
            serverUrl = new URL("https://meet.jit.si");


            JitsiMeetConferenceOptions defaultOptions =
                    new JitsiMeetConferenceOptions.Builder()
                            .setServerURL(serverUrl)
                            .setWelcomePageEnabled(false)
                            .build();
            JitsiMeet.setDefaultConferenceOptions(defaultOptions);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }


        joinbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                JitsiMeetConferenceOptions options = new JitsiMeetConferenceOptions.Builder()
                        .setRoom(meetIdBox.getText().toString())
                        .setWelcomePageEnabled(false)
                        .setAudioMuted(true)
                        .setVideoMuted(true)
                        .build();

                JitsiMeetActivity.launch(DashboardActivity.this,options);

            }
        });

        sharebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Now share text only function will be called
                // here we  will be passing the text to share
                shareTextOnly(meetIdBox.getText().toString());
            }

            private void shareTextOnly(String titlee) {

                String sharebody = titlee;

                // The value which we will sending through data via
                // other applications is defined
                // via the Intent.ACTION_SEND
                Intent intentt = new Intent(Intent.ACTION_SEND);

                // setting type of data shared as text
                intentt.setType("text/plain");
                intentt.putExtra(Intent.EXTRA_SUBJECT, "Subject Here This is virtual classroom meeting Id ");

                // Adding the text to share using putExtra
                intentt.putExtra(Intent.EXTRA_TEXT, "This is the Virtual Classroom meeting Id :--> " +sharebody+ " <--: from Maharana Pratap Polytechnic.");
                startActivity(Intent.createChooser(intentt, "Share Via"));
            }
        });
    }
}