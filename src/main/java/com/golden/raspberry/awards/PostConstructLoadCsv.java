package com.golden.raspberry.awards;


import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.golden.raspberry.awards.domain.Movie;
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
	   
	   SaveInRepositoryMemory(domainMovies);
	   
   }
   
   
   private void SaveInRepositoryMemory(List<Movie> domainMovies) {
	   
	   _movieRepository.save(domainMovies);
	   
   }


  


   private List<Movie> LoadAndMapMovieReader(String fileName){
	   
	   List<Movie> movies =  (List<Movie>) CsvUtil.loadObjectList(Movie.class, fileName);

	   return movies;
   }
   
 
   
}
