package me.dio.pokedex_android.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import me.dio.pokedex_android.R
import me.dio.pokedex_android.view.api.PokemonRepository
import me.dio.pokedex_android.view.domain.Pokemon
import me.dio.pokedex_android.view.domain.PokemonType

class MainActivity : AppCompatActivity() {
    lateinit var recyclerView: RecyclerView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        recyclerView = findViewById<RecyclerView>(R.id.rvPokemons)

        /*      val charmander = Pokemon(
                  "https://assets.pokemon.com/assets/cms2/img/pokedex/detail/004.png",
                  1,
                  "Charmander",
                  listOf(
                      PokemonType("Fire")
                  )
              )
              val pokemons = listOf(
                  charmander, charmander, charmander, charmander
              )
         */




        Thread(Runnable {
            loadPokemons()
        }).start()


    }

    private fun loadPokemons() {
        val pokemonsApiResult = PokemonRepository.listPokemons()

        pokemonsApiResult?.results?.let {
            val pokemons: List<Pokemon?> = it.map { pokemonsApiResult ->
                val number =
                    pokemonsApiResult.url.replace("https://pokeapi.co/api/v2/pokemon/", "").replace("/", "").toInt()

             val pokeApiResult =  PokemonRepository.getPokemon(number)

                pokeApiResult?.let {
                    Pokemon(

                        pokeApiResult.id,
                        pokeApiResult.name,
                        pokeApiResult.types.map {
                            type ->
                            type.type
                        }
                    )
                }

            }

            val layoutManager = LinearLayoutManager(this)

            recyclerView.post {

                recyclerView.layoutManager = layoutManager
                recyclerView.adapter = PokemonAdapter(pokemons)
            }
        }
    }
}