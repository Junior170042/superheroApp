package com.example.superhero

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.superhero.databinding.ItemSuperHeroBinding
import com.squareup.picasso.Picasso

class SuperHeroViewHolder(view: View):RecyclerView.ViewHolder(view) {

    private val biding=ItemSuperHeroBinding.bind(view)
    fun bind(superHero: SuperHeroItem, handleClick: (String) -> Unit){
        biding.name.text=superHero.name
        Picasso.get().load(superHero.image.url).into(biding.imageHero)

        biding.root.setOnClickListener{handleClick(superHero.id)}
    }
}