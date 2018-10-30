package com.golden.raspberry.awards.dtos;

import java.util.List;

import lombok.Data;


public class YearDto {

	private List<YearWinnerDto> years;

	public YearDto() {
		
	}
	
	public YearDto(List<YearWinnerDto> yearWinners) {
		super();
		this.years = yearWinners;
	}

	public List<YearWinnerDto> getYears() {
		return years;
	}

	public void setYears(List<YearWinnerDto> years) {
		this.years = years;
	}

	
	
	
}
