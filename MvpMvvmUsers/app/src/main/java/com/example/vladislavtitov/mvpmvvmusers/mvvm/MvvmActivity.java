package com.example.vladislavtitov.mvpmvvmusers.mvvm;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.vladislavtitov.mvpmvvmusers.R;
import com.example.vladislavtitov.mvpmvvmusers.databinding.ActivityMvvmBinding;
import com.example.vladislavtitov.mvpmvvmusers.databinding.UserItemMvvmBinding;
import com.example.vladislavtitov.mvpmvvmusers.models.User;
import com.example.vladislavtitov.mvpmvvmusers.mvvm.viewModels.UserItemViewModel;
import com.example.vladislavtitov.mvpmvvmusers.mvvm.viewModels.UsersViewModel;

import java.util.List;
import java.util.Observable;
import java.util.Observer;

public class MvvmActivity extends AppCompatActivity implements Observer{

    ActivityMvvmBinding binding;
    RecyclerView users;
    UsersMvvmAdapter adapter;
    UsersViewModel usersViewModel;

    public final static String MVVM_LOG_TAG = "mvvm_logs";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_mvvm);

        usersViewModel = new UsersViewModel();
        usersViewModel.addObserver(this);
        binding.setUsersVM(usersViewModel);

        users = binding.usersList;
        adapter = new UsersMvvmAdapter(usersViewModel.getUsers());
        users.setAdapter(adapter);
        users.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    public void update(Observable o, Object arg) {
        if (o instanceof UsersViewModel){
            adapter.updateUsers(((UsersViewModel) o).getUsers());
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 0){
            adapter.updateUsers(usersViewModel.getUsers());
        }
    }

    private class UsersMvvmAdapter extends RecyclerView.Adapter<UserMvvmHolder>{

        List<User> users;

        public UsersMvvmAdapter() {
        }

        public UsersMvvmAdapter(List<User> users) {
            this.users = users;
        }

        public void updateUsers(List<User> newUsers){
            this.users = newUsers;
            notifyDataSetChanged();
        }

        @Override
        public UserMvvmHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            UserItemMvvmBinding userItemMvvmBinding =
                    DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.user_item_mvvm, parent, false);
            return new UserMvvmHolder(userItemMvvmBinding);
        }

        @Override
        public void onBindViewHolder(UserMvvmHolder holder, int position) {
            holder.bindUser(users.get(position), position);
        }

        @Override
        public int getItemCount() {
            return users.size();
        }
    }

    private class UserMvvmHolder extends RecyclerView.ViewHolder{

        UserItemMvvmBinding userBinding;

        public UserMvvmHolder(UserItemMvvmBinding itemBinding) {
            super(itemBinding.itemView);
            this.userBinding = itemBinding;
        }

        public void bindUser(User user, int position){
            if (userBinding.getUserItemVM() == null){
                userBinding.setUserItemVM(new UserItemViewModel(MvvmActivity.this));
            }
            Log.d(MVVM_LOG_TAG, user.getSurname() + " " +user.getName());
            userBinding.getUserItemVM().setUser(user);
            userBinding.getUserItemVM().setPosition(position);

        }
    }

}
