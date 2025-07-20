package com.example.internshipscreen;

import com.example.internshipscreen.Internship;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface InternshipApi {
    @GET("/search")
    Call<List<Internship>> searchInternships(@Query("q") String query);
}
