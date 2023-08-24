package com.example.superhero

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class SuperHeroAdapter(private var superHeroes:List<SuperHeroItem> = emptyList(),
                       private val handleClick:(String)->Unit)
    :RecyclerView.Adapter<SuperHeroViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SuperHeroViewHolder {
        return SuperHeroViewHolder(LayoutInflater.from(parent.context)
            .inflate(R.layout.item_super_hero,parent,false))
    }

    @SuppressLint("NotifyDataSetChanged")
    fun updateList(superHeroList:List<SuperHeroItem>){
        this.superHeroes=superHeroList

        notifyDataSetChanged()
    }

    override fun getItemCount()=superHeroes.size

    override fun onBindViewHolder(viewHolder: SuperHeroViewHolder, position: Int) {
        viewHolder.bind(superHeroes[position],handleClick)
    }
}