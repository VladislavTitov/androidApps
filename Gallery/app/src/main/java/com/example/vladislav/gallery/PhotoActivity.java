package com.example.vladislav.gallery;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

public class PhotoActivity extends AppCompatActivity {

    ImageView iv_photo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photo);

        iv_photo = (ImageView) findViewById(R.id.iv_photo);

        Picasso.with(this).load(getIntent().getIntExtra("photo", getResources().getIdentifier("error", "drawable", getPackageName()))).into(iv_photo);
    }
}
