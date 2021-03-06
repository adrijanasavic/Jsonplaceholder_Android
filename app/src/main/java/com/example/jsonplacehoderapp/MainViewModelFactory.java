package com.example.jsonplacehoderapp;

import android.content.Context;

import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;

public class MainViewModelFactory extends ViewModelProvider.NewInstanceFactory {
         RecyclerView recyclerView;
        Context context;

        public MainViewModelFactory(Context context, RecyclerView recyclerView) {
           this.recyclerView=recyclerView;
           this.context=context;
        }

        @Override
        public <T extends ViewModel> T create(Class<T> modelClass) {
            return (T) new com.example.jsonplacehoderapp.MainViewModel(context,recyclerView);
        }
}

