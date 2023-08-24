package com.example.superhero

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface AppiService {

    @GET("api/1340390860239854/search/{name}")
    suspend fun getSuperHero(@Path("name")name:String):Response<DataResponse>

    @GET("api/1340390860239854/{id}")
    suspend fun getSuperHeroByID(@Path("id")id:String):Response<DetailResponse>
}