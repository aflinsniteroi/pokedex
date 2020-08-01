/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pokedex.reactiveWeb.repository;

import com.pokedex.reactiveWeb.model.Pokemon;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

/**
 *
 * @author CP39
 */
public interface PokemonRepository extends ReactiveMongoRepository <Pokemon,String>{
    
}
