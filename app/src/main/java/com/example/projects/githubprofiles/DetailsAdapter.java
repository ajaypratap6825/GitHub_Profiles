package com.example.projects.githubprofiles;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

public class DetailsAdapter extends RecyclerView.Adapter<DetailsAdapter.DetailsViewHolder> {
    private Context context;
    private List<Details> uploads;

    public DetailsAdapter(Context context, List<Details> uploads) {
        this.context = context;
        this.uploads = uploads;
    }

    public class DetailsViewHolder extends RecyclerView.ViewHolder{
        ImageView post;
        TextView text, text2;
        View itemView;
        public DetailsViewHolder(@NonNull View itemView) {
            super(itemView);
            this.itemView = itemView;
            post = itemView.findViewById(R.id.post);
            text = itemView.findViewById(R.id.text);
            text2 = itemView.findViewById(R.id.text2);
        }
    }

    @Override
    public DetailsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v= LayoutInflater.from(context).inflate(R.layout.list ,parent,false);
        return new DetailsViewHolder(v);
    }

    @Override
    public void onBindViewHolder(DetailsViewHolder holder, int position) {
        Details u = uploads.get(position);
        String url = u.getAvatarUrl();
        Picasso.get().load(url).fit().centerCrop().into(holder.post);
        holder.text.setText(u.getLogin());
        holder.text2.setText(u.getId());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Context c =  v.getContext();
                String url = u.getUrl();
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(url));
                c.startActivity(i);
            }
        });
    }

    @Override
    public int getItemCount() {
        return uploads.size();
    }
}
