package com.golden.raspberry.awards.repository;

import java.util.List;

import com.golden.raspberry.awards.domain.Movie;


public interface IMovieMemoryRepository {
	
	public Movie save (Movie newMovie);

	public void save(List<Movie> domainMovies);

	public List<Movie> getAll();

	public List<Movie> getWinnerMoviesByYear(int year);

	public List<Movie> getAllWinnerMovies();

	public Movie getById(int id);

	public void remove(Movie movie);
	
	
	
}
