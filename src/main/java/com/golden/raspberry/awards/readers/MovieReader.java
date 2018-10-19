package com.golden.raspberry.awards.readers;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.support.GenericTypeAwareAutowireCandidateResolver;

import com.golden.raspberry.awards.domain.Movie;

import lombok.Data;

@Data
public class MovieReader {

    public Integer year;
	
	public String title;

	public String[] studios;
	
	public String[] producers;
	
	public String winner;

	
	public Movie getAndConvertToMovie(int newId) {
		return new Movie(newId,year,title,Arrays.asList(studios),Arrays.asList(producers),winner.toUpperCase().trim().equals("YES"));
	}
}
