package com.golden.raspberry.awards.apis;


import static org.junit.Assert.fail;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.golden.raspberry.awards.services.IMovieService;



@RunWith(SpringRunner.class) 
@WebMvcTest(MovieApiController.class)
public class MovieApiControllerIntegrationTest {

	
	
    @Autowired
    private MockMvc mvc;
	 
	@Autowired
	private IMovieService _service;
	
	
	@Before
	public void setUp() {
	}
	
	
	@Test
	public void testGetAllMovie() {

		try {
		
			 mvc.perform(
					  get("/movies")
					   .contentType(MediaType.APPLICATION_JSON))
					   .andExpect(status().isOk())
					   .andExpect(jsonPath("$", Matchers.hasSize(196)));
			
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
	public void testRemoveMovie() {
		
		try {
		
			mvc.perform(
			     delete("/movies/2")
		 	    .contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$", Matchers.is(true)));
		
			
			 mvc.perform(
					  get("/movies")
					   .contentType(MediaType.APPLICATION_JSON))
					   .andExpect(status().isOk())
					   .andExpect(jsonPath("$", Matchers.hasSize(195)));
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

}
