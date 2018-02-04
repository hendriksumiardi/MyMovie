package com.iak.belajar.mymovie.view.adapter;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;

import com.iak.belajar.mymovie.R;
import com.iak.belajar.mymovie.model.Movie;
import com.iak.belajar.mymovie.restapi.RestAPIURL;
import com.iak.belajar.mymovie.utility.AppConstant;
import com.iak.belajar.mymovie.utility.CommonFunction;
import com.iak.belajar.mymovie.view.activity.DetailActivity;

import java.util.ArrayList;
import java.util.List;


public class MoviesAdapter extends RecyclerView.Adapter<MoviesAdapter.ListHolder> {
    private Context context;
    private List<Movie> movies;

    public MoviesAdapter(Context context) {
        this.context = context;
        movies = new ArrayList<>();
    }

    public void addAll(List<Movie> movies) {
        this.movies.addAll(movies);
        notifyDataSetChanged();
    }

    public void resetData() {
        this.movies.clear();
        notifyDataSetChanged();
    }

    @Override
    public ListHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.movie_cardview_layout, null);
        return new ListHolder(view);
    }

    @Override
    public void onBindViewHolder(final ListHolder holder, int position) {
        holder.movieCardViewTitle.setText(movies.get(position).getTitle());

        CommonFunction.setImage(context, RestAPIURL.getUrlImage(movies.get(position).getPosterPath()), holder.movieCardViewPic);

        holder.movieCardViewLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendIntent(movies.get(holder.getAdapterPosition()));
            }
        });
        holder.movieCardViewLayout.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                Dialog dialog = new Dialog(context);
                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                dialog.setContentView(R.layout.movie_bigpicture_layout);
                ImageView imageView = (ImageView) dialog.findViewById(R.id.bigpicture_pic);
                CommonFunction.setImage(context, RestAPIURL.getUrlImage(movies.get(holder.getAdapterPosition()).getPosterPath()), imageView);
                dialog.show();
                return false;
            }
        });

    }

    private void sendIntent(Movie movieDetail) {
        Bundle bundle = new Bundle();
        bundle.putInt(AppConstant.MOVIE_ID, movieDetail.getId());
        bundle.putString(AppConstant.MOVIE_TITLE, movieDetail.getTitle());
        CommonFunction.moveActivity(context, DetailActivity.class, bundle, false);
    }

    @Override
    public int getItemCount() {
        return movies.size();
    }

    class ListHolder extends RecyclerView.ViewHolder {
        TextView movieCardViewTitle;
        ImageView movieCardViewPic;
        CardView movieCardViewLayout;

        ListHolder(View itemView) {
            super(itemView);
            movieCardViewTitle = (TextView) itemView.findViewById(R.id.movie_cardview_title);
            movieCardViewPic = (ImageView) itemView.findViewById(R.id.movie_cardview_pic);
            movieCardViewLayout = (CardView) itemView.findViewById(R.id.movie_cardview_layout);
        }
    }
}