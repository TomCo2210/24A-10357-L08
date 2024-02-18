package dev.tomco.a24a_10357_l08.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatRatingBar;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.imageview.ShapeableImageView;
import com.google.android.material.textview.MaterialTextView;

import java.util.ArrayList;

import dev.tomco.a24a_10357_l08.Interfaces.MovieCallback;
import dev.tomco.a24a_10357_l08.Models.Movie;
import dev.tomco.a24a_10357_l08.R;
import dev.tomco.a24a_10357_l08.Utilities.ImageLoader;
import dev.tomco.a24a_10357_l08.Utilities.TimeFormatter;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MovieViewHolder> {

    private Context context;
    private ArrayList<Movie> movies;
    private MovieCallback movieCallback;

    public MovieAdapter(Context context, ArrayList<Movie> movies) {
        this.context = context;
        this.movies = movies;
    }

    public MovieAdapter setMovieCallback(MovieCallback movieCallback) {
        this.movieCallback = movieCallback;
        return this;
    }

    @NonNull
    @Override
    public MovieViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.horizontal_movie_item, parent, false);
        return new MovieViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieViewHolder holder, int position) {
        Movie movie = getItem(position);

        ImageLoader.getInstance().load(movie.getPoster(), holder.movie_IMG_poster);
        holder.movie_LBL_name.setText(movie.getName());
        holder.movie_LBL_year.setText(String.valueOf(movie.getReleaseDate()));
        holder.movie_LBL_duration.setText(TimeFormatter.getTimeFormatted(movie.getLength()));
        holder.movie_LBL_genres.setText(String.join(", ", movie.getGenre()));
        holder.movie_LBL_actors.setText(String.join(", ", movie.getActors()));
        holder.movie_RTNG_rating.setRating(movie.getRating()/2);
        if (movie.isFavorite())
            holder.movie_IMG_favorite.setImageResource(R.drawable.heart);
        else
            holder.movie_IMG_favorite.setImageResource(R.drawable.empty_heart);
        holder.movie_LBL_overview.setText(movie.getOverview());
        holder.movie_LBL_overview.setOnClickListener(v -> {
            if (movie.isCollapsed())
                holder.movie_LBL_overview.setMaxLines(Integer.MAX_VALUE);
            else
                holder.movie_LBL_overview.setMaxLines(Movie.MAX_LINES_COLLAPSED);
            movie.setCollapsed(!movie.isCollapsed());
        });
    }

    @Override
    public int getItemCount() {
        return movies == null ? 0 : movies.size();
    }

    private Movie getItem(int position) {
        return movies.get(position);
    }

    public class MovieViewHolder extends RecyclerView.ViewHolder {

        private ShapeableImageView movie_IMG_poster;
        private ShapeableImageView movie_IMG_favorite;
        private MaterialTextView movie_LBL_name;
        private MaterialTextView movie_LBL_year;
        private MaterialTextView movie_LBL_duration;
        private MaterialTextView movie_LBL_genres;
        private MaterialTextView movie_LBL_actors;
        private MaterialTextView movie_LBL_overview;
        private AppCompatRatingBar movie_RTNG_rating;

        public MovieViewHolder(@NonNull View itemView) {
            super(itemView);
            movie_IMG_poster = itemView.findViewById(R.id.movie_IMG_poster);
            movie_IMG_favorite = itemView.findViewById(R.id.movie_IMG_favorite);
            movie_LBL_name = itemView.findViewById(R.id.movie_LBL_name);
            movie_LBL_year = itemView.findViewById(R.id.movie_LBL_year);
            movie_LBL_duration = itemView.findViewById(R.id.movie_LBL_duration);
            movie_LBL_genres = itemView.findViewById(R.id.movie_LBL_genres);
            movie_LBL_actors = itemView.findViewById(R.id.movie_LBL_actors);
            movie_LBL_overview = itemView.findViewById(R.id.movie_LBL_overview);
            movie_RTNG_rating = itemView.findViewById(R.id.movie_RTNG_rating);
            movie_IMG_favorite.setOnClickListener(v ->
            {
                if (movieCallback != null){
                    movieCallback.favoriteButtonClicked(getItem(getAdapterPosition()),getAdapterPosition());
                }
            });
        }
    }
}
