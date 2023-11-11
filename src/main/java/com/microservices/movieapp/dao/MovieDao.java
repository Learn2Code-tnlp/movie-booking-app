package com.microservices.movieapp.dao;

import com.microservices.movieapp.entities.Movie;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MovieDao extends JpaRepository<Movie, Integer> {

    saveAll();
}
