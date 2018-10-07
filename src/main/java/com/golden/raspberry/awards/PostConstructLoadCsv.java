package com.golden.raspberry.awards;


import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Component;

import com.golden.raspberry.awards.domain.Movie;
import com.golden.raspberry.awards.readers.MovieCsvReader;
import com.golden.raspberry.awards.util.CsvUtil;




@Component
public class PostConstructLoadCsv {

   private final String fileName = "/movielist.csv";
	
   @PostConstruct
   public void init() {
     
		
	   List<MovieCsvReader> movieReaderList = LoadAndMapMovieReader(fileName);
	   
	   List<Movie> domainMovies = ConvertToDomainMovieModel(movieReaderList);
	   
	   SaveInRepositoryMemory(domainMovies);
	   
   }
   
   
   private void SaveInRepositoryMemory(List<Movie> domainMovies) {
	  
	   // TODO Auto-generated method stub
	
   }


   private List<Movie> ConvertToDomainMovieModel(List<MovieCsvReader> movieReaderList) {
	
	   List<Movie> movieList = new ArrayList<Movie>();
	   
	   for(MovieCsvReader reader : movieReaderList) {
		
		   String teste =  reader.producers.toString();
		   
	   }
	   
	   return movieList;
   }


   private List<MovieCsvReader> LoadAndMapMovieReader(String fileName){
	   
	   List<MovieCsvReader> movies =  (List<MovieCsvReader>) CsvUtil.loadObjectList(MovieCsvReader.class, fileName);

	   return movies;
   }
   
 
   
}
