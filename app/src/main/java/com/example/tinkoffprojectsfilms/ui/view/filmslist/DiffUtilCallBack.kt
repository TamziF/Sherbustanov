package com.example.tinkoffprojectsfilms.ui.view.filmslist

import androidx.recyclerview.widget.DiffUtil
import com.example.tinkoffprojectsfilms.data.model.Film

class DiffUtilCallBack : DiffUtil.ItemCallback<Film>() {
    override fun areItemsTheSame(oldItem: Film, newItem: Film): Boolean {
        return oldItem.filmId == newItem.filmId
    }

    override fun areContentsTheSame(oldItem: Film, newItem: Film): Boolean {
        return oldItem == newItem
    }
}