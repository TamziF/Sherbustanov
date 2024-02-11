package com.example.tinkoffprojectsfilms.ui.stateholders

import androidx.lifecycle.ViewModel
import com.example.tinkoffprojectsfilms.data.model.FilmInfo
import com.example.tinkoffprojectsfilms.data.repository.NetworkRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class FilmInfoViewModel(
    val repository: NetworkRepository
): ViewModel() {

    private val _film: MutableStateFlow<FilmInfo?> = MutableStateFlow(null)
    val film: StateFlow<FilmInfo?> = _film
}