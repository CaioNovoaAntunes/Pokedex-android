package me.dio.pokedex_android.view.domain

data class PokemonType(
    val name: String
){
    val formattedName = name.capitalize()
}