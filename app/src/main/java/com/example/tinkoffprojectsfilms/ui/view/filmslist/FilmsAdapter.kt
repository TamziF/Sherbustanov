package com.example.tinkoffprojectsfilms.ui.view.filmslist

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.example.tinkoffprojectsfilms.data.model.Film
import com.example.tinkoffprojectsfilms.databinding.FilmViewHolderBinding

class FilmsAdapter(
    private val fragment: FilmsListFragment
) : ListAdapter<Film, FilmViewHolder>(
    DiffUtilCallBack()
) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FilmViewHolder {
        val binding =
            FilmViewHolderBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return FilmViewHolder(binding, fragment)
    }

    override fun onBindViewHolder(holder: FilmViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}