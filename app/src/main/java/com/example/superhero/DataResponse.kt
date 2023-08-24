package com.example.superhero

import com.google.gson.annotations.SerializedName

data class DataResponse(@SerializedName("response") val response:String,
                        @SerializedName("results") val superHeroes:List<SuperHeroItem>
)

data class SuperHeroItem(val id:String,
                         val name:String,
                         val image:HeroImage
)

data class HeroImage(val url:String)


