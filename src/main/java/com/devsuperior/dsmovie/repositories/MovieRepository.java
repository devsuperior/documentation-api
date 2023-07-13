package com.devsuperior.dsmovie.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.devsuperior.dsmovie.entities.MovieEntity;

public interface MovieRepository extends JpaRepository<MovieEntity, Long> {

}