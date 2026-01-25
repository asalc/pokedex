package es.shiro.data.model.remote

import com.google.gson.annotations.SerializedName
import java.io.Serializable

class PokemonKindRemoteDto(
    @SerializedName("genus")
    val name: String? = null,
    val language: GenericRemoteDto? = null
): Serializable