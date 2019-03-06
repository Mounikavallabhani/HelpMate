package com.arkainfoteck.helpmate.Activitys;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.View;

import com.arkainfoteck.helpmate.R;

public class HelpAndSupport extends AppCompatActivity {
    CardView call,mail,whatsapp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help_and_support);
        call=(CardView)findViewById(R.id.call);
        mail=(CardView)findViewById(R.id.mail);
        whatsapp=(CardView)findViewById(R.id.whatsapp);
        call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:9866333969"));
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                Intent chooser  = Intent.createChooser(intent, "Complete Action using..");
                startActivity(chooser);
            }
        });
        mail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Intent i = new Intent(Intent.ACTION_SENDTO);
                    i.setType("message/rfc822");
                    i.setData(Uri.parse("info@broomsticks.in"));
//                    i.putExtra(Intent.EXTRA_SUBJECT, "subject of email");
//                    i.putExtra(Intent.EXTRA_TEXT   , "body of email");
                    i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(Intent.createChooser(i, "Send mail..."));
                } catch (android.content.ActivityNotFoundException ex) {
                    Log.d("exception", "There are no email clients installed.");
                }
            }
        });
        whatsapp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Intent.ACTION_VIEW,
                        Uri.parse(
                                "https://api.whatsapp.com/send?phone=+9866333969"
                        )));
            }
        });
    }
}
