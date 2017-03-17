package com.example.vladislavtitov.randomusermvvm.activities;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.vladislavtitov.randomusermvvm.R;
import com.example.vladislavtitov.randomusermvvm.databinding.ActivityUserDetailsBinding;
import com.example.vladislavtitov.randomusermvvm.viewModels.UserDetailsViewModel;

public class UserDetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityUserDetailsBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_user_details);
        binding.setDetailsVM(new UserDetailsViewModel(this, getIntent().getIntExtra("position", 0)));

        setSupportActionBar(binding.detailsToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        binding.detailsCollapseToolbar.setCollapsedTitleTextColor(Color.WHITE);
        binding.detailsCollapseToolbar.setExpandedTitleColor(Color.WHITE);
    }

    public static void launchDetails(Context context, int position){
        Intent intent = new Intent(context, UserDetailsActivity.class);
        intent.putExtra("position", position);
        context.startActivity(intent);
    }
}
