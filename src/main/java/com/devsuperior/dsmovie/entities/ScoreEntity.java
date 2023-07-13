package com.devsuperior.dsmovie.entities;

import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "tb_score")
public class ScoreEntity {

	@EmbeddedId
	private ScoreEntityPK id = new ScoreEntityPK();
	
	@Column(name = "score_value")
	private Double value;
	
	public ScoreEntity() {
	}
	
	public void setMovie(MovieEntity movie) {
		id.setMovie(movie);
	}

	public void setUser(UserEntity user) {
		id.setUser(user);
	}
	
	public ScoreEntityPK getId() {
		return id;
	}

	public void setId(ScoreEntityPK id) {
		this.id = id;
	}

	public Double getValue() {
		return value;
	}

	public void setValue(Double value) {
		this.value = value;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ScoreEntity other = (ScoreEntity) obj;
		return Objects.equals(id, other.id);
	}
}
