package com.example.vladislavtitov.mvpmvvmusers.mvp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.util.DiffUtil;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.vladislavtitov.mvpmvvmusers.R;
import com.example.vladislavtitov.mvpmvvmusers.app.MyApplication;
import com.example.vladislavtitov.mvpmvvmusers.models.User;
import com.example.vladislavtitov.mvpmvvmusers.modules.UsersPresenterModule;
import com.example.vladislavtitov.mvpmvvmusers.presenters.UsersPresenter;
import com.example.vladislavtitov.mvpmvvmusers.utils.MyDiffUtilCallback;

import java.util.List;

public class MvpActivity extends AppCompatActivity implements MvpActivityCallback{

    UsersPresenter usersPresenter;

    RecyclerView rvUsers;

    private UsersAdapter usersAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mvp);
        usersPresenter = ((MyApplication)getApplication()).getUsersComponent().usersPresenter();
        usersPresenter.setMvpActivityCallback(this);
        usersPresenter.setContext(this);

        rvUsers = (RecyclerView) findViewById(R.id.rv_users);
        usersAdapter = new UsersAdapter(usersPresenter.getUsers());
        rvUsers.setAdapter(usersAdapter);
        rvUsers.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        usersAdapter.updateUsers(usersPresenter.getUsers());
    }

    @Override
    public void updateUsers(List<User> newUsers) {
        usersAdapter.updateUsers(newUsers);
    }

    private class UsersAdapter extends RecyclerView.Adapter<UserViewHolder>{

        List<User> users;

        public UsersAdapter(List<User> users) {
            this.users = users;
        }

        public void updateUsers(List<User> newUsers){
            DiffUtil.DiffResult diffResult = DiffUtil.calculateDiff(new MyDiffUtilCallback(users, newUsers));
            diffResult.dispatchUpdatesTo(this);
            notifyDataSetChanged(); //TODO ask about trouble with diffResult
        }

        @Override
        public UserViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.user_item_layout, parent, false);
            return new UserViewHolder(view);
        }

        @Override
        public void onBindViewHolder(UserViewHolder holder, final int position) {
            holder.getItemTv().setText(users.get(position).getSurname() + " " + users.get(position).getName());
            holder.getItemView().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent toInfo = new Intent(MvpActivity.this, MvpInfoActivity.class);
                    toInfo.putExtra("position", position);
                    startActivityForResult(toInfo, 0);
                }
            });
        }

        @Override
        public int getItemCount() {
            return users.size();
        }
    }

    private class UserViewHolder extends RecyclerView.ViewHolder{

        private View itemView;
        private TextView itemTv;

        public UserViewHolder(View itemView) {
            super(itemView);
            this.itemView = itemView;
            itemTv = (TextView) itemView.findViewById(R.id.item_tv);
        }

        public View getItemView() {
            return itemView;
        }

        public TextView getItemTv() {
            return itemTv;
        }
    }
}
