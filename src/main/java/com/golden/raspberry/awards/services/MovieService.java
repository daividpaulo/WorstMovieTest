package com.golden.raspberry.awards.services;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.deser.std.CollectionDeserializer.CollectionReferringAccumulator;
import com.golden.raspberry.awards.domain.Movie;
import com.golden.raspberry.awards.dtos.YearDto;
import com.golden.raspberry.awards.dtos.YearWinnerDto;
import com.golden.raspberry.awards.repository.IMovieMemoryRepository;



@Service
public class MovieService implements IMovieService {

	 
	@Autowired
	private IMovieMemoryRepository _movieRepository;
	
	
	@Override
	public List<Movie> getAll() {
		return _movieRepository.getAll();
	}


	@Override
	public List<Movie> getWinnerMoviesByYear(int year) {
		return _movieRepository.getWinnerMoviesByYear(year);
	}


	@Override
	public YearDto getYearsMoreThanOneWinner() {
		
	   List<Movie> winnerMovies = _movieRepository.getAllWinnerMovies();
	
		
	   Map<Integer,Long> yearsWinnerCounts = 	winnerMovies
		 .stream()
	     .collect(Collectors.groupingBy(x->x.getYear(), Collectors.counting()));
		
	   
	   List<YearWinnerDto> YearWinners = new ArrayList<YearWinnerDto>();
	   yearsWinnerCounts.forEach((k,v)-> YearWinners.add(new YearWinnerDto(k, v.intValue())));
	   
	   List<YearWinnerDto> yearsMoreThanOneWinner =
			               YearWinners
			               .stream()
			               .filter(x-> x.getWinnerCount() > 1)
			               .collect(Collectors.toList());
	   
	   YearDto yearDto = new YearDto(yearsMoreThanOneWinner); 
		
	   return yearDto;
		
	}

	
	
}
