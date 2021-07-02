package com.zeygame.javamvvm.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.zeygame.javamvvm.R;
import com.zeygame.javamvvm.model.DetailsModel;
import com.zeygame.javamvvm.model.MovieModel;
import com.zeygame.javamvvm.network.ImageProccess;

import java.util.List;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MyViewHolder> {
    private Context context;
    private List<MovieModel> list;
    private IItemClickListener clickListener;

    public MovieAdapter(Context context, List<MovieModel> list, IItemClickListener clickListener) {
        this.context = context;
        this.list = list;
        this.clickListener = clickListener;
    }

    public void setMovieList(List<MovieModel> list){
        this.list = list;
        notifyDataSetChanged();
    }

    @NonNull
    @org.jetbrains.annotations.NotNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull @org.jetbrains.annotations.NotNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.row_view,parent,false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull @org.jetbrains.annotations.NotNull MovieAdapter.MyViewHolder holder, int position) {
        holder.txTitle.setText(list.get(position).getTitle());
        new ImageProccess().initImage(list.get(position).getPoster(),context, holder.imgMovie);
        holder.imgMovie.setOnClickListener(event->clickListener.onclick(list.get(position)));
    }

    @Override
    public int getItemCount() {
        if (this.list !=null) return this.list.size();
        return 0;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private ImageView imgMovie;
        private TextView txTitle;
        public MyViewHolder(@NonNull @org.jetbrains.annotations.NotNull View itemView) {
            super(itemView);

            imgMovie = itemView.findViewById(R.id.imgMovie);
            txTitle = itemView.findViewById(R.id.txTitle);
        }
    }
    public interface IItemClickListener{
        void onclick(MovieModel movie);
    }

}
