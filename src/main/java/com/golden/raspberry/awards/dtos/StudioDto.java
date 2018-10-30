package com.golden.raspberry.awards.dtos;

import java.util.List;

public class StudioDto {

	private List<StudioWinnerDto> studios;

	
	public StudioDto() {
		
	}
	
	public StudioDto(List<StudioWinnerDto> studios) {
		this.studios=studios;
	}

	public List<StudioWinnerDto> getStudios() {
		return studios;
	}

	public void setStudios(List<StudioWinnerDto> studios) {
		this.studios = studios;
	}

	
    
}
