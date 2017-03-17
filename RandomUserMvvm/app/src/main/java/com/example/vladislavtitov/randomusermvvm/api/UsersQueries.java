package com.example.vladislavtitov.randomusermvvm.api;

import retrofit2.Call;
import retrofit2.http.GET;

public interface UsersQueries {

    @GET("/api/?results=100")
    Call<String> getUsers();

}
