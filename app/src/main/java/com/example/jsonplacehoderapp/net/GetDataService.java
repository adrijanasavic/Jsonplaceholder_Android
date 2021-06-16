package com.example.jsonplacehoderapp.net;
import com.example.jsonplacehoderapp.model.Post;
import java.util.List;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface  GetDataService {

    @GET("posts")
    Call<List<Post>> getPosts(
            @Query("userId") Integer[] userId,
            @Query("id") int id,
            @Query("_sort") String sort,
            @Query("_order") String order
            );

    @GET("posts")
    Call<List<Post>> getPosts();
}
