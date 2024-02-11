package com.example.tinkoffprojectsfilms.ui.view.filmslist

import android.content.Context
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.tinkoffprojectsfilms.R
import com.example.tinkoffprojectsfilms.data.model.Film
import com.example.tinkoffprojectsfilms.databinding.FilmViewHolderBinding

class FilmViewHolder(
    private val binding: FilmViewHolderBinding,
    private val context: Context
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(film: Film) {
        bindImage(film)
        setInfoText(film)

        binding.filmName.text = film.nameRu
    }

    private fun setInfoText(film: Film) {
        val info = "${film.genres[0].genre}: (${film.year})"
        binding.filmInfo.text = info
    }

    private fun bindImage(film: Film) {
        binding.filmImage.load(film.posterUrlPreview){
            error(R.drawable.broken_image)
        }
    }

    private fun bindCardClick(){
        binding.filmCard.setOnClickListener {
        }
    }

}