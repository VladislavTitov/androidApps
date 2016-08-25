package com.example.vladislav.historybyplaces;


import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class FinishActivity extends AppCompatActivity {

    LinearLayout linearLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_finish);

        Toast.makeText(this, "FinishActivity start", Toast.LENGTH_SHORT).show();

        linearLayout = (LinearLayout) findViewById(R.id.ll_finish);

        Intent intent = getIntent();
        if (intent.getBooleanExtra("finish", false)){
            linearLayout.setBackgroundResource(R.drawable.background_win);
        }else{
            linearLayout.setBackgroundResource(R.drawable.background_lose);
        }

        linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

}
