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

class CocktailAdapter (private val onClick: (Cocktail) -> Unit, private val onSaveClick: (Cocktail) -> Unit) :
    ListAdapter<Cocktail, CocktailAdapter.ViewHolder>(CocktailDiffCallback) {

    /**
     * Provide a reference to the type of views that you are using
     * (custom ViewHolder).
     */
    class ViewHolder(val view: View, val onClick: (Cocktail) -> Unit, val onSaveClick: (Cocktail) -> Unit) : RecyclerView.ViewHolder(view) {
        private val cocktailNameTextView: TextView
        private var currentCocktail: Cocktail? = null
        private val cocktailThumbnailImageView: ImageView
        private val saveCocktail: ImageView

        init {
            itemView.setOnClickListener {
                currentCocktail?.let {
                    onClick(it)
                }
            }
            cocktailNameTextView = view.findViewById(R.id.tv_cocktailName)
            cocktailThumbnailImageView = view.findViewById(R.id.iv_cocktail_thumbnail)
            saveCocktail = view.findViewById(R.id.iv_save_cocktail)

            saveCocktail.setOnClickListener{
                currentCocktail?.let {
                    if (it.isFavorite){
                        saveCocktail.setImageDrawable(view.resources.getDrawable(R.drawable.ic_baseline_favorite_border_24))
                    }else{
                        saveCocktail.setImageDrawable(view.resources.getDrawable(R.drawable.ic_baseline_favorite_24))
                    }
                    onSaveClick(it)
                }
            }

        }

        fun bind(cocktail: Cocktail) {
            currentCocktail = cocktail

            cocktailNameTextView.text = cocktail.strDrink
            if (cocktail.isFavorite){
                saveCocktail.setImageDrawable(view.resources.getDrawable(R.drawable.ic_baseline_favorite_24))
            }else{
                saveCocktail.setImageDrawable(view.resources.getDrawable(R.drawable.ic_baseline_favorite_border_24))
            }
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

        return ViewHolder(view, onClick, onSaveClick)
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