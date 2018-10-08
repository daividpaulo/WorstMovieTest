package com.golden.raspberry.awards.repository;

import java.util.List;

import com.golden.raspberry.awards.domain.Movie;


public interface IMovieMemoryRepository {
	
	public Movie save (Movie newMovie);

	public void save(List<Movie> domainMovies);

	public List<Movie> getAll();
	
	
	
}
