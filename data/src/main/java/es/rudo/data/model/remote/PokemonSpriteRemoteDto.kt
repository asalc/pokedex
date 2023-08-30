package es.rudo.data.model.remote

import com.google.gson.annotations.SerializedName
import java.io.Serializable

class PokemonSpriteRemoteDto(
   @SerializedName("back_default")
   val backDefault: String? = null,

   @SerializedName("front_default")
   val frontDefault: String? = null
): Serializable