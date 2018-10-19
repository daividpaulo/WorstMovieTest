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


	@Override
	public StudioDto getStudios() {
	  
		   List<Movie> winnerMovies = _movieRepository.getAll();
			
		   
		   List<StudioWinnerDto> studios = new ArrayList<StudioWinnerDto>();
		   
		   
		   for(Movie movie : winnerMovies){
			
			   for(String actualStudio : movie.getStudios()) {
				   
				   if(!studios.stream().anyMatch(x->x.getName().equals(actualStudio.trim()))) {
					   
					   Long winCount = winnerMovies
					     .stream()
					     .filter(x->x.isWinner() && x.getStudios().stream().anyMatch(s->s.equalsIgnoreCase(actualStudio.trim())))
					     .count();
					   
					   StudioWinnerDto newStudio = new StudioWinnerDto(actualStudio.trim(), winCount.intValue());     
					   
					   studios.add(newStudio);
				   }
			   }
		   }
		   
		   
		   //studios = studios
			//	     .stream()
				//     .sorted((o1, o2)->o1.getWinCount().compareTo(o2.getWinCount()))
                  //   .collect(Collectors.toList());
		   
		   studios = studios
			 .stream()
		     .sorted(Comparator.comparing(StudioWinnerDto::getWinCount).reversed())
		     .collect(Collectors.toList());
		   
		   StudioDto studioDto = new StudioDto(studios);
			
		   
		   return studioDto;
		
		
	}

	
	
}
