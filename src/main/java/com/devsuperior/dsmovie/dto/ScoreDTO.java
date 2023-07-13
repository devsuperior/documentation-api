package com.devsuperior.dsmovie.dto;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;

public class ScoreDTO {
	
	private static final DecimalFormat df = new DecimalFormat("#.##", new DecimalFormatSymbols(Locale.US));

	@NotNull(message = "Required field")
	private Long movieId;

	@PositiveOrZero(message = "Score should be greater than or equal to zero")
	private Double score;

	public ScoreDTO(Long movieId, Double score) {
		this.movieId = movieId;
		this.score = Double.valueOf(df.format(score));
	}
	
	public Long getMovieId() {
		return movieId;
	}

	public Double getScore() {
		return score;
	}
}
