package com.example.vladislav.numberlist;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.vladislav.numberlist.contacts.ContactsProvider;

public class NewUserActivity extends AppCompatActivity {

    EditText phone;
    EditText recipient;
    Button add;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_user);

        phone = (EditText) findViewById(R.id.add_number);
        recipient = (EditText) findViewById(R.id.add_recipient);
        add = (Button) findViewById(R.id.add);

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String phoneNumber = phone.getText().toString();
                String recipientName = recipient.getText().toString();

                /**
                 * Here I must write adding contact into list of contacts
                 */

                ContactsProvider.getInstance().addNewUser(getApplicationContext(), phoneNumber, recipientName);
                Log.d(ContactsProvider.MY_TAG, "This is NewUserActivity");
                finish();
            }
        });

    }
}
