package com.devsuperior.dsmovie.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.devsuperior.dsmovie.entities.ScoreEntity;
import com.devsuperior.dsmovie.entities.ScoreEntityPK;

public interface ScoreRepository extends JpaRepository<ScoreEntity, ScoreEntityPK> {

}