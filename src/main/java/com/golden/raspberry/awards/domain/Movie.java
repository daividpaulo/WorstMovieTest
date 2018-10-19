package com.golden.raspberry.awards.domain;


import java.util.List;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.stereotype.Component;

@Component
public class Movie {

	private int id;
	
	private Integer year;
	
	private String title;
	
	private List<String> studios;
	
	private List<String> producers;
	
	private boolean winner;

	public Movie() {
		
	}
	
	public Movie(int id,Integer year, String title, List<String> studios, List<String> producers, boolean winner) {
		super();
		this.id=id;
		this.year = year;
		this.title = title.trim();
		this.studios = studios;
		this.producers = producers;
		this.winner = winner;
	}
	
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Integer getYear() {
		return year;
	}

	public void setYear(Integer year) {
		this.year = year;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public List<String> getStudios() {
		return studios;
	}

	public void setStudios(List<String> studios) {
		this.studios = studios;
	}

	public List<String> getProducers() {
		return producers;
	}

	public void setProducers(List<String> producers) {
		this.producers = producers;
	}

	public boolean isWinner() {
		return winner;
	}

	public void setWinner(boolean winner) {
		this.winner = winner;
	}

		

}
