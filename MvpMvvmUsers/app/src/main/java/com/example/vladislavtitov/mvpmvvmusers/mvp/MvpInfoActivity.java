package com.example.vladislavtitov.mvpmvvmusers.mvp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.vladislavtitov.mvpmvvmusers.R;
import com.example.vladislavtitov.mvpmvvmusers.app.MyApplication;
import com.example.vladislavtitov.mvpmvvmusers.models.User;
import com.example.vladislavtitov.mvpmvvmusers.presenters.UserInfoPresenter;

public class MvpInfoActivity extends AppCompatActivity {

    int position;

    UserInfoPresenter userInfoPresenter;

    EditText editName;
    EditText editSurname;
    EditText editEmail;

    Button btnSave;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mvp_info);
        userInfoPresenter = ((MyApplication) getApplication()).getUsersComponent().usersInfoPresenter();
        position = getIntent().getIntExtra("position", 0);

        editName = (EditText) findViewById(R.id.edit_name);
        editSurname = (EditText) findViewById(R.id.edit_surname);
        editEmail = (EditText) findViewById(R.id.edit_email);

        btnSave = (Button) findViewById(R.id.btn_save);

        User user = userInfoPresenter.getUser(position);
        editName.setText(user.getName());
        editSurname.setText(user.getSurname());
        editEmail.setText(user.getEmail());

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userInfoPresenter.save(position, new User(editName.getText().toString(), editSurname.getText().toString(),
                        editEmail.getText().toString()));
                finish();
            }
        });
    }
}
