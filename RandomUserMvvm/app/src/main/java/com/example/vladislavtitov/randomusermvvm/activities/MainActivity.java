package com.example.vladislavtitov.randomusermvvm.activities;

import android.content.SharedPreferences;
import android.databinding.DataBindingUtil;
import android.graphics.Color;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.BaseTransientBottomBar;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.vladislavtitov.randomusermvvm.R;
import com.example.vladislavtitov.randomusermvvm.databinding.ActivityMainBinding;
import com.example.vladislavtitov.randomusermvvm.databinding.ItemUserBinding;
import com.example.vladislavtitov.randomusermvvm.models.Result;
import com.example.vladislavtitov.randomusermvvm.utils.UsersProvider;
import com.example.vladislavtitov.randomusermvvm.viewModels.ItemUserViewModel;
import com.example.vladislavtitov.randomusermvvm.viewModels.MainViewModel;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import android.os.Handler;

public class MainActivity extends AppCompatActivity implements Observer,
        SwipeRefreshLayout.OnRefreshListener, SharedPreferences.OnSharedPreferenceChangeListener{

    private UsersListAdapter adapter;
    private SwipeRefreshLayout swipeRefreshLayout;
    private MainViewModel mainViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Log.d("my_logs", "MainActivity.getSharedPreferences(...)");
        getSharedPreferences(UsersProvider.PREFS_NAME, MODE_PRIVATE).registerOnSharedPreferenceChangeListener(this);

        ActivityMainBinding mainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        this.mainViewModel = new MainViewModel(this);
        mainBinding.setMainVM(this.mainViewModel);
        this.mainViewModel.addObserver(this);

        setSupportActionBar(mainBinding.mainToolbar);

        swipeRefreshLayout = mainBinding.refresh;
        swipeRefreshLayout.setColorSchemeColors(Color.RED, Color.GREEN, Color.BLUE, Color.CYAN);
        swipeRefreshLayout.setOnRefreshListener(this);


        RecyclerView usersList = mainBinding.usersList;
        adapter = new UsersListAdapter();
        if (!getApplicationContext().getSharedPreferences(UsersProvider.PREFS_NAME, MODE_PRIVATE).getString("users", "empty").equals("empty")){
            Log.d("my_logs", "MainActivity.onCreate().adapter.updateUsers()");
            adapter.updateUsers(mainBinding.getMainVM().getUsers());
        }
        usersList.setAdapter(adapter);
        usersList.setLayoutManager(new LinearLayoutManager(this));

        checkConnection(mainBinding.refresh);

    }

    private void checkConnection(View view){
        if (!mainViewModel.hasConnection()){
            Snackbar.make(view, "Check your internet connection!", BaseTransientBottomBar.LENGTH_LONG).show();
        }
    }

    @Override
    public void update(Observable o, Object arg) {
        if (o instanceof MainViewModel) {
            adapter.updateUsers(((MainViewModel) o).getUsers());
        }
    }

    @Override
    public void onRefresh() {
        Log.d("my_logs", "MainActivity.onRefresh() start");
        new Handler().post(new Runnable() {
            @Override
            public void run() {
                UsersProvider.loadUsers(MainActivity.this);
                Log.d("my_logs", "MainActivity.onRefresh() finish");
            }
        });
    }

    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
        Log.d("my_logs", "onSharedPreferenceChanged");
        adapter.updateUsers(mainViewModel.getUsers());
    }

    private class UsersListAdapter extends RecyclerView.Adapter<UserHolder>{

        private List<Result> results;

        public UsersListAdapter() {
            this.results = new ArrayList<>();
            Log.d("my_logs", "Constructor of UsersListAdapter");
        }

        public void updateUsers(List<Result> newResults){
            this.results = newResults;
            notifyDataSetChanged();
            swipeRefreshLayout.setRefreshing(false);
            Log.d("my_logs", "UsersListAdapter.updateUsers()");
        }

        @Override
        public UserHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            ItemUserBinding binding =
                    DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.item_user, parent, false);
            return new UserHolder(binding);
        }

        @Override
        public void onBindViewHolder(UserHolder holder, int position) {
            holder.bindUser(results.get(position), position);
        }

        @Override
        public int getItemCount() {
            return results.size();
        }
    }

    private class UserHolder extends RecyclerView.ViewHolder{

        private ItemUserBinding itemUserBinding;

        public UserHolder(ItemUserBinding binding) {
            super(binding.itemUser);
            itemUserBinding = binding;
            if (itemUserBinding.getItemUserVM() == null){
                itemUserBinding.setItemUserVM(new ItemUserViewModel(MainActivity.this));
            }
        }

        public void bindUser(Result result, int position){
            itemUserBinding.getItemUserVM().update(result, itemUserBinding.itemPhoto, position);
        }
    }

}
