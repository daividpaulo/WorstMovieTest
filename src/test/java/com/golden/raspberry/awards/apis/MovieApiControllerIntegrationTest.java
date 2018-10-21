package com.golden.raspberry.awards.apis;


import static org.junit.Assert.fail;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.client.RestClientTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.client.MockRestServiceServer;



@RunWith(SpringRunner.class) 
@RestClientTest(MovieApiController.class)
public class MovieApiControllerIntegrationTest {

	
	@Autowired
    private MockRestServiceServer server;
	
	@Test
	public void testGetAllMovie() {
       fail("Not yet implemented");
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
		fail("Not yet implemented");
	}

}
