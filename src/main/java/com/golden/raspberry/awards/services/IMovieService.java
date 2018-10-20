package com.golden.raspberry.awards.services;

import java.util.List;

import com.golden.raspberry.awards.domain.Movie;
import com.golden.raspberry.awards.dtos.RangeAwardsDto;
import com.golden.raspberry.awards.dtos.StudioDto;
import com.golden.raspberry.awards.dtos.YearDto;

public interface IMovieService {

	public List<Movie> getAll();

	public List<Movie> getWinnerMoviesByYear(int year);

	public YearDto getYearsMoreThanOneWinner();

	public StudioDto getStudios();

	public RangeAwardsDto getBetterAndWorseProducer();

	public boolean removeMovie(int id);
	
}
