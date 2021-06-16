package com.example.jsonplacehoderapp;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import com.example.jsonplacehoderapp.adapter.RecyclerAdapter;
import com.example.jsonplacehoderapp.model.Post;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerAdapter adapter;
    private RecyclerView recyclerView;
    private ProgressDialog progressDialog;

    private List<Post> data;
    private EditText postIdEditTextID;
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        progressDialog = new ProgressDialog(MainActivity.this);
        progressDialog.setMessage("Loading....");
        progressDialog.show();
        data = new ArrayList<>();
        postIdEditTextID = findViewById(R.id.postIdEditTextID);
        textView = findViewById(R.id.textView);
        recyclerView = findViewById(R.id.customRecyclerView);
        setupViewModel();
        addTextListener();
    }

    public void addTextListener() {

        postIdEditTextID.addTextChangedListener(new TextWatcher() {

            public void afterTextChanged(Editable s) {
            }

            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            public void onTextChanged(CharSequence query, int start, int before, int count) {

                query = query.toString().toLowerCase();
                final List<Post> filteredList = new ArrayList<Post>();
                if (data != null) {
                    if (data.size() > 0) {
                        for (int i = 0; i < data.size(); i++) {
                            String subject = "" + data.get(i).getId();
                            String tag = "" + data.get(i).getUserId();
                            String title = "" + data.get(i).getTitle();
                            String body = "" + data.get(i).getBody();
                            if (subject.contains(query)) {
                                filteredList.add(data.get(i));
                            } else if (tag.contains(query)) {

                                filteredList.add(data.get(i));
                            } else if (title.contains(query)) {

                                filteredList.add(data.get(i));
                            } else if (body.contains(query)) {

                                filteredList.add(data.get(i));
                            }
                        }
                    }
                }
                adapter = new RecyclerAdapter(MainActivity.this, filteredList);
                RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(MainActivity.this);
                recyclerView.setLayoutManager(layoutManager);
                recyclerView.setAdapter(adapter);
            }
        });
    }

    private void setupViewModel() {
        MainViewModelFactory factory = new MainViewModelFactory(this, recyclerView);
        MainViewModel viewModel = ViewModelProviders.of(this, factory).get(MainViewModel.class);

        viewModel.getTasks().observe(this, new Observer<List<Post>>() {
            @Override
            public void onChanged(List<Post> posts) {
                if (posts.isEmpty()) {
                    progressDialog.dismiss();
                    Toast.makeText(MainActivity.this, "Something went wrong...Please try later!", Toast.LENGTH_SHORT).show();
                } else {
                    progressDialog.dismiss();
                    fillAdapter(posts);
                    data = posts;
                }
            }
        });
    }

    private void fillAdapter(List<Post> posts) {
        adapter = new RecyclerAdapter(this, posts);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
    }
}