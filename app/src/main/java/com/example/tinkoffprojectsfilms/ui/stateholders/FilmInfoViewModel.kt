package com.example.tinkoffprojectsfilms.ui.stateholders

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tinkoffprojectsfilms.data.model.Answer
import com.example.tinkoffprojectsfilms.data.model.FilmInfo
import com.example.tinkoffprojectsfilms.data.repository.NetworkRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import retrofit2.Response

class FilmInfoViewModel(
    private val repository: NetworkRepository
): ViewModel() {

    private val _filmInfo: MutableStateFlow<FilmInfo?> = MutableStateFlow(null)
    val filmInfo: StateFlow<FilmInfo?> = _filmInfo

    private val _downloadState: MutableStateFlow<DownloadState> = MutableStateFlow(DownloadState.LOADING)
    val downloadState: StateFlow<DownloadState> = _downloadState

    private var filmId: Int = 0

    fun setFilmId(id: Int){
        filmId = id
        loadFilmInfo()
    }

    fun loadFilmInfo(){
        viewModelScope.launch(Dispatchers.IO) {
            val response: Response<FilmInfo>
            try {
                Log.v("TESTOSTERON", "Loading")
                _downloadState.value = DownloadState.LOADING
                response = repository.loadFilmInfo(filmId)
                if(response.isSuccessful){
                    Log.v("TESTOSTERON", "Download")
                    _downloadState.value = DownloadState.DOWNLOAD
                    _filmInfo.value = response.body()
                }
            } catch (e: Exception) {
                e.message?.let { Log.v("TESTOSTERON", it) }
                _downloadState.value = DownloadState.ERROR
            }
        }
    }
}