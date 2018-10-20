package com.golden.raspberry.awards.dtos;


public class RangeAwardsDto {
  
	private IntervalDto mim;
	
	private IntervalDto max;

	public RangeAwardsDto(IntervalDto mim, IntervalDto max) {
		super();
		this.mim = mim;
		this.max = max;
	}

	public IntervalDto getMim() {
		return mim;
	}

	public void setMim(IntervalDto mim) {
		this.mim = mim;
	}

	public IntervalDto getMax() {
		return max;
	}

	public void setMax(IntervalDto max) {
		this.max = max;
	}
	
	
	
}
