package com.golden.raspberry.awards.dtos;

import lombok.Data;


public class YearWinnerDto {

	private Integer year;
	
	private int winnerCount;

	public YearWinnerDto(Integer year, int winnerCount) {
		super();
		this.year = year;
		this.winnerCount = winnerCount;
	}

	public Integer getYear() {
		return year;
	}

	public void setYear(Integer year) {
		this.year = year;
	}

	public int getWinnerCount() {
		return winnerCount;
	}

	public void setWinnerCount(int winnerCount) {
		this.winnerCount = winnerCount;
	}
	
	
}
