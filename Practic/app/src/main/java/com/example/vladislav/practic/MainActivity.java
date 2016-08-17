package com.example.vladislav.practic;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    EditText et_name;
    Button btn_set_text;
    Button btn_regular;
    TextView tv_text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        et_name = (EditText) findViewById(R.id.et_first);
        btn_set_text = (Button) findViewById(R.id.btn_set_text);
        tv_text = (TextView) findViewById(R.id.tv_text);
        btn_regular = (Button) findViewById(R.id.btn_regular);

        btn_set_text.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tv_text.setText(et_name.getText());
            }
        });


    }
}
