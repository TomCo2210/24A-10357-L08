package dev.tomco.a24a_10357_l08;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import dev.tomco.a24a_10357_l08.Adapters.MovieAdapter;
import dev.tomco.a24a_10357_l08.Interfaces.MovieCallback;
import dev.tomco.a24a_10357_l08.Models.Movie;
import dev.tomco.a24a_10357_l08.Utilities.DataManager;

public class MainActivity extends AppCompatActivity {
    private RecyclerView main_LST_movies;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViews();
        initViews();
    }

    private void initViews() {
        MovieAdapter movieAdapter = new MovieAdapter(getApplicationContext(), DataManager.getMovies());
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(RecyclerView.VERTICAL);
        main_LST_movies.setLayoutManager(linearLayoutManager);
        main_LST_movies.setAdapter(movieAdapter);

        movieAdapter.setMovieCallback(new MovieCallback() {
            @Override
            public void favoriteButtonClicked(Movie movie, int position) {
                movie.setFavorite(!movie.isFavorite());
                main_LST_movies.getAdapter().notifyDataSetChanged();
            }
        });
    }

    private void findViews() {
        main_LST_movies = findViewById(R.id.main_LST_movies);
    }
}