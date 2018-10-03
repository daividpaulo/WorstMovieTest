package com.golden.raspberry.awards.domain;

import java.util.List;

import lombok.Data;

@Data
public class Movie {

	private Long id;
	
	private int year;
	
	private String title;
	
	private List<Studio> studios; 
	
	private boolean winner;
}
