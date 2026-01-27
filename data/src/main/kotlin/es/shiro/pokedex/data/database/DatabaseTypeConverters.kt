package es.shiro.pokedex.data.database

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import es.shiro.pokedex.domain.model.PokemonSpecies

object DatabaseTypeConverters {

    @TypeConverter
    fun fromStringListToString(
        stringList: List<String>?
    ): String? = stringList?.let { Gson().toJson(it) }

    @TypeConverter
    fun fromStringToStringList(
        string: String?
    ): List<String> =
        string?.let {
            Gson().fromJson(
                string,
                TypeToken.getParameterized(
                    List::class.java,
                    String::class.java
                ).type
            )
        } ?: emptyList()

    @TypeConverter
    fun fromStringPairToString(
        pair: Pair<String, String>
    ): String = Gson().toJson(pair)

    @TypeConverter
    fun fromStringToStringPair(
        value: String
    ): Pair<String, String> =
        Gson().fromJson(
            value,
            object : TypeToken<Pair<String, String>>() { }.type
        )

    @TypeConverter
    fun fromPairListToString(
        pairs: List<Pair<String, String>>?
    ): String? {
        val stringPairs: List<String>? = pairs?.map {
            fromStringPairToString(it)
        }
        return fromStringListToString(stringPairs)
    }

    @TypeConverter
    fun fromStringToPairArrayList(
        value: String?
    ): List<Pair<String, String>> {
        val stringArrayList: List<String> = fromStringToStringList(value)
        return stringArrayList.map {
            fromStringToStringPair(it)
        }
    }

    @TypeConverter
    fun fromPokemonSpeciesToString(
        value: PokemonSpecies
    ): String = Gson().toJson(value)

    @TypeConverter
    fun fromStringToPokemonSpecies(
        value: String
    ): PokemonSpecies =
        Gson().fromJson(
            value,
            object : TypeToken<PokemonSpecies>() { }.type
        )
}