package com.therishka.androidlab_2;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.therishka.androidlab_2.models.VkDialog;
import com.therishka.androidlab_2.models.VkDialogResponse;
import com.therishka.androidlab_2.network.RxVk;

import java.util.List;

public class DialogsActivity extends AppCompatActivity {

    ProgressBar mProgressBar;
    RecyclerView recyclerView;
    RecyclerDialogsAdapter dialogsAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dialogs);

        mProgressBar = (ProgressBar) findViewById(R.id.diag_loading_view);
        recyclerView = (RecyclerView) findViewById(R.id.dialogs_list);
        dialogsAdapter = new RecyclerDialogsAdapter(this);
        recyclerView.setAdapter(dialogsAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        showLoading();
        RxVk api = new RxVk();
        api.getDialogs(new RxVk.RxVkListener<VkDialogResponse>() {
            @Override
            public void requestFinished(VkDialogResponse requestResult) {
                dialogsAdapter.setmDialogList(requestResult);
                showFriends();
            }
        });

    }

    private void showLoading() {
        recyclerView.setVisibility(View.GONE);
        mProgressBar.setVisibility(View.VISIBLE);
    }

    private void showFriends() {
        recyclerView.setVisibility(View.VISIBLE);
        mProgressBar.setVisibility(View.GONE);
    }

    private class RecyclerDialogsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{

        private List<VkDialog> mDialogList;
        private Context context;

        public RecyclerDialogsAdapter(Context context) {
            this.context = context;
            notifyDataSetChanged();
        }

        public void setmDialogList(VkDialogResponse mDialogList) {
            this.mDialogList = mDialogList.getDialogs();
        }

        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.dialog_item, parent, false);
            return new DialogsViewHolder(view);
        }

        @Override
        public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
            if (holder instanceof DialogsActivity.DialogsViewHolder) {
                VkDialog dialog = mDialogList.get(position);
                ((DialogsActivity.DialogsViewHolder) holder).bind(dialog);
                Glide.with(context).load(dialog.getPhoto())
                        .fitCenter()
                        .into(((DialogsViewHolder) holder).iv_dialog_avatar);
            }
        }

        @Override
        public int getItemCount() {
            if (mDialogList != null){
                return mDialogList.size();
            }else return 0;
        }
    }

    private class DialogsViewHolder extends RecyclerView.ViewHolder{

        TextView tv_dialog;
        ImageView iv_dialog_avatar;

        public DialogsViewHolder(View itemView) {
            super(itemView);

            tv_dialog = (TextView) itemView.findViewById(R.id.dialog_name);
            iv_dialog_avatar = (ImageView) itemView.findViewById(R.id.dialog_avatar);

        }

        public void bind(VkDialog dialog){
            tv_dialog.setText(dialog.getUsername());

        }
    }
}
