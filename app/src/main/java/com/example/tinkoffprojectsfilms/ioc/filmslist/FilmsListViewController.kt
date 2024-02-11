package com.example.tinkoffprojectsfilms.ioc.filmslist

import android.util.Log
import android.view.View
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.tinkoffprojectsfilms.databinding.FragmentFilmsListBinding
import com.example.tinkoffprojectsfilms.ui.stateholders.DownloadState
import com.example.tinkoffprojectsfilms.ui.stateholders.FilmsListViewModel
import com.example.tinkoffprojectsfilms.ui.view.filmslist.FilmsAdapter
import com.example.tinkoffprojectsfilms.ui.view.filmslist.FilmsListFragment
import kotlinx.coroutines.launch

class FilmsListViewController(
    binding: FragmentFilmsListBinding,
    private val adapter: FilmsAdapter,
    private val fragment: FilmsListFragment,
    private val viewModel: FilmsListViewModel
) {

    private val recyclerView = binding.recyclerView
    private val errorCloud = binding.errorCloud
    private val errorText = binding.errorText
    private val errorButton = binding.errorButton
    private val loadingCircle = binding.loadingCircle

    fun setUpViews() {
        setUpAdapter()
        setUpListUpdatesObserver()
        bindDownloadStateHandling()
        bindErrorButton()
    }

    private fun bindDownloadStateHandling() {
        fragment.viewLifecycleOwner.lifecycleScope.launch {
            fragment.lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.downloadState.collect {
                    when (it) {
                        DownloadState.LOADING -> {
                            Log.v("TESTOSTERON", "LOADING")
                            hideErrors()
                            recyclerView.visibility = View.INVISIBLE
                            loadingCircle.visibility = View.VISIBLE
                        }

                        DownloadState.DOWNLOAD -> {
                            Log.v("TESTOSTERON", "DOWNLOAD")
                            hideErrors()
                            loadingCircle.visibility = View.INVISIBLE
                            recyclerView.visibility = View.VISIBLE
                        }

                        DownloadState.ERROR -> {
                            loadingCircle.visibility = View.INVISIBLE
                            recyclerView.visibility = View.INVISIBLE
                            showErrors()
                        }
                    }
                }
            }
        }
    }

    private fun setUpAdapter() {
        recyclerView.layoutManager = LinearLayoutManager(fragment.context)
        recyclerView.adapter = adapter
    }

    private fun setUpListUpdatesObserver() {
        fragment.viewLifecycleOwner.lifecycleScope.launch {
            fragment.lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.filmsList.collect {
                    adapter.submitList(it)
                }
            }
        }
    }

    private fun hideErrors(){
        errorCloud.visibility = View.INVISIBLE
        errorText.visibility = View.INVISIBLE
        errorButton.visibility = View.INVISIBLE
    }

    private fun showErrors(){
        errorCloud.visibility = View.VISIBLE
        errorText.visibility = View.VISIBLE
        errorButton.visibility = View.VISIBLE
    }

    private fun bindErrorButton(){
        errorButton.setOnClickListener{
            viewModel.loadFilms()
        }
    }

}