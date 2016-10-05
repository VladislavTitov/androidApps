package com.example.vladislav.gallery;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import java.util.LinkedList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    RecyclerView rv_gallery;
    List<String> photos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        photos = new LinkedList<>();
        fillPhotos();

        rv_gallery = (RecyclerView) findViewById(R.id.rv_gallery);
        rv_gallery.setLayoutManager(new GridLayoutManager(this, 2));
        rv_gallery.setAdapter(new GalleryAdapter(photos));

    }

    private void fillPhotos(){
        for (int i = 0; i < 12; i++) {
            photos.add("photo" + i);
        }
    }

    private class GalleryAdapter extends RecyclerView.Adapter<GalleryViewHolder>{

        List<String> gallery_list;

        public GalleryAdapter(List<String> gallery_list) {
            this.gallery_list = gallery_list;
        }

        @Override
        public GalleryViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.gallery_item, parent, false);
            return new GalleryViewHolder(view);
        }

        @Override
        public void onBindViewHolder(GalleryViewHolder holder, int position) {

            final int res_photo = getResources().getIdentifier("photo" + position, "drawable", getPackageName());

            Picasso.with(MainActivity.this)
                    .load(res_photo)
                    //.fit()
                    .into(holder.getIv_image());

            holder.getIv_image().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(MainActivity.this, PhotoActivity.class);
                    intent.putExtra("photo", res_photo);
                    startActivity(intent);
                }
            });
/*
            holder.getIv_image().setImageResource(getResources().getIdentifier("photo" + position, "drawable", getPackageName()));
*/

        }

        @Override
        public int getItemCount() {
            return gallery_list.size();
        }
    }

    private class GalleryViewHolder extends RecyclerView.ViewHolder{

        ImageView iv_image;

        public GalleryViewHolder(View itemView) {
            super(itemView);
            iv_image = (ImageView) itemView.findViewById(R.id.iv_image);
        }

        public ImageView getIv_image() {
            return iv_image;
        }
    }
}
