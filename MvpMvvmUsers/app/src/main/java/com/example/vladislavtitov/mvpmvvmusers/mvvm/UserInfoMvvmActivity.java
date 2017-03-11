package com.example.vladislavtitov.mvpmvvmusers.mvvm;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.vladislavtitov.mvpmvvmusers.R;
import com.example.vladislavtitov.mvpmvvmusers.databinding.ActivityUserInfoMvvmBinding;
import com.example.vladislavtitov.mvpmvvmusers.mvvm.viewModels.UserInfoViewModel;

public class UserInfoMvvmActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityUserInfoMvvmBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_user_info_mvvm);
        int position = getIntent().getIntExtra("position", 0);
        binding.setUserInfoVM(new UserInfoViewModel(this, position));
    }

    public static void launchUserInfoActivity(MvvmActivity context, int position){
        Intent intent = new Intent(context, UserInfoMvvmActivity.class);
        intent.putExtra("position", position);
        context.startActivityForResult(intent, 0);
    }

}
