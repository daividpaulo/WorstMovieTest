package com.golden.raspberry.awards.services;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
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
import com.golden.raspberry.awards.dtos.IntervalDto;
import com.golden.raspberry.awards.dtos.RangeAwardsDto;
import com.golden.raspberry.awards.dtos.StudioDto;
import com.golden.raspberry.awards.dtos.StudioWinnerDto;
import com.golden.raspberry.awards.dtos.YearDto;
import com.golden.raspberry.awards.dtos.YearWinnerDto;
import com.golden.raspberry.awards.repository.IMovieMemoryRepository;



@Service
public class MovieService implements IMovieService {

	 
	@Autowired
	private IMovieMemoryRepository _movieRepository;
	
	
	@Override
	public List<Movie> getAll() {
		return _movieRepository
				 .getAll()
				 .stream()
			     .sorted(Comparator.comparing(Movie::getId))
			     .collect(Collectors.toList());
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


	@Override
	public StudioDto getStudios() {
	  
		   List<Movie> movies = _movieRepository.getAll();
		   List<StudioWinnerDto> studios = new ArrayList<StudioWinnerDto>();
		   
		   for(Movie movie : movies){
			   for(String actualStudio : movie.getStudios()) {
				   if(!studios.stream().anyMatch(x->x.getName().equals(actualStudio.trim()))) {
					   
					   Long winCount = movies
					     .stream()
					     .filter(x->x.isWinner() && x.getStudios().stream().anyMatch(s->s.trim().equalsIgnoreCase(actualStudio.trim())))
					     .count();
					   
					   StudioWinnerDto newStudio = new StudioWinnerDto(actualStudio.trim(), winCount.intValue());     
					   studios.add(newStudio);
				   }
			   }
		   }
		   
		   
		   studios = studios
			 .stream()
		     .sorted(Comparator.comparing(StudioWinnerDto::getWinCount).reversed())
		     .collect(Collectors.toList());
		   
		   StudioDto studioDto = new StudioDto(studios);
			
		   
		   return studioDto;
	}


	private List<String> getListDistinctProducers (List<Movie> movies){
		
		List<String> produces = new ArrayList<String>();
		
		movies
		  .stream()
		  .forEach(m->m.getProducers().stream().forEach(p-> produces.add(p.trim())));
		
		return produces.stream().distinct().collect(Collectors.toList());
		
	}
	
	
	@Override
	public RangeAwardsDto getBetterAndWorseProducer() {
	
		   List<Movie> winnersMovies = _movieRepository.getAllWinnerMovies();
					   
		   List<IntervalDto> intervalWins = new ArrayList<IntervalDto>();
		   
		   for(String actualProduce : getListDistinctProducers(winnersMovies)) {
			
			   List<Movie> moviesProducer = winnersMovies
				      	                      .stream()
					                          .filter(x-> x.getProducers().stream().anyMatch(s-> s.equalsIgnoreCase(actualProduce.trim())))
					                          .sorted(Comparator.comparing(Movie::getYear).reversed())
					                          .collect(Collectors.toList());
				 
				 IntervalDto newInteval = null; 
				 int count = 1;
				 
				 for(Movie movieProducer : moviesProducer) {
					 if(count==1) {
						newInteval = new IntervalDto(); 
					    newInteval.setProducer(actualProduce);
					    newInteval.setFollowingWin(movieProducer.getYear());
					    count++;
					 }else if(count==2) {
						 newInteval.setPreviousWin(movieProducer.getYear());
						 intervalWins.add(newInteval);
						 count=1;
					 }
				 }
		   }
		   
		   
		  IntervalDto max = intervalWins
		     .stream()
		     .max(Comparator.comparing(IntervalDto::getInterval))
		     .get();
		   
		
		  IntervalDto min = intervalWins
				     .stream()
				     .min(Comparator.comparing(IntervalDto::getInterval))
				     .get();
				  
		  		  
		return new RangeAwardsDto(min, max);
	}


	@Override
	public boolean removeMovie(int id) {
		
		Movie movie = _movieRepository.getById(id);
		
		if(!movie.isWinner()) {
			_movieRepository.remove(movie);
			return true;
		}
		
		return false;
	}


	@Override
	public Movie save(Movie movie) {
		return _movieRepository.save(movie);
	}


	@Override
	public Movie update(Movie movie) {
		return _movieRepository.update(movie);
	}

	
	
}
