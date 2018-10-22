package com.golden.raspberry.awards;


import java.util.ArrayList;
import java.util.List;


import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.golden.raspberry.awards.domain.Movie;
import com.golden.raspberry.awards.readers.MovieReader;
import com.golden.raspberry.awards.repository.IMovieMemoryRepository;
import com.golden.raspberry.awards.util.CsvUtil;


@Component
public class PostConstructLoadCsv {

   private final String dataBaseFileName = "/movielist.csv";
   
   @Autowired
   private IMovieMemoryRepository _movieRepository;
	
   @PostConstruct
   public void init() {
		
	   List<Movie> domainMovies = LoadAndMapMovieReader(dataBaseFileName);
	   _movieRepository.save(domainMovies);
	   
   }
   

   private List<Movie> LoadAndMapMovieReader(String fileName){
	   
	   List<MovieReader> moviesReaders =   CsvUtil.loadObjectList(MovieReader.class, fileName);
	   
	   List<Movie> movies = new ArrayList<>();
	   
	   moviesReaders
	    .forEach(x-> movies.add(x.getAndConvertToMovie(movies.size()+1)));
			   					

	   return movies;
   }
   
 
   
}
