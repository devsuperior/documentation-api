package com.devsuperior.dsmovie.entities;

import java.util.Objects;

import jakarta.persistence.Embeddable;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Embeddable
public class ScoreEntityPK {

	@ManyToOne
	@JoinColumn(name = "movie_id")
	private MovieEntity movie;
	
	@ManyToOne
	@JoinColumn(name = "user_id")
	private UserEntity user;
	
	public ScoreEntityPK() {
	}

	public MovieEntity getMovie() {
		return movie;
	}

	public void setMovie(MovieEntity movie) {
		this.movie = movie;
	}

	public UserEntity getUser() {
		return user;
	}

	public void setUser(UserEntity user) {
		this.user = user;
	}

	@Override
	public int hashCode() {
		return Objects.hash(movie, user);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ScoreEntityPK other = (ScoreEntityPK) obj;
		return Objects.equals(movie, other.movie) && Objects.equals(user, other.user);
	}
}