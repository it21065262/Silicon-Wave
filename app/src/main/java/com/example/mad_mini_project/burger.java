package com.example.mad_mini_project;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.SearchView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.FirebaseDatabase;

public class burger extends AppCompatActivity
{
    RecyclerView recyclerView;
    MainAdapter mainAdapter;

    FloatingActionButton floatingActionButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_burger);

        recyclerView = (RecyclerView) findViewById(R.id.rv);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        FirebaseRecyclerOptions<item> options =
                new FirebaseRecyclerOptions.Builder<item>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("Items"),item.class)
                        .build();

        mainAdapter = new MainAdapter(options);
        recyclerView.setAdapter(mainAdapter);

        floatingActionButton = (FloatingActionButton)findViewById(R.id.floatingActionButton);

        floatingActionButton.setOnClickListener(view ->
                startActivity(new Intent(getApplicationContext(),AddActivity.class)));
    }

    @Override
    protected void onStart() {
        super.onStart();
        mainAdapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        mainAdapter.stopListening();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.search,menu);
        MenuItem item = menu.findItem(R.id.search);
        SearchView searchView = (SearchView) item.getActionView();

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                textSearch(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String query) {
                textSearch(query);
                return false;
            }
        });


        return super.onCreateOptionsMenu(menu);
    }

    private void textSearch(String str){
        FirebaseRecyclerOptions<item> options =
                new FirebaseRecyclerOptions.Builder<item>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("Items").orderByChild("name").startAt(str).endAt(str + "~"),item.class)
                        .build();

        mainAdapter = new MainAdapter(options);
        mainAdapter.startListening();
        recyclerView.setAdapter(mainAdapter);
    }
}