package com.example.listapp;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by Vladislav on 17.08.2016.
 */
public class LessonAdapter extends RecyclerView.Adapter<LessonAdapter.LessonViewHolder> {

    private ArrayList<Lesson> mLessons;
    Context context;

    public LessonAdapter(Context context, ArrayList<Lesson> mLessons) {
        this.context = context;
        this.mLessons = mLessons;
    }

    @Override
    public LessonViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(
                R.layout.item_lesson,
                parent,
                false);
        return new LessonViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final LessonViewHolder holder, int position) {
        final Lesson lesson = mLessons.get(position);
        holder.tvName.setText(lesson.getmName());

        /*holder.tvTime.setText(lesson.getmTime());
        holder.tvRoom.setText(lesson.getmRoom());
        holder.tvTeacher.setText(lesson.getmTeacher());*/

        holder.ivPicture.setImageResource(R.drawable.cloud);

        holder.view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, InsideActivity.class);
                intent.putExtra("name", lesson.getmName());
                intent.putExtra("info", "" + lesson.getmTime() + "\n" + lesson.getmRoom() + "\n" + lesson.getmTeacher());
                context.startActivity(intent);

            }
        });
    }

    @Override
    public int getItemCount() {
        return mLessons.size();
    }





    public class LessonViewHolder extends RecyclerView.ViewHolder{

        TextView tvName;
        TextView tvTime;
        TextView tvRoom;
        TextView tvTeacher;
        ImageView ivPicture;
        View view;

        public LessonViewHolder(View itemView) {
            super(itemView);

            view = itemView;

            tvName = (TextView) itemView.findViewById(R.id.tv_name);
            /*tvTime = (TextView) itemView.findViewById(R.id.tv_time);
            tvRoom = (TextView) itemView.findViewById(R.id.tv_room);
            tvTeacher = (TextView) itemView.findViewById(R.id.tv_teacher);*/
            ivPicture = (ImageView) itemView.findViewById(R.id.iv);

        }
    }

}
