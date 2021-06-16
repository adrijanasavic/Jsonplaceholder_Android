package com.example.jsonplacehoderapp;

import android.content.Context;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import androidx.recyclerview.widget.RecyclerView;

import com.example.jsonplacehoderapp.model.Post;

import java.util.List;

public class MainViewModel extends ViewModel {
    Repository repository;

    public MainViewModel(Context context, RecyclerView recyclerView) {
        repository = new Repository(context,recyclerView);
    }

    public MutableLiveData<List<Post>> getTasks() {
        return repository.getTasks();
    }

}
