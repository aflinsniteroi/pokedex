/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pokedex.reactiveWeb.Controller;

import com.pokedex.reactiveWeb.repository.PokemonRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import reactor.core.publisher.Flux;
import com.pokedex.reactiveWeb.model.Pokemon;
import com.pokedex.reactiveWeb.model.PokemonEvent;
import java.time.Duration;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/pokemons")
public class  PokedexController{
    
    private PokemonRepository repository;
    public PokedexController(PokemonRepository repository) { 
        this.repository = repository; 
    }

    @GetMapping
    public Flux<Pokemon> getAllPokemons() {
        return repository.findAll().sort();
    }
    
    @GetMapping("/{id}")
    public Mono<ResponseEntity<Pokemon>> getPokemon(@PathVariable String id) {
        return repository.findById(id)
                .map(pokemon -> ResponseEntity.ok(pokemon))
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }
    
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<Pokemon> savePokemon(@RequestBody Pokemon pokemon) {
        return repository.save(pokemon);
    }
    
    @PutMapping("{id}")
    public Mono<ResponseEntity<Pokemon>> updatePokemon(@PathVariable(value = "id") String id,
                                                       @RequestBody Pokemon pokemon) {
        return repository.findById(id)
                .flatMap(existingPokemon -> {
                    existingPokemon.setNome(pokemon.getNome());
                    existingPokemon.setCategoria(pokemon.getCategoria());
                    existingPokemon.setHabilidades(pokemon.getHabilidades());
                    existingPokemon.setPeso(pokemon.getPeso());
                    existingPokemon.setEvolucao(pokemon.getEvolucao());
                    return repository.save(existingPokemon);
                })
                .map(updatePokemon -> ResponseEntity.ok(updatePokemon))
                .defaultIfEmpty(ResponseEntity.notFound().build());

    }
    
    @DeleteMapping("{id}")
    public Mono<ResponseEntity<Void>> deletePokemon(@PathVariable(value = "id") String id) {
        if (id.startsWith("GB")) {
            id = id.substring(2);
            return repository.findById(id)
                    .flatMap(existingPokemon
                            -> repository.delete(existingPokemon)
                            .then(Mono.just(ResponseEntity.ok().<Void>build()))
                    )
                    .defaultIfEmpty(ResponseEntity.notFound().build());
        } else {
            return (Mono.just(ResponseEntity.badRequest().build()));
        }
    }
    
    
    @GetMapping(value = "/events", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<PokemonEvent> getPokemonEvents() {
        return Flux.interval(Duration.ofSeconds(5))
                .map(val ->
                        new PokemonEvent(val, "Product Event")
                );
    }
}
