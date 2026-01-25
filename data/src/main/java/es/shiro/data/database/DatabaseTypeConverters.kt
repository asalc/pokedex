package es.shiro.data.database

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import es.shiro.domain.model.PokemonSpecies

object DatabaseTypeConverters {

    @TypeConverter
    fun fromStringArrayListToString(
        stringArrayList: ArrayList<String>?
    ): String? = stringArrayList?.let { Gson().toJson(it) }

    @TypeConverter
    fun fromStringToStringArrayList(
        string: String?
    ): ArrayList<String> =
        string?.let {
            Gson().fromJson(
                string,
                TypeToken.getParameterized(
                    ArrayList::class.java,
                    String::class.java
                ).type
            )
        } ?: arrayListOf()

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
    fun fromPairArrayListToString(
        pairs: ArrayList<Pair<String, String>>?
    ): String? {
        val stringPairs: ArrayList<String>? = pairs?.map {
            fromStringPairToString(it)
        } as? ArrayList
        return fromStringArrayListToString(stringPairs)
    }

    @TypeConverter
    fun fromStringToPairArrayList(
        value: String?
    ): ArrayList<Pair<String, String>> {
        val stringArrayList: ArrayList<String> = fromStringToStringArrayList(value)
        return stringArrayList.map {
            fromStringToStringPair(it)
        } as ArrayList
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