package com.example.tinkoffprojectsfilms.ioc

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.tinkoffprojectsfilms.data.repository.NetworkRepository
import com.example.tinkoffprojectsfilms.ui.stateholders.FilmInfoViewModel
import com.example.tinkoffprojectsfilms.ui.stateholders.FilmsListViewModel

class ViewModelFactory(private val repository: NetworkRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T = when (modelClass) {
        FilmsListViewModel::class.java -> FilmsListViewModel(repository)
        FilmInfoViewModel::class.java -> FilmInfoViewModel(repository)
        else -> throw IllegalArgumentException("Указана неизвестная ViewModel")
    } as T
}