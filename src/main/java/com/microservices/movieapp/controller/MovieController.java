package com.microservices.movieapp.controller;

import com.microservices.movieapp.dao.MovieDao;
import com.microservices.movieapp.dto.MovieDTO;
import com.microservices.movieapp.entities.Movie;
import com.microservices.movieapp.services.MovieService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/movie_app/v1")
public class MovieController {

    @Autowired
    private MovieService movieService;

    @Autowired
    ModelMapper modelMapper;

    //to save a new movie received from frontend
    @PostMapping(value="/movies", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity createMovie(MovieDTO movieDTO) {

        Movie newMovie = modelMapper.map(movieDTO, Movie.class);

        Movie savedMovie = movieService.acceptMovieDetails(newMovie);

        MovieDTO savedMovieDTO = modelMapper.map(savedMovie, MovieDTO.class);

        return new ResponseEntity(savedMovieDTO, HttpStatus.CREATED);

    }

    @GetMapping(value="/movies", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity getAllMovies() {

    }

}
