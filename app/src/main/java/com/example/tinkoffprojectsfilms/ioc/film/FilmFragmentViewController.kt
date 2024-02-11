package com.example.tinkoffprojectsfilms.ioc.film

import android.util.Log
import android.view.View
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.findNavController
import coil.load
import com.example.tinkoffprojectsfilms.R
import com.example.tinkoffprojectsfilms.data.model.FilmInfo
import com.example.tinkoffprojectsfilms.databinding.FragmentFilmBinding
import com.example.tinkoffprojectsfilms.ui.stateholders.DownloadState
import com.example.tinkoffprojectsfilms.ui.stateholders.FilmInfoViewModel
import com.example.tinkoffprojectsfilms.ui.view.film.FilmFragment
import com.example.tinkoffprojectsfilms.ui.view.film.FilmFragmentDirections
import kotlinx.coroutines.launch
import java.util.Locale

class FilmFragmentViewController(
    binding: FragmentFilmBinding,
    private val fragment: FilmFragment,
    private val viewModel: FilmInfoViewModel
) {

    private val filmImage = binding.filmImage
    private val filmName = binding.filmName
    private val filmDescription = binding.filmDescription
    private val filmGenresTitle = binding.filmGenresTitle
    private val filmGenres = binding.filmGenres
    private val filmCountriesTitle = binding.filmCountriesTitle
    private val filmCountries = binding.filmCountries
    private val backArrow = binding.backArrow

    private val errorCloud = binding.errorCloud
    private val errorText = binding.errorText
    private val errorButton = binding.errorButton
    private val loadingCircle = binding.loadingCircle

    private fun bindViews(film: FilmInfo) {
        setUpImage(film)
        bindBackArrow()

        with(film) {
            filmName.text = nameRu
            filmDescription.text = description
        }

        filmGenres.text = buildGenresString(film)
        filmCountries.text = buildCountriesString(film)
    }

    private fun bindDownloadStateHandling() {
        fragment.viewLifecycleOwner.lifecycleScope.launch {
            fragment.lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.downloadState.collect {
                    when (it) {
                        DownloadState.LOADING -> {
                            Log.v("OPOPOP", "Loading")
                            hideErrors()
                            hideInfo()
                            loadingCircle.visibility = View.VISIBLE
                        }

                        DownloadState.DOWNLOAD -> {
                            Log.v("OPOPOP", "Download")
                            hideErrors()
                            loadingCircle.visibility = View.INVISIBLE
                            showInfo()
                        }

                        DownloadState.ERROR -> {
                            Log.v("OPOPOP", "Error")
                            loadingCircle.visibility = View.INVISIBLE
                            hideInfo()
                            showErrors()
                        }
                    }
                }
            }
        }
    }

    fun setUpViews() {
        bindDownloadStateHandling()
        bindErrorButton()
        fragment.viewLifecycleOwner.lifecycleScope.launch {
            fragment.lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.filmInfo.collect { filmInfo ->
                    filmInfo?.let { bindViews(it) }
                }
            }
        }
    }

    private fun bindBackArrow() {
        backArrow.setOnClickListener {
            val action = FilmFragmentDirections.actionFilmFragmentToFilmsListFragment()
            it.findNavController().navigate(action)
        }
    }

    private fun buildCountriesString(film: FilmInfo): String {
        val stringBuilder = StringBuilder()
        val divider = ", "
        var i = 1
        stringBuilder.append(film.countries.first().country)
        while (i < film.countries.size) {
            stringBuilder.append(divider)
            stringBuilder.append(film.countries[i].country)
            i++
        }
        return stringBuilder.toString()
    }

    private fun String.capitalizeFirstLetter(): String {
        if (isEmpty()) {
            return this
        }
        return this.substring(0, 1).uppercase(Locale.ROOT) + this.substring(1)
    }

    private fun buildGenresString(film: FilmInfo): String {
        val stringBuilder = StringBuilder()
        val divider = ", "
        var i = 1
        stringBuilder.append(film.genres.first().genre.capitalizeFirstLetter())
        while (i < film.genres.size) {
            stringBuilder.append(divider)
            stringBuilder.append(film.genres[i].genre.capitalizeFirstLetter())
            i++
        }
        return stringBuilder.toString()
    }

    private fun setUpImage(film: FilmInfo) {
        filmImage.load(film.posterUrl) {
            error(R.drawable.broken_image)
            placeholder(R.drawable.loading_circle)
        }
    }

    private fun hideErrors() {
        errorCloud.visibility = View.INVISIBLE
        errorText.visibility = View.INVISIBLE
        errorButton.visibility = View.INVISIBLE
    }

    private fun showErrors() {
        errorCloud.visibility = View.VISIBLE
        errorText.visibility = View.VISIBLE
        errorButton.visibility = View.VISIBLE
    }

    private fun hideInfo() {
        val state = View.INVISIBLE
        filmImage.visibility = state
        filmName.visibility = state
        filmDescription.visibility = state
        filmGenresTitle.visibility = state
        filmGenres.visibility = state
        filmCountriesTitle.visibility = state
        filmCountries.visibility = state
    }

    private fun showInfo() {
        val state = View.VISIBLE
        filmImage.visibility = state
        filmName.visibility = state
        filmDescription.visibility = state
        filmGenresTitle.visibility = state
        filmGenres.visibility = state
        filmCountriesTitle.visibility = state
        filmCountries.visibility = state
    }

    private fun bindErrorButton() {
        errorButton.setOnClickListener {
            viewModel.loadFilmInfo()
        }
    }
}