package com.example.internshipscreen;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class SearchActivity extends AppCompatActivity {

    private EditText searchInput;
    private RecyclerView recyclerView;
    private InternshipAdapter adapter;
    private List<Internship> internshipList = new ArrayList<>();

    private InternshipApi internshipApi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        searchInput = findViewById(R.id.searchEditText);
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new InternshipAdapter(this, internshipList);
        recyclerView.setAdapter(adapter);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://10.0.2.2:3000/") // localhost for emulator
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        internshipApi = retrofit.create(InternshipApi.class);

        searchInput.addTextChangedListener(new TextWatcher() {
            @Override public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
            @Override public void afterTextChanged(Editable s) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                searchInternships(s.toString().trim());
            }
        });
    }

    private void searchInternships(String query) {
        if (query.isEmpty()) {
            internshipList.clear();
            adapter.notifyDataSetChanged();
            return;
        }

        internshipApi.searchInternships(query).enqueue(new Callback<List<Internship>>() {
            @Override
            public void onResponse(Call<List<Internship>> call, Response<List<Internship>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    internshipList.clear();
                    internshipList.addAll(response.body());
                    adapter.notifyDataSetChanged();
                } else {
                    Toast.makeText(SearchActivity.this, "No results found", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<Internship>> call, Throwable t) {
                Toast.makeText(SearchActivity.this, "Search failed: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
