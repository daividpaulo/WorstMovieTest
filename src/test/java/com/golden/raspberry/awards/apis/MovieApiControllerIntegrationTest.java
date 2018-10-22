package com.golden.raspberry.awards.apis;


import static org.junit.Assert.fail;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;

import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.golden.raspberry.awards.domain.Movie;

@RunWith(SpringRunner.class) 
@WebMvcTest(MovieApiController.class)
public class MovieApiControllerIntegrationTest {

    @Autowired
    private MockMvc mvc;
	 
    private  List<Movie> allMovies;
	
	@Before
	public void setUp() {
		LoadMovies();
	}
	
	private void LoadMovies() {
		
		try {
			MvcResult	mvcResult = mvc.perform(
				     get("/movies")
			 	    .contentType(MediaType.APPLICATION_JSON))
					.andReturn();
		   
			MockHttpServletResponse response = mvcResult.getResponse();
 		   String lista = response.getContentAsString();

 		   ObjectMapper mapper = new ObjectMapper();
 		   
 		  allMovies = mapper.readValue(lista, new TypeReference<List<Movie>>(){});
  		 
 		  
		} catch (Exception e) {
			
			e.printStackTrace();
		}

	}
	
	
	@Test
	public void testGetAllMovie() {

		try {
		
			 mvc.perform(
					  get("/movies")
					   .contentType(MediaType.APPLICATION_JSON))
					   .andExpect(status().isOk())
					   .andExpect(jsonPath("$",  Matchers.hasSize(allMovies.size())));
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testAddNew() {
		fail("Not yet implemented");
	}

	@Test
	public void testUpdateMovie() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetWinnerMoviesByYear() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetYearsMoreThanOneWinner() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetStudios() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetBetterAndWorseProducer() {
		fail("Not yet implemented");
	}

	
	
	@Test
	public void testRemoveWinnerMovie() {
		
		try {
	
			int idWinnerMovie = allMovies.stream().filter(x->x.isWinner()).findFirst().get().getId();
			
			mvc.perform(
			     delete("/movies/" + idWinnerMovie)
		 	    .contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$", Matchers.is(false)));
		
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	@Test
	public void testRemoveNotWinnerMovie() {
		
		try {
	
			int idWinnerMovie = allMovies.stream().filter(x-> !x.isWinner()).findFirst().get().getId();
			
			mvc.perform(
			     delete("/movies/" + idWinnerMovie)
		 	    .contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$", Matchers.is(true)));
		
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

}
