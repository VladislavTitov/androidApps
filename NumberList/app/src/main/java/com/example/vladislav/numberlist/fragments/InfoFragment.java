package com.example.vladislav.numberlist.fragments;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.vladislav.numberlist.R;
import com.example.vladislav.numberlist.contacts.ContactsProvider;

public class InfoFragment extends Fragment {

    TextView tvNameFragment;
    TextView tvNumberFragment;
    Button btnSendFragment;

    String name;
    String number;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null){
            name = getArguments().getString("name");
            number = getArguments().getString("number");
        }else {
            name = null;
            number = null;
        }

        Log.d(ContactsProvider.MY_TAG, "This is Info fragment!");
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_pager_info, container, false);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        tvNameFragment = (TextView) view.findViewById(R.id.recipient_info_fragment);
        tvNumberFragment = (TextView) view.findViewById(R.id.number_info_fragment);
        btnSendFragment = (Button) view.findViewById(R.id.btn_send_fragment);

        tvNumberFragment.setText(number);
        tvNameFragment.setText(name);

        btnSendFragment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String toSms = "smsto:" + number;
                Intent sms = new Intent(Intent.ACTION_SENDTO, Uri.parse(toSms));
                startActivity(sms);
            }
        });

    }
}
