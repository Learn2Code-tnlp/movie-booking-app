package com.microservices.movieapp.services;

import com.microservices.movieapp.dao.MovieDao;
import com.microservices.movieapp.entities.Movie;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.ArrayList;
import java.util.List;

public class MovieServiceImpl implements MovieService {

    @Autowired
    private MovieDao movieDao;

    @Override
    public Movie acceptMovieDetails(Movie movie) {
        // to save a new movie
        return movieDao.save(movie);
    }

    @Override
    public  List<Movie> acceptMultipleMovieDetails(List<Movie> movies) {
        List<Movie> savedMovies = new ArrayList<>();
        for(Movie movie : movies){
            savedMovies.add(acceptMovieDetails(movie));
        }
        return savedMovies;
    }

    @Override
    public Movie getMovieDetails(int id) {
        // get movie details by its id
        return movieDao.findById(id).get();
    }

    @Override
    public Movie updateMovieDetails(int id, Movie movie) {
        Movie savedMovie = getMovieDetails(id);
        savedMovie.setMovieDescription(movie.getMovieDescription());
        savedMovie.setMovieName(movie.getMovieName());
        savedMovie.setCoverPhotoUrl(movie.getCoverPhotoUrl());
        savedMovie.setDuration(movie.getDuration());
        savedMovie.setTrailerUrl(movie.getTrailerUrl());
        savedMovie.setReleaseDate(movie.getReleaseDate());
        return movieDao.save(savedMovie);
    }

    @Override
    public boolean deleteMovie(int id) {
        Movie savedMovie = getMovieDetails(id);

        if(savedMovie ==null){
            return false;
        }
        movieDao.delete(savedMovie);
        return true;
    }

    @Override
    public List<Movie> getAllMovies() {
        return movieDao.findAll();
    }

    @Override
    public Page<Movie> getPaginatedMovieDetails(Pageable pageable) {
        return movieDao.findAll(pageable);
    }
}
