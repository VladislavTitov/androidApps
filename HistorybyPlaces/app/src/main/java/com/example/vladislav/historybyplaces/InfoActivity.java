package com.example.vladislav.historybyplaces;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class InfoActivity extends AppCompatActivity {

    LinearLayout ll_info;
    TextView tv_info;
    ImageView iv_info;
    TextView tv_description;

    private static final String TAG = "myLogs";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);

        ll_info = (LinearLayout) findViewById(R.id.ll_info);
        tv_info = (TextView) findViewById(R.id.tv_info);
        iv_info = (ImageView) findViewById(R.id.iv_info);
        tv_description = (TextView) findViewById(R.id.tv_description);

        tv_info.setTypeface(GameActivity.cormorant_regular);
        tv_description.setTypeface(GameActivity.cormorant_regular);

        Intent intent = getIntent();
        tv_info.setText(intent.getStringExtra("result"));
        iv_info.setImageResource(intent.getIntExtra("id_image", R.drawable.error));
        tv_description.setText(intent.getStringExtra("info"));

        ll_info.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (GameActivity.points == GameActivity.POINTS_MAX){
                    Intent intent1 = new Intent(InfoActivity.this, FinishActivity.class);
                    intent1.putExtra("finish", true);
                    startActivity(intent1);
                }else if (GameActivity.hp == GameActivity.HP_MIN){
                    Intent intent1 = new Intent(InfoActivity.this, FinishActivity.class);
                    intent1.putExtra("finish", false);
                    startActivity(intent1);
                }
                finish();
            }
        });
    }

}
