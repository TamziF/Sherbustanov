package com.example.tinkoffprojectsfilms.ui.view.filmslist

import androidx.core.os.bundleOf
import androidx.fragment.app.setFragmentResult
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.tinkoffprojectsfilms.R
import com.example.tinkoffprojectsfilms.data.model.Film
import com.example.tinkoffprojectsfilms.databinding.FilmViewHolderBinding
import java.util.Locale

class FilmViewHolder(
    private val binding: FilmViewHolderBinding,
    private val fragment: FilmsListFragment
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(film: Film) {
        bindImage(film)
        setInfoText(film)
        bindCardClick(film)

        binding.filmName.text = film.nameRu
    }

    private fun setInfoText(film: Film) {
        val info = "${film.genres[0].genre.capitalizeFirstLetter()}: (${film.year})"
        binding.filmInfo.text = info
    }

    private fun String.capitalizeFirstLetter(): String {
        if (isEmpty()) {
            return this
        }
        return this.substring(0, 1).uppercase(Locale.ROOT) + this.substring(1)
    }

    private fun bindImage(film: Film) {
        binding.filmImage.load(film.posterUrlPreview){
            error(R.drawable.broken_image)
            placeholder(R.drawable.loading_circle)
        }
    }

    private fun bindCardClick(film: Film){
        binding.filmCard.setOnClickListener {
            fragment.setFragmentResult(R.string.request_key.toString(), bundleOf(R.string.bundle_key.toString() to film.filmId))
            val action = FilmsListFragmentDirections.actionFilmsListFragmentToFilmFragment()
            it.findNavController().navigate(action)
        }
    }

}