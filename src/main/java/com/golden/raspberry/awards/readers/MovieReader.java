package com.golden.raspberry.awards.readers;

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

	
	public Movie getAndConvertToMovie() {
		
		return new Movie(year,title,studios,producers,winner.toUpperCase().trim().equals("YES"));
	}
}
