package com.example.projects.githubprofiles;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    DetailsAdapter adapter;
    List<Details> uploads;
    private static final String URL= "https://api.github.com/users";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.rv);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        uploads = new ArrayList<>();
        adapter = new DetailsAdapter(MainActivity.this, uploads);
        recyclerView.setAdapter(adapter);

        AAsyncTask task = new AAsyncTask();
        task.execute(URL);
    }
    private class AAsyncTask extends AsyncTask<String, Void, List<Details>> {

        @Override
        protected List<Details> doInBackground(String... urls) {
            if (urls.length < 1 || urls[0] == null) {
                return null;
            }
            List<Details> e= QueryUtils.fetchData(urls[0]);
            return e;
        }

        @Override
        protected void onPostExecute(List<Details> e) {
            View loadingIndicator = findViewById(R.id.loading_indicator);
            loadingIndicator.setVisibility(View.GONE);
            uploads.clear();

            if(e != null && !e.isEmpty()){
                uploads.addAll(e);
            }
            adapter.notifyDataSetChanged();
        }

    }
}