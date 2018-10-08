package com.golden.raspberry.awards.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.golden.raspberry.awards.domain.Movie;
import com.golden.raspberry.awards.repository.IMovieMemoryRepository;

@Service
public class MovieService implements IMovieService {

	 
	@Autowired
	private IMovieMemoryRepository _movieRepository;
	
	
	@Override
	public List<Movie> getAll() {
		
		return _movieRepository.getAll();
		
	}

	
	
}
