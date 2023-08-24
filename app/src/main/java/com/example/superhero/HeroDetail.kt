package com.example.superhero

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.view.isVisible
import com.example.superhero.databinding.ActivityHeroDetailBinding
import com.squareup.picasso.Picasso
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class HeroDetail : AppCompatActivity() {
private lateinit var biding:ActivityHeroDetailBinding

    companion object{
        const val HERO_ID="hero_id"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        biding= ActivityHeroDetailBinding.inflate(layoutInflater)

        setContentView(biding.root)

        val id = intent.getStringExtra(HERO_ID).orEmpty()

        getSingleHero(id)
    }

    private fun getSingleHero(id:String){
        biding.pbDetail.isVisible=true
        CoroutineScope(Dispatchers.IO).launch {
           val response= getRetrofit().create(AppiService::class.java).getSuperHeroByID(id)

            if(response.body() !=null){
                runOnUiThread {
                    biding.pbDetail.isVisible=false
                    uiInit(response.body()!!)
                    biding.cardDetail.isVisible=true
                }
            }
        }
    }

    private fun uiInit(res: DetailResponse) {
        biding.heroNames.text=res.name
        Picasso.get().load(res.image.url).into(biding.imageDetail)
        biding.fullName.text=res.biography.fullName
        biding.country.text=res.biography.country
        biding.appearance.text=res.biography.firstView
        biding.gender.text=res.appearance.gender

    }

    private fun getRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://superheroapi.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}