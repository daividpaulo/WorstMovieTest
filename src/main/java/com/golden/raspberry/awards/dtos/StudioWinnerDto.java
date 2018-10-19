package com.golden.raspberry.awards.dtos;

public class StudioWinnerDto {

	
	private String name;
	
	private Integer winCount;

	
	public StudioWinnerDto(String name, Integer winCount) {
		super();
		this.name = name;
		this.winCount = winCount;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getWinCount() {
		return winCount;
	}

	public void setWinCount(Integer winCount) {
		this.winCount = winCount;
	}
	
	
	

}
