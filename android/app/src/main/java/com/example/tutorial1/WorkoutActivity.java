package com.example.tutorial1;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class WorkoutActivity extends AppCompatActivity {

    RecyclerView mRecyclerView;
    MyAdapter myAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_workout);

        mRecyclerView = findViewById(R.id.recyclerView);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        myAdapter = new MyAdapter(this, getMyList());
    }

    private ArrayList<Model> getMyList(){

        ArrayList<Model> models = new ArrayList<>();

        Model m = new Model();

        m.setTitle("News Feed");
        m.setDescription("This is newsfeed description");
        m.setImg(R.drawable.main_logo);
        models.add(m);

        m = new Model();

        m.setTitle("News Feed");
        m.setDescription("This is newsfeed description");
        m.setImg(R.drawable.main_logo);
        models.add(m);

        m = new Model();

        m.setTitle("News Feed");
        m.setDescription("This is newsfeed description");
        m.setImg(R.drawable.main_logo);
        models.add(m);

        m = new Model();

        m.setTitle("News Feed");
        m.setDescription("This is newsfeed description");
        m.setImg(R.drawable.main_logo);
        models.add(m);

        m = new Model();

        m.setTitle("News Feed");
        m.setDescription("This is newsfeed description");
        m.setImg(R.drawable.main_logo);
        models.add(m);

        return models;

    }
}
