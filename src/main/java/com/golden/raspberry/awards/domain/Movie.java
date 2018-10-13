package com.golden.raspberry.awards.domain;


import org.springframework.stereotype.Component;




@Component
public class Movie {

	private Integer year;
	
	private String title;
	
	private String[] studios;
	
	private String[] producers;
	
	private boolean winner;


	public Movie() {
		
	}
	
	public Movie(Integer year, String title, String[] studios, String[] producers, boolean winner) {
		super();
		this.year = year;
		this.title = title;
		this.studios = studios;
		this.producers = producers;
		this.winner = winner;
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

	public String[] getStudios() {
		return studios;
	}

	public void setStudios(String[] studios) {
		this.studios = studios;
	}

	public String[] getProducers() {
		return producers;
	}

	public void setProducers(String[] producers) {
		this.producers = producers;
	}

	public boolean isWinner() {
		return winner;
	}

	public void setWinner(boolean winner) {
		this.winner = winner;
	}

		

}
