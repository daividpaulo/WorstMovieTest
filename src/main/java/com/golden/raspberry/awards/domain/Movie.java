package com.golden.raspberry.awards.domain;

import java.util.List;

import org.springframework.stereotype.Component;

import lombok.Data;

@Data
@Component
public class Movie {

	public String year;
	
	public String title;
	
	public String[] studios;
	
	public String[] producers;
	
	public String winner;

}
