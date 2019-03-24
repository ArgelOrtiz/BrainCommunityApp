package com.example.braincommunity.Post;

import android.content.Intent;
import android.os.Bundle;
import android.service.autofill.TextValueSanitizer;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;

import com.example.braincommunity.R;

public class PostViewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_view);

        Intent intent = getIntent();
        setTitle(intent.getStringExtra("title"));

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeAsUpIndicator(R.drawable.ic_arrow_back);

        TextView usernameTextView, dateTextView, questionTextView, contextTextView;
        Button likeButton, dislikeButton, orderButton;

        usernameTextView = findViewById(R.id.userPostTextView);
        dateTextView = findViewById(R.id.datePostTextView);
        questionTextView = findViewById(R.id.questionPostTextView);
        contextTextView = findViewById(R.id.descriptionPostTextView);

        String title = getIntent().getStringExtra("title");
        questionTextView.setText(title);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        finish();
        return super.onOptionsItemSelected(item);
    }
}
