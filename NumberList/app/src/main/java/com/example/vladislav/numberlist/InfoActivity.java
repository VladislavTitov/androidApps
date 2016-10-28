package com.example.vladislav.numberlist;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.vladislav.numberlist.contacts.Contact;
import com.example.vladislav.numberlist.contacts.ContactsProvider;

public class InfoActivity extends AppCompatActivity {

    TextView tvNumber;
    TextView tvName;
    Button btnSend;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);

        tvName = (TextView) findViewById(R.id.recipient_info);
        tvNumber = (TextView) findViewById(R.id.number_info);
        btnSend = (Button) findViewById(R.id.btn_send);

        tvName.setText(getIntent().getStringExtra("name"));
        tvNumber.setText(getIntent().getStringExtra("number"));

        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String toSms = "smsto:" + getIntent().getStringExtra("number");
                Intent sms = new Intent(Intent.ACTION_SENDTO, Uri.parse(toSms));
                startActivity(sms);
            }
        });

        Log.d(ContactsProvider.MY_TAG, "This is Info ACTIVITY");
    }
}
