# Worst Movie Test

## Introduction

> Este é um projeto de avaliação 

## APIs

Get All Movies
> [httpGet] /movies

Update Movie
> [httpPut] /movies

Save New Movie
> [httpPost] /movies

1. Obter o(s) vencedor(es), informando um ano;
> [httpGet] /movies/winners/{year}

2. Obter os anos que tiveram mais de um vencedor;
> [httpGet] /movies/years/morethanonewinner

3. Obter a lista de estúdios, ordenada pelo número de premiações;
> [httpGet] /movies/studios

4. Obter o produtor com maior intervalo entre dois prêmios, e o que obteve dois	prêmios mais rápido;
> [httpGet] /movies/producer/betterandworse

4. Obter o produtor com maior intervalo entre dois prêmios, e o que obteve dois	prêmios mais rápido;
> [httpGet] /movies/producer/betterandworse

5. Excluir um filme. Não deve permitir excluir vencedores.
> [httpDelete] /movies


## Installation

> git clone

Como Executar API

> mvn spring-boot:run

Como Executar Testes

> mvn test

