package com.example.intenttesting;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class FirstActivity extends AppCompatActivity implements View.OnClickListener{

    private EditText et_name;
    private Button btn1;
    private Button btn2;
    private Button btn3;
    private Button btn_start_intent;
    private String number = "0";

    private int count1 = 0;
    private int count2 = 0;
    private int count3 = 0;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);

        et_name = (EditText) findViewById(R.id.et_enter_text);
        btn1 = (Button) findViewById(R.id.btn1);
        btn2 = (Button) findViewById(R.id.btn2);
        btn3 = (Button) findViewById(R.id.btn3);
        btn_start_intent = (Button) findViewById(R.id.btn_start_intent);

        btn1.setOnClickListener(this);
        btn2.setOnClickListener(this);
        btn3.setOnClickListener(this);

        btn_start_intent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(FirstActivity.this, SecondActivity.class);
                intent.putExtra("text", et_name.getText().toString());
                intent.putExtra("number", number);
                intent.putExtra("count", "" + count1 + " " + count2 + " " + count3);
                startActivity(intent);
            }
        });


    }

    @Override
    public void onClick(View view) {
        Button btn = (Button) view;
        number = btn.getText().toString();

        switch (btn.getText().toString()){
            case "1":
                count1++;
                break;
            case "2":
                count2++;
                break;
            case "3":
                count3++;
                break;
        }
    }
}
