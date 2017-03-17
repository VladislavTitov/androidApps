package com.example.vladislavtitov.randomusermvvm.viewModels;

import android.content.Context;
import android.databinding.BindingAdapter;
import android.widget.ImageView;

import com.example.vladislavtitov.randomusermvvm.R;
import com.example.vladislavtitov.randomusermvvm.models.Location;
import com.example.vladislavtitov.randomusermvvm.models.Result;
import com.example.vladislavtitov.randomusermvvm.utils.UsersProvider;
import com.squareup.picasso.Picasso;

public class UserDetailsViewModel {

    private Context context;
    private final int position;
    private final Result user;

    public UserDetailsViewModel(Context context, int position) {
        this.context = context;
        this.position = position;
        this.user = UsersProvider.getUsers(context).get(position);
    }

    public String getTitle(){
        return user.getName().getFirst() + " " + user.getName().getLast();
    }

    public String getAddress(){
        Location location = user.getLocation();
        String address = "Street: " + location.getStreet() + "\n" +
                        "City: " + location.getCity() + "\n" +
                        "State: " + location.getState() + "\n" +
                        "Postcode: " + location.getPostcode();
        return address;
    }

    public String getContacts(){
        String contacts = "Username: " + user.getLogin().getUsername() + "\n" +
                "Phone: " + user.getPhone() + "\n" +
                "Email: " + user.getEmail();
        return contacts;
    }

    public String getPhotoUrl(){
        return user.getPicture().getLarge();
    }

    @BindingAdapter("android:src")
    public static void loadImage(ImageView imageView, String url){
        Picasso.with(imageView.getContext())
                .load(url)
                .error(R.mipmap.ic_launcher)
                .into(imageView);
    }

}
