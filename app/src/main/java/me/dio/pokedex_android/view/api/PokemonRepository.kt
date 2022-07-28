package me.dio.pokedex_android.view.api

import android.util.Log
import me.dio.pokedex_android.view.api.model.PokemonApiResult
import me.dio.pokedex_android.view.api.model.PokemonService
import me.dio.pokedex_android.view.api.model.PokemonsApiResult
import me.dio.pokedex_android.view.domain.Pokemon
import retrofit2.*
import retrofit2.converter.gson.GsonConverterFactory


object PokemonRepository {
    private val service: PokemonService


    // https://pokeapi.co/api/v2/pokemon/?limit=151
    init {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://pokeapi.co/api/v2/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        service = retrofit.create(PokemonService::class.java)
    }

    fun listPokemons(limit: Int = 151): PokemonsApiResult? {
        val call = service.listPokemons(limit)

        return call.execute().body()
    }
    fun getPokemon(number: Int): PokemonApiResult? {
        val call = service.getPokemon(number)

        return call.execute().body()
    }
}