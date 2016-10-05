package com.therishka.androidlab_2;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.therishka.androidlab_2.models.VkFriend;
import com.therishka.androidlab_2.network.RxVk;

import java.util.List;

public class FriendsActivityOptimised extends AppCompatActivity {

    ProgressBar mProgress;
    RecyclerView mRecyclerList;
    RecyclerFriendsAdapter mFriendsAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_friends_optimised);

        mProgress = (ProgressBar) findViewById(R.id.loading_view);
        mRecyclerList = (RecyclerView) findViewById(R.id.friends_list);
        mFriendsAdapter = new RecyclerFriendsAdapter(this);
        mFriendsAdapter.setOnItemClickListener(new ItemsClickListener() {
            @Override
            public void onItemClick(String name, int color) {
                Intent intent = new Intent(FriendsActivityOptimised.this, ItemActivity.class);
                intent.putExtra("name", name);
                if (color == Color.BLACK){
                    intent.putExtra("color", "black");
                }else if (color == Color.WHITE){
                    intent.putExtra("color", "white");
                }
                startActivity(intent);
            }
        });
        mRecyclerList.setAdapter(mFriendsAdapter);
        mRecyclerList.setLayoutManager(new LinearLayoutManager(this));

        getFriendsAndShowThem();
    }

    private void getFriendsAndShowThem() {
        showLoading();
        RxVk api = new RxVk();
        api.getFriends(new RxVk.RxVkListener<List<VkFriend>>() {
            @Override
            public void requestFinished(List<VkFriend> requestResult) {
                mFriendsAdapter.setFriendsList(requestResult);
                showFriends();
            }
        });
    }

    private void showLoading() {
        mRecyclerList.setVisibility(View.GONE);
        mProgress.setVisibility(View.VISIBLE);
    }

    private void showFriends() {
        mRecyclerList.setVisibility(View.VISIBLE);
        mProgress.setVisibility(View.GONE);
    }

    @SuppressWarnings("WeakerAccess")
    private class RecyclerFriendsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

        private List<VkFriend> mFriendsList;
        private Context mContext;
        ItemsClickListener itemsClickListener;

        public RecyclerFriendsAdapter(@NonNull Context context) {
            mContext = context;
        }

        public void setFriendsList(@Nullable List<VkFriend> friendsList) {
            mFriendsList = friendsList;
            notifyDataSetChanged();
        }

        public void setOnItemClickListener(ItemsClickListener itemClickListener){
            this.itemsClickListener = itemClickListener;
        }

        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = null;
            LayoutInflater inflater =LayoutInflater.from(parent.getContext());
            RecyclerView.ViewHolder holder;
            switch (viewType){
                case 0:
                    view = inflater.inflate(R.layout.friend1_item, parent, false);
                    holder = new FriendsViewHolder1(view);
                    break;
                case 1:
                    view = inflater.inflate(R.layout.friend2_item, parent, false);
                    holder = new FriendsViewHolder1(view);
                    break;
                default:
                    view = inflater.inflate(R.layout.friend1_item, parent, false);
                    holder = new FriendsViewHolder1(view);
                    break;
            }
            return holder;
        }

        @Override
        public int getItemViewType(int position) {
            return position % 2;
        }

        @Override
        public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
            if (holder instanceof FriendsViewHolder1) {
                VkFriend friend = mFriendsList.get(position);
                ((FriendsViewHolder1) holder).bind(friend, itemsClickListener);
                Glide.with(mContext).load(friend.getSmallPhotoUrl())
                        .fitCenter()
                        .into(((FriendsViewHolder1) holder).avatar);
            }else if (holder instanceof FriendsViewHolder2){
                VkFriend friend = mFriendsList.get(position);
                ((FriendsViewHolder2) holder).bind(friend, itemsClickListener);
                Glide.with(mContext).load(friend.getSmallPhotoUrl())
                        .fitCenter()
                        .into(((FriendsViewHolder2) holder).avatar);
            }
        }

        @Override
        public int getItemCount() {
            return mFriendsList != null ? mFriendsList.size() : 0;
        }
    }

    @SuppressWarnings("WeakerAccess")
    private class FriendsViewHolder1 extends RecyclerView.ViewHolder {

        TextView fullName;
        ImageView avatar;
        View isOnline;
        View itemView;

        public FriendsViewHolder1(View itemView) {
            super(itemView);

            this.itemView = itemView;
            fullName = (TextView) itemView.findViewById(R.id.full_name);
            avatar = (ImageView) itemView.findViewById(R.id.avatar);
            isOnline = itemView.findViewById(R.id.is_user_online);
        }

        public void bind(final VkFriend friend, final ItemsClickListener itemsClickListener) {
            fullName.setText(friend.getName() + " " + friend.getSurname());
            fullName.setTextColor(Color.BLACK);
            isOnline.setVisibility(friend.isOnline() ? View.VISIBLE : View.GONE);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    itemsClickListener.onItemClick(friend.getName() + " " + friend.getSurname(), Color.BLACK);
                }
            });
        }
    }

    private class FriendsViewHolder2 extends RecyclerView.ViewHolder{

        TextView fullName;
        ImageView avatar;
        View isOnline;
        View itemView;

        public FriendsViewHolder2(View itemView) {
            super(itemView);

            this.itemView = itemView;
            fullName = (TextView) itemView.findViewById(R.id.full_name);
            avatar = (ImageView) itemView.findViewById(R.id.avatar);
            isOnline = itemView.findViewById(R.id.is_user_online);
        }

        public void bind(final VkFriend friend, final ItemsClickListener itemsClickListener) {
            fullName.setText(friend.getName() + " " + friend.getSurname());
            fullName.setTextColor(Color.WHITE);
            isOnline.setVisibility(friend.isOnline() ? View.VISIBLE : View.GONE);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    itemsClickListener.onItemClick(friend.getName() + " " + friend.getSurname(), Color.WHITE);
                }
            });
        }
    }

}
