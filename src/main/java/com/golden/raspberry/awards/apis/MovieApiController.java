package com.golden.raspberry.awards.apis;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.golden.raspberry.awards.domain.*;
import com.golden.raspberry.awards.services.IMovieService;

@RestController
public class MovieApiController {

	@Autowired
	private IMovieService _service;
	
	
	@RequestMapping(path = "/movie", method = RequestMethod.GET)
	 public List<Movie> getAllMoview(){
		 return _service.getAll();
	 }
	
	
  // 1. Obter o(s) vencedor(es), informando um ano;
  // 2. Obter os anos que tiveram mais de um vencedor;
  // 3. Obter a lista de estúdios, ordenada pelo número de premiações;
  // 4. Obter o produtor com maior intervalo entre dois prêmios, e o que obteve dois	prêmios mais rápido;
  // 5. Excluir um filme. Não deve permitir excluir vencedores.
	 
}
