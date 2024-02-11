package com.example.tinkoffprojectsfilms.ui.stateholders

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tinkoffprojectsfilms.data.model.Answer
import com.example.tinkoffprojectsfilms.data.model.Film
import com.example.tinkoffprojectsfilms.data.repository.NetworkRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import retrofit2.Response

enum class DownloadState{LOADING, DOWNLOAD, ERROR}

class FilmsListViewModel(
    private val repository: NetworkRepository
) : ViewModel() {

    private val _filmsList: MutableStateFlow<List<Film>> = MutableStateFlow(emptyList())
    val filmsList: StateFlow<List<Film>> = _filmsList

    private val _downloadState: MutableStateFlow<DownloadState> = MutableStateFlow(DownloadState.LOADING)
    val downloadState: StateFlow<DownloadState> = _downloadState

    init {
        loadFilms()
    }

    fun loadFilms() {
        viewModelScope.launch(Dispatchers.IO) {
            val response: Response<Answer>
            try {
                Log.v("TESTOSTATE", "loading")
                _downloadState.value = DownloadState.LOADING
                response = repository.loadFilms()
                if(response.isSuccessful){
                    Log.v("TESTOSTATE", "download")
                    _downloadState.value = DownloadState.DOWNLOAD
                    _filmsList.value = response.body()!!.films
                }
            } catch (e: Exception) {
                Log.v("TESTOSTATE", "error")
                _downloadState.value = DownloadState.ERROR
            }
        }
    }
}