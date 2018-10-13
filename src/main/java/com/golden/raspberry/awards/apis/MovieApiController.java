package com.golden.raspberry.awards.apis;

import java.util.List;

import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.golden.raspberry.awards.domain.*;
import com.golden.raspberry.awards.dtos.YearDto;
import com.golden.raspberry.awards.services.IMovieService;

@RestController
@RequestMapping("movie")
public class MovieApiController {

	@Autowired
	private IMovieService _service;
	
	
	 @ResponseStatus(code = HttpStatus.OK)
	 @GetMapping(path = "")
	 public List<Movie> getAllMovie(){
		 return _service.getAll();
	 }
	
	 
	//1. Obter o(s) vencedor(es), informando um ano;
	 @ResponseStatus(code = HttpStatus.OK)
	 @GetMapping(path = "/winners/{year}")
	 public List<Movie> getWinnerMoviesByYear(@PathVariable int year){
		 return _service.getWinnerMoviesByYear(year);	 
	 }
	 
    
	// 2. Obter os anos que tiveram mais de um vencedor;
	@ResponseStatus(code = HttpStatus.OK)
	@GetMapping(path = "/years/morethanonewinner")
	public YearDto getYearsMoreThanOneWinner(){
		 return _service.getYearsMoreThanOneWinner();	 
	}
		
    
	// 3. Obter a lista de estúdios, ordenada pelo número de premiações;
	@ResponseStatus(code = HttpStatus.OK)
	@GetMapping(path = "/studio")
	public List<Movie> getStudios(){
		 return null;	 
	}
	
    
	// 4. Obter o produtor com maior intervalo entre dois prêmios, e o que obteve dois	prêmios mais rápido;
	@ResponseStatus(code = HttpStatus.OK)
	@GetMapping(path = "/produtor/betterandworse")
	public List<Movie> getBetterAndWorseProductor(){
		 return null;	 
	}
		
   
	// 5. Excluir um filme. Não deve permitir excluir vencedores.
	@ResponseStatus(code = HttpStatus.OK)
    @DeleteMapping(path="/") 
	public Movie removeMovie(Movie movie) {
    	 
    	 return null;
     }
	
}
