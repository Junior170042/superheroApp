package com.example.superhero

import com.google.gson.annotations.SerializedName

data class DetailResponse (
    val name:String,
    val image:HeroImage,
    val biography: Biography,
    val appearance: Appearance
)



data class Appearance(
    val gender:String,
    @SerializedName("eye-color") val eyeColor:String,
)

data class Biography(
    @SerializedName("full-name")  val fullName:String,
    @SerializedName("place-of-birth")  val country:String,
    @SerializedName("first-appearance") val firstView:String


)