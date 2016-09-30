package com.therishka.androidlab_2;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.therishka.androidlab_2.models.VkNewsItem;
import com.therishka.androidlab_2.network.RxVk;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

public class NewsActivity extends AppCompatActivity {

    ProgressBar progressBar;
    RecyclerView recyclerView;
    NewsAdapter newsAdapter;

    final String myTag = "MyLog";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);

        progressBar = (ProgressBar) findViewById(R.id.news_loading_view);
        recyclerView = (RecyclerView) findViewById(R.id.news_list);

        newsAdapter = new NewsAdapter(this);
        recyclerView.setAdapter(newsAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        showLoading();
        RxVk api = new RxVk();
        api.getNews(new RxVk.RxVkListener<LinkedList<VkNewsItem>>() {
            @Override
            public void requestFinished(LinkedList<VkNewsItem> requestResult) {
                newsAdapter.setmNewsList(requestResult);
                showFriends();
            }
        });

    }

    private void showLoading(){
        recyclerView.setVisibility(View.GONE);
        progressBar.setVisibility(View.VISIBLE);
    }

    private void showFriends(){
        recyclerView.setVisibility(View.VISIBLE);
        progressBar.setVisibility(View.GONE);
    }

    private class NewsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{

        private List<VkNewsItem> mNewsList;
        private Context context;

        public NewsAdapter(Context context) {
            this.context = context;
            notifyDataSetChanged();
        }

        public void setmNewsList(List<VkNewsItem> mNewsList) {
            this.mNewsList = mNewsList;
        }

        @Override
        public NewsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.news_item, parent, false);
            return new NewsViewHolder(view);
        }

        @Override
        public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
            if (holder instanceof NewsActivity.NewsViewHolder) {
                VkNewsItem item = mNewsList.get(position);
                ((NewsViewHolder) holder).bind(item);
                try {
                    Glide.with(context).load(item.getAttachments().get(0).getPhoto().getPhoto_1280())
                            .fitCenter()
                            .into(((NewsViewHolder) holder).news_image);

                    Glide.with(context).load(item.getPublisher().getPhoto_50())
                            .fitCenter()
                            .into(((NewsViewHolder) holder).news_group_image);

                }catch (NullPointerException e){
                    Log.d(myTag, e.getMessage());
                }
                if (item.getAttachments() != null){
                    Log.d(myTag, String.valueOf(item.getAttachments().size()));
                }else {
                    Log.d(myTag, "0");
                }

            }

        }

        @Override
        public int getItemCount() {
            return mNewsList.size();
        }
    }

    private class NewsViewHolder extends RecyclerView.ViewHolder {

        ImageView news_group_image;
        TextView news_group_name;
        TextView news_text;
        ImageView news_image;
        TextView news_date;
        TextView news_count_likes;

        public NewsViewHolder(View itemView) {
            super(itemView);

            news_group_image = (ImageView) itemView.findViewById(R.id.news_group_image);
            news_group_name = (TextView) itemView.findViewById(R.id.news_group_name);
            news_text = (TextView) itemView.findViewById(R.id.news_text);
            news_image = (ImageView) itemView.findViewById(R.id.news_image);
            news_date = (TextView) itemView.findViewById(R.id.news_date_view);
            news_count_likes = (TextView) itemView.findViewById(R.id.news_count_likes);
        }

        public void bind(VkNewsItem item){
            try {
                Date date = new Date(item.getDate()*1000);
                SimpleDateFormat format1 = new SimpleDateFormat("dd.MM.yy hh:mm");

                news_group_name.setText(item.getPublisher().getName());
                news_text.setText(item.getText());
                news_date.setText(format1.format(date));
                news_count_likes.setText(String.valueOf(item.getLikes().getCount()));
            }catch (NullPointerException e){
                Log.d(myTag, e.getMessage());
            }

        }
    }
}
