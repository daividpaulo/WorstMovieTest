package com.golden.raspberry.awards.apis;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.hamcrest.Matchers;
import org.junit.Assert;
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
import com.golden.raspberry.awards.dtos.IntervalDto;
import com.golden.raspberry.awards.dtos.RangeAwardsDto;
import com.golden.raspberry.awards.dtos.StudioDto;
import com.golden.raspberry.awards.dtos.StudioWinnerDto;
import com.golden.raspberry.awards.dtos.YearDto;

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
	
	public static String asJsonString(final Object obj) {
	    try {
	        final ObjectMapper mapper = new ObjectMapper();
	        final String jsonContent = mapper.writeValueAsString(obj);
	        return jsonContent;
	    } catch (Exception e) {
	        throw new RuntimeException(e);
	    }
	} 

	@Test
	public void testAddNewMovie() {
		try {
			
			 List<String> studios = new ArrayList<String>();
			 studios.add("MAVEL");
			 List<String> producers = new ArrayList<String>();
			 producers.add("MAVEL PICTURES");
			 Movie newMovie = new Movie(0,2018,"Teste",studios,producers,true);
			 
			
			 mvc.perform(
					  post("/movies")
					   .content(asJsonString(newMovie))
					   .contentType(MediaType.APPLICATION_JSON))
					   .andExpect(status().isOk())
					   .andExpect(jsonPath("$.id",  Matchers.greaterThan(0)))
					   .andExpect(jsonPath("$.title",  Matchers.equalTo("Teste")));
					   
			 
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testUpdateMovie() {
		try {
			
			 Movie movie = allMovies.stream().findFirst().get();
			 movie.setTitle("Novo Titulo");
			
			 mvc.perform(
					  put("/movies")
					   .content(asJsonString(movie))
					   .contentType(MediaType.APPLICATION_JSON))
					   .andExpect(status().isOk())
					   .andExpect(jsonPath("$.id",  Matchers.equalTo(movie.getId())))
					   .andExpect(jsonPath("$.title",  Matchers.equalTo("Novo Titulo")));
					   
			 
			
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Test
	public void testGetWinnerMoviesByYear() {
		
		try {
			
			 mvc.perform(
					  get("/movies/winners/2017")
					   .contentType(MediaType.APPLICATION_JSON))
					   .andExpect(status().isOk())
					   .andExpect(jsonPath("$[*].year",  Matchers.contains(2017)))
			           .andExpect(jsonPath("$[*].winner",  Matchers.contains(true)));
			 
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	
	@Test
	public void testGetYearsMoreThanOneWinner() {
		try {
			
			MvcResult mvcResult = mvc.perform(
					  get("/movies/years/morethanonewinner")
					   .contentType(MediaType.APPLICATION_JSON))
					   .andExpect(status().isOk())
					   .andReturn();

			 MockHttpServletResponse response = mvcResult.getResponse();
	 		 String jsonObjectString = response.getContentAsString();

	 		 ObjectMapper mapper = new ObjectMapper();
	 		   
	 		 YearDto yearDto = mapper.readValue(jsonObjectString, new TypeReference<YearDto>(){});
	 		 
	 		 Assert.assertEquals(0, yearDto.getYears().stream().filter(x->x.getWinnerCount()<=1).count());
			 
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	
	@Test
	public void testGetStudiosOrderedWinCount() {
		
		try {
		
			MvcResult mvcResult= mvc.perform(
					  get("/movies/studios")
					   .contentType(MediaType.APPLICATION_JSON))
					   .andExpect(status().isOk())
					   .andReturn();

			MockHttpServletResponse response = mvcResult.getResponse();
	 		String jsonObjectString = response.getContentAsString();
    		ObjectMapper mapper = new ObjectMapper();
	 		   
	 		StudioDto studioDto = mapper.readValue(jsonObjectString, new TypeReference<StudioDto>(){});

	 		StudioWinnerDto studioAnterior =null;
	 		
	 		for(StudioWinnerDto studioAtual: studioDto.getStudios()) {
	 			if(studioAnterior!=null)
	 			   Assert.assertThat(studioAnterior.getWinCount(), Matchers.greaterThanOrEqualTo(studioAtual.getWinCount()));	
	 			
	 			studioAnterior = studioAtual;
	 		}
	 		
	 		
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		
	}

	
	private IntervalDto InsertBetterProducerExemple() {
		
		try {

		  List<String> producers = new ArrayList<String>();
		  List<String> studios = new ArrayList<String>();
		  producers.add("MAVEL Better");
		  Movie newMovie1 = new Movie(0,2019,"Teste",studios,producers,true);
		  Movie newMovie2 = new Movie(0,2018,"Teste",studios,producers,true);
		
		   mvc.perform(
				  post("/movies")
				   .content(asJsonString(newMovie1))
				   .contentType(MediaType.APPLICATION_JSON))
				   .andExpect(status().isOk());
	
		   mvc.perform(
					  post("/movies")
					   .content(asJsonString(newMovie2))
					   .contentType(MediaType.APPLICATION_JSON))
					   .andExpect(status().isOk());
		   
		   IntervalDto min = new IntervalDto();
		   min.setProducer("MAVEL Better");
		   min.setFollowingWin(2019);
		   min.setPreviousWin(2018);
		   
		   return min;
		   
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

	}

	private IntervalDto InsertWorseProducerExemple() {
		
		try {

		  List<String> producers = new ArrayList<String>();
		  List<String> studios = new ArrayList<String>();
		  producers.add("MAVEL Worse");
		  Movie newMovie1 = new Movie(0,1965,"Teste",studios,producers,true);
		  Movie newMovie2 = new Movie(0,2000,"Teste",studios,producers,true);
		
		   mvc.perform(
				  post("/movies")
				   .content(asJsonString(newMovie1))
				   .contentType(MediaType.APPLICATION_JSON))
				   .andExpect(status().isOk());
	
		   mvc.perform(
					  post("/movies")
					   .content(asJsonString(newMovie2))
					   .contentType(MediaType.APPLICATION_JSON))
					   .andExpect(status().isOk());
		   
		   IntervalDto max = new IntervalDto();
		   max.setProducer("MAVEL Worse");
		   max.setFollowingWin(2000);
		   max.setPreviousWin(1965);
		   
		   return max;
		   
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

	}

	@Test
	public void testGetBetterAndWorseProducer() {

		try {
			
			IntervalDto min = InsertBetterProducerExemple();
			IntervalDto max = InsertWorseProducerExemple();
			
			MvcResult mvcResult= mvc.perform(
					  get("/movies/producer/betterandworse")
					   .contentType(MediaType.APPLICATION_JSON))
					   .andExpect(status().isOk())
					   .andReturn();

			MockHttpServletResponse response = mvcResult.getResponse();
	 		String jsonObjectString = response.getContentAsString();
    		ObjectMapper mapper = new ObjectMapper();
	 		   
	 		RangeAwardsDto rangeDto = mapper.readValue(jsonObjectString, new TypeReference<RangeAwardsDto>(){});
	 		
	 		assertEquals(min.getProducer(), rangeDto.getMim().getProducer());
	 		assertEquals(max.getProducer(), rangeDto.getMax().getProducer());
	 		assertEquals(min.getInterval(), rangeDto.getMim().getInterval());
	 		assertEquals(max.getInterval(), rangeDto.getMax().getInterval());
	 		
	 		
	 		
		} catch (Exception e) {
			e.printStackTrace();
		}
		

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
