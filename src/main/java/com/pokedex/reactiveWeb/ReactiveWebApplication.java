package com.pokedex.reactiveWeb;

import com.pokedex.reactiveWeb.model.Pokemon;
import com.pokedex.reactiveWeb.repository.PokemonRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.core.ReactiveMongoOperations;
import reactor.core.publisher.Flux;

@SpringBootApplication
public class ReactiveWebApplication {

    public static void main(String[] args) {
		SpringApplication.run(ReactiveWebApplication.class, args);
    }
    
    @Bean
    CommandLineRunner init(ReactiveMongoOperations operations, PokemonRepository repository) {
        return args -> {

            Flux<Pokemon> pokemonFlux = Flux.just(
                    new Pokemon(null, "Dograss", "Cachorro pequeno", "OverGrow", 2.00, 1),
                    new Pokemon(null, "Baddog", "Cachorro", "OverGrow", 12.00, 2),
                    new Pokemon(null, "Wolfking", "Lobo", "OverGrow", 30.0, 3),
                    new Pokemon(null, "Firefox", "Raposa", "Blaze", 7.0, 4),
                    new Pokemon(null, "Boxfox", "Raposa Boxer", "Blaze", 12.0, 5),
                    new Pokemon(null, "Foxlibre", "Raposa Wrestling", "Blaze", 15.0, 6),
                    new Pokemon(null, "Fishin", "Peixe", "Torrent", 6.09, 7),
                    new Pokemon(null, "Serpentor", "Serpente marinha", "Torrent", 22.5, 8),
                    new Pokemon(null, "Marinedragon", "Dragão marinha", "Torrent", 30.0, 9),
                    new Pokemon(null, "Bulbassauro", "Semente", "OverGrow", 6.09, 10),
                    new Pokemon(null, "Charmander", "Lagarto", "Blaze", 8.5, 11),
                    new Pokemon(null, "Squirtle", "Tartaruga pequena", "Torrent", 9.0, 12),
                    new Pokemon(null, "Blastoise", "Marisco", "Torrente", 6.09, 13),
                    new Pokemon(null, "Pikachu", "Rato", "Choque", 6.00, 14))
                    .flatMap(repository::save);

            pokemonFlux
                    .thenMany(repository.findAll())
                    .subscribe(System.out::println);
        };
    }
}
