package com.golden.raspberry.awards.apis;

import java.util.List;

import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.golden.raspberry.awards.domain.*;
import com.golden.raspberry.awards.dtos.RangeAwardsDto;
import com.golden.raspberry.awards.dtos.StudioDto;
import com.golden.raspberry.awards.dtos.YearDto;
import com.golden.raspberry.awards.services.IMovieService;

@RestController
@RequestMapping("movies")
public class MovieApiController {

	@Autowired
	private IMovieService _service;
	
	
	// @ResponseStatus(code = HttpStatus.OK)
	 @GetMapping(path = "")
	 public List<Movie> getAllMovie(){
 		   return _service.getAll();
	 }
	
	 
	@PostMapping(path="")
	public Movie addNew(Movie movie) {
		return movie;
	}
	
	@PutMapping(path="")
	public Movie updateMovie(Movie movie) {
		return movie;
	}
	
	//1. Obter o(s) vencedor(es), informando um ano;
	 @GetMapping(path = "/winners/{year}")
	 public List<Movie> getWinnerMoviesByYear(@PathVariable int year){
		 return _service.getWinnerMoviesByYear(year);	 
	 }
	 
    
	// 2. Obter os anos que tiveram mais de um vencedor;
	@GetMapping(path = "/years/morethanonewinner")
	public YearDto getYearsMoreThanOneWinner(){
		 return _service.getYearsMoreThanOneWinner();	 
	}
		
    
	// 3. Obter a lista de estúdios, ordenada pelo número de premiações;
	@GetMapping(path = "/studios")
	public StudioDto getStudios(){
		 return _service.getStudios();	 
	}
	
    
	// 4. Obter o produtor com maior intervalo entre dois prêmios, e o que obteve dois	prêmios mais rápido;
	@GetMapping(path = "/producer/betterandworse")
	public RangeAwardsDto getBetterAndWorseProducer(){
		 return _service.getBetterAndWorseProducer();	 
	}
		
   
	// 5. Excluir um filme. Não deve permitir excluir vencedores.
	@DeleteMapping(path="{id}") 
	public boolean removeMovie(int id) {
    	 return _service.removeMovie(id);
    }
	
	
	
}
