package com.example.moviesapp.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.moviesapp.Model.MovieModel;
import com.example.moviesapp.R;

import java.util.List;

public class MovieListAdapter extends RecyclerView.Adapter<MovieListAdapter.ViewHolder> {
    private Context context;
    private List<MovieModel> movielist;
    private itemclicklistener itemclicklistener;

    public MovieListAdapter(Context context, List<MovieModel> movielist, itemclicklistener itemclicklistener) {
        this.context = context;
        this.movielist = movielist;
        this.itemclicklistener = itemclicklistener;
    }

    public void setMovielist(List<MovieModel> movielist) {
        this.movielist = movielist;
        notifyDataSetChanged();

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.recycle_row, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.tvTitle.setText(this.movielist.get(position).getTitle().toString());
        Glide.with(context)
                .load(this.movielist.get(position).getImage())
                .apply(RequestOptions.centerCropTransform())
                .into(holder.imageView);

holder.itemView.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        itemclicklistener.onmovieclick(movielist.get(position));
    }
});
    }


    @Override
    public int getItemCount() {
        if (this.movielist != null) {
            return this.movielist.size();
        }
        return 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvTitle;
        ImageView imageView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTitle = itemView.findViewById(R.id.titleview);
            imageView = itemView.findViewById(R.id.imageview);


        }


    }

    public interface itemclicklistener {
        public void onmovieclick(MovieModel movie);
    }

}
