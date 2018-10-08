package com.golden.raspberry.awards.repository;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.golden.raspberry.awards.domain.Movie;


@Component
public class MovieMemoryRepository implements IMovieMemoryRepository {

	private static List<Movie> database = new ArrayList<Movie>();
	

	public MovieMemoryRepository(List<Movie> database) {
		this.database = database;
	}


	@Override
	public Movie save(Movie newMovie) {
		
		this.database.add(newMovie);
		return newMovie;
		
	}


	@Override
	public void save(List<Movie> domainMovies) {
		this.database.addAll(domainMovies);
	}


	@Override
	public List<Movie> getAll() {
		return this.database;
	}
	

}