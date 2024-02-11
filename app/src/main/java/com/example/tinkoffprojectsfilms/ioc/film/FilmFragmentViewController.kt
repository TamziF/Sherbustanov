package com.example.tinkoffprojectsfilms.ioc.film

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import coil.load
import com.example.tinkoffprojectsfilms.R
import com.example.tinkoffprojectsfilms.data.model.FilmInfo
import com.example.tinkoffprojectsfilms.databinding.FragmentFilmBinding
import com.example.tinkoffprojectsfilms.ui.stateholders.FilmInfoViewModel
import com.example.tinkoffprojectsfilms.ui.view.film.FilmFragment
import kotlinx.coroutines.launch

class FilmFragmentViewController(
    binding: FragmentFilmBinding,
    private val fragment: FilmFragment,
    private val viewModel: FilmInfoViewModel
) {

    private val filmImage = binding.filmImage
    private val filmName = binding.filmName
    private val filmDescription = binding.filmDescription
    private val filmGenres = binding.filmGenres
    private val filmCountries = binding.filmCountries

    private fun bindViews(film: FilmInfo){
        setUpImage(film)

        with(film) {
            filmName.text = nameRu
            filmDescription.text = description
            filmGenres.text = genres[0].genre
            filmCountries.text = countries[0].country
        }
    }

    fun setUpViews(){
        fragment.viewLifecycleOwner.lifecycleScope.launch {
            fragment.lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED){
                viewModel.film.collect{
                        film -> film?.let { bindViews(it) }
                }
            }
        }
    }

    private fun setUpImage(film: FilmInfo){
        filmImage.load(film.posterUrl){
            error(R.drawable.broken_image)
        }
    }
}