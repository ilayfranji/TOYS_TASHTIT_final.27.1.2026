package com.ilay_f.tashtit.ACTIVITIES;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.ilay_f.tashtit.R;

public class ToysActivity extends AppCompatActivity {
    private RecyclerView toysRecycleView;
    private FloatingActionButton fabAdd;
    /// אדפטר???.


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_toys);

        initialViews();
        setListeners();

    }

    private void initialViews() {
        toysRecycleView = findViewById(R.id.toysRecycleView);
        fabAdd = findViewById(R.id.fabAdd);
    }
    private void setListeners() {
        fabAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ToysActivity.this, ToyActivity.class);
                startActivity(intent);
            }
        });
    }
}