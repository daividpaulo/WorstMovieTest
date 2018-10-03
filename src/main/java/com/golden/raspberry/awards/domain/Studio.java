package com.golden.raspberry.awards.domain;

import java.util.List;

import lombok.Data;

@Data
public class Studio {

	private Long id;
	
	private String studioName;
	
	private List<Producer> producers;
	
}
