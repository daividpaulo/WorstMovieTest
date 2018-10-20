package com.golden.raspberry.awards.repository;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.golden.raspberry.awards.domain.Movie;


@Repository
public class MovieMemoryRepository implements IMovieMemoryRepository {

	private static List<Movie> database = new ArrayList<Movie>();
	

	public MovieMemoryRepository(List<Movie> database) {
		this.database = database;
	}


	@Override
	public Movie save(Movie newMovie) {
		
	 int nextId =	this.database
		   .stream()
		   .max(Comparator.comparing(Movie::getId))
		   .get().getId() + 1;
		
	    newMovie.setId(nextId);
		
	    this.database.add(newMovie);
		
		return newMovie;
		
	}


	@Override
	public void save(List<Movie> domainMovies) {
		this.database.addAll(domainMovies);
	}


	@Override
	public List<Movie> getAll() {
		return this.database.stream()
				        .filter(x->x.getId()>0)
				        .collect(Collectors.toList());
	}


	@Override
	public List<Movie> getWinnerMoviesByYear(int year) {
		
		return this.database.stream()
				            .filter(x-> x.isWinner() && x.getYear()==year)
				            .collect(Collectors.toList());
		
	}


	@Override
	public List<Movie> getAllWinnerMovies() {
		
		return this.database.stream()
	            .filter(x-> x.isWinner())
	            .collect(Collectors.toList());

	}


	@Override
	public Movie getById(int id) {
		return this.database.stream()
	               .filter(x-> x.getId()==id)
                   .findFirst()
                   .get();
   }


	@Override
	public void remove(Movie movie) {
		this.database.remove(movie);
	}


	@Override
	public Movie update(Movie movie) {
	    
	  Movie obsoletMovie = this.database
		     .stream()
		     .filter(x->x.getId()==movie.getId())
		     .findFirst()
		     .get();
		
		this.database.remove(obsoletMovie);
		this.database.add(movie);
		
		return movie;
	}
	

}