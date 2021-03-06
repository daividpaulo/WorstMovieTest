package com.golden.raspberry.awards.dtos;

import java.beans.Transient;

import org.junit.Ignore;

public class IntervalDto {

   private String producer;
   private int    previousWin;
   private int    followingWin;
	
   public IntervalDto() {
		
   }

	public String getProducer() {
		return producer;
	}

	public void setProducer(String producer) {
		this.producer = producer;
	}

	@Transient
	public int getInterval() {
		return (getFollowingWin() - getPreviousWin());
	}

	
	public int getPreviousWin() {
		return previousWin;
	}

	public void setPreviousWin(int previousWin) {
		this.previousWin = previousWin;
	}

	public int getFollowingWin() {
		return followingWin;
	}

	public void setFollowingWin(int followingWin) {
		this.followingWin = followingWin;
	} 
	   
	  
	   
}
