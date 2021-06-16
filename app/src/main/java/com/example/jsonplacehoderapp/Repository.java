package com.example.jsonplacehoderapp;
import android.content.Context;
import androidx.lifecycle.MutableLiveData;
import androidx.recyclerview.widget.RecyclerView;
import com.example.jsonplacehoderapp.model.Post;
import com.example.jsonplacehoderapp.net.GetDataService;
import com.example.jsonplacehoderapp.net.RetrofitClientInstance;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Repository {
    Context context;
    RecyclerView recyclerView;
    public Repository(Context applicationContext, RecyclerView recyclerView) {
        context=applicationContext;
        this.recyclerView=recyclerView;
    }

    public MutableLiveData<List<Post>> getTasks() {
        GetDataService service = RetrofitClientInstance.getRetrofitInstance().create(GetDataService.class);
        Call<List<Post>> call = service.getPosts();

        final MutableLiveData<List<Post>> newsData = new MutableLiveData<>();
        call.enqueue(new Callback<List<Post>>() {
            @Override
            public void onResponse(Call<List<Post>> call, Response<List<Post>> response) {
                newsData.setValue(response.body());
            }

            @Override
            public void onFailure(Call<List<Post>> call, Throwable t) {
                System.out.println("onFailure");
                newsData.setValue(null);
            }
        });
        return newsData;
    }
}
