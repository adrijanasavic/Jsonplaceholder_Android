package com.example.jsonplacehoderapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.jsonplacehoderapp.R;
import com.example.jsonplacehoderapp.model.Post;

import java.util.List;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.CustomViewHolder> {

    private List<Post> dataList;
    private Context context;

    public RecyclerAdapter(Context context, List<Post> dataList){
        this.context = context;
        this.dataList = dataList;
    }

    class CustomViewHolder extends RecyclerView.ViewHolder {

        public final View mView;
        TextView txtUserID;
        TextView txtId;
        TextView txtTitle;
        TextView txtBody;


        CustomViewHolder(View itemView) {
            super(itemView);
            mView = itemView;
            txtUserID = mView.findViewById(R.id.userId);
            txtId = mView.findViewById(R.id.id);
            txtTitle = mView.findViewById(R.id.title);
            txtBody = mView.findViewById(R.id.body);
        }
    }

    @Override
    public CustomViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.post_row, parent, false);
        return new CustomViewHolder(view);
    }

    @Override
    public void onBindViewHolder(CustomViewHolder holder, int position) {
        holder.txtTitle.setText(dataList.get(position).getTitle());
        holder.txtUserID.setText(String.valueOf(dataList.get(position).getUserId()));
        holder.txtId.setText(String.valueOf(dataList.get(position).getId()));
        holder.txtBody.setText(dataList.get(position).getBody());
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }
}
