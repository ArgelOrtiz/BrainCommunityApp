package com.example.braincommunity.Post;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.braincommunity.R;

import java.util.Map;
import java.util.Objects;

public class PostRecyclerViewAdapter extends RecyclerView.Adapter<PostRecyclerViewAdapter.ViewHolder> {

    Context context;
    Map<Integer,Map<String, String>> post;

    public PostRecyclerViewAdapter(Context context, Map<Integer, Map<String, String>> post) {
        this.context = context;
        this.post = post;
    }

    @NonNull
    @Override
    public PostRecyclerViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new PostRecyclerViewAdapter.ViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_post_recycler_view,viewGroup,false));
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    public void onBindViewHolder(@NonNull PostRecyclerViewAdapter.ViewHolder viewHolder, int i) {

        Map<String, String> currencyPost = post.get(i);

        final String title = currencyPost.get("title");

        viewHolder.process(title, currencyPost.get("description"), Integer.parseInt(Objects.requireNonNull(currencyPost.get("status"))));

        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    Intent intent = new Intent(context,PostViewActivity.class);
                    intent.putExtra("title", title);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    context.startActivity(intent);
                Toast.makeText(context, title, Toast.LENGTH_SHORT).show();
        }
        });
    }

    @Override
    public int getItemCount() {
        return post.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView titleTextView, descriptionTextView;
        ImageView indicatorImageView;
        ConstraintLayout layout;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            titleTextView       = itemView.findViewById(R.id.titleItemPostTextView);
            descriptionTextView = itemView.findViewById(R.id.descriptionItemPostTextView);
            indicatorImageView  = itemView.findViewById(R.id.indicatorItemPostImageView);
            layout              = itemView.findViewById(R.id.layoutItemPostConstraintLayout);
        }

        public void process(String title, String description, int status){

            titleTextView.setText(title);
            descriptionTextView.setText(description);

            if (status == 0) indicatorImageView.setImageResource(R.drawable.ic_indicator_red);
            else indicatorImageView.setImageResource(R.drawable.ic_indicator_green);


        }

    }

}
