package com.capestone.shakeitup.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.capestone.shakeitup.R
import com.capestone.shakeitup.data.Cocktail

class CocktailAdapter (private val onClick: (Cocktail) -> Unit) :
    ListAdapter<Cocktail, CocktailAdapter.ViewHolder>(CocktailDiffCallback) {

    /**
     * Provide a reference to the type of views that you are using
     * (custom ViewHolder).
     */
    class ViewHolder(val view: View, val onClick: (Cocktail) -> Unit) : RecyclerView.ViewHolder(view) {
        private val cocktailNameTextView: TextView
        private var currentCocktail: Cocktail? = null
        private val cocktailThumbnailImageView: ImageView

        init {
            itemView.setOnClickListener {
                currentCocktail?.let {
                    onClick(it)
                }
            }
            cocktailNameTextView = view.findViewById(R.id.tv_cocktailName)
            cocktailThumbnailImageView = view.findViewById(R.id.iv_cocktail_thumbnail)

        }

        fun bind(cocktail: Cocktail) {
            currentCocktail = cocktail

            cocktailNameTextView.text = cocktail.strDrink
            Glide
                .with(view)
                .load(cocktail.strDrinkThumb+"/preview")
                .placeholder(R.drawable.ic_baseline_wine_bar_24)
                .into(cocktailThumbnailImageView)
        }
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.cocktail_item, viewGroup, false)

        return ViewHolder(view, onClick)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        val cocktail = getItem(position)
        viewHolder.bind(cocktail)
    }
}

object CocktailDiffCallback : DiffUtil.ItemCallback<Cocktail>() {
    override fun areItemsTheSame(oldItem: Cocktail, newItem: Cocktail): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: Cocktail, newItem: Cocktail): Boolean {
        return oldItem.idDrink == newItem.idDrink
    }
}