package com.example.superhero

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.superhero.HeroDetail.Companion.HERO_ID
import com.example.superhero.databinding.ActivityMainBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {

    private lateinit var biding:ActivityMainBinding
    private lateinit var retrofit: Retrofit
    private lateinit var heroAdapter:SuperHeroAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        biding= ActivityMainBinding.inflate(layoutInflater)
        setContentView(biding.root)

        retrofit=getRetrofit()

        initUi()

        handleRefresh()
    }

    private fun handleRefresh() {
      biding.swiper.setOnRefreshListener {
          searchByName("a")
        biding.swiper.isRefreshing=false
          biding.notFound.isVisible=false
      }
    }

    private fun initUi() {
        biding.search.setOnQueryTextListener(object:SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {

                searchByName(query.orEmpty())
                return false
            }

            override fun onQueryTextChange(newText: String?)=false
        })

        heroAdapter= SuperHeroAdapter { heroId->getHeroDetail(heroId)}
        biding.superHeroRecView.layoutManager=LinearLayoutManager(this)
        biding.superHeroRecView.adapter=heroAdapter

        searchByName("j")
    }

    private fun searchByName(query:String){
        biding.progressBar.isVisible=true
        CoroutineScope(Dispatchers.IO).launch{
            val data= retrofit.create(AppiService::class.java).getSuperHero(query)

            if(data.isSuccessful){
                val res:DataResponse?=data.body()

                if(res!=null && res.response!="error"){
                    runOnUiThread {
                        biding.notFound.isVisible=false
                        heroAdapter.updateList(res.superHeroes)
                    }
                }else{
                    runOnUiThread{
                        biding.notFound.isVisible=true

                    }
                }

                runOnUiThread {
                    biding.progressBar.isVisible=false
                }
            }



        }
    }

    private fun getHeroDetail(id:String){
        val intent = Intent(this,HeroDetail::class.java)
        intent.putExtra(HERO_ID,id)
        startActivity(intent)
    }

    private fun getRetrofit(): Retrofit {
       return Retrofit.Builder()
            .baseUrl("https://superheroapi.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}