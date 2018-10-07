package com.golden.raspberry.awards.readers;

import java.util.List;

import lombok.Data;

@Data
public class MovieCsvReader {

	public String year;
	
	public String title;
	
	public String[] studios;
	
	public String[] producers;
	
	public String winner;

}
