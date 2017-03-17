package com.example.vladislavtitov.randomusermvvm.viewModels;

import android.content.Context;
import android.databinding.BaseObservable;
import android.databinding.ObservableField;
import android.widget.ImageView;

import com.example.vladislavtitov.randomusermvvm.R;
import com.example.vladislavtitov.randomusermvvm.activities.UserDetailsActivity;
import com.example.vladislavtitov.randomusermvvm.models.Result;
import com.squareup.picasso.Picasso;

public class ItemUserViewModel extends BaseObservable {

    public final ObservableField<String> name = new ObservableField<>();
    public final ObservableField<String> username = new ObservableField<>();
    private ImageView itemPhoto;
    private int position;
    private Context context;

    public ItemUserViewModel() {
    }

    public ItemUserViewModel(Context context) {
        this.context = context;
    }

    public void update(Result result, ImageView itemPhoto, int position){
        this.name.set(result.getName().getFirst() + " " + result.getName().getLast());
        this.username.set(result.getLogin().getUsername());
        this.itemPhoto = itemPhoto;
        this.position = position;
        loadPhoto(this.itemPhoto, result.getPicture().getMedium());
    }

    private void loadPhoto(ImageView itemPhoto, String url){
        Picasso.with(itemPhoto.getContext())
                .load(url)
                .error(R.mipmap.ic_launcher)
                .into(itemPhoto);
    }

    public void toDetails(){
        UserDetailsActivity.launchDetails(context, position);
    }

}
