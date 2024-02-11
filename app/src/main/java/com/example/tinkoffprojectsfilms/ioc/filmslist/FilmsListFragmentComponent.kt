package com.example.tinkoffprojectsfilms.ioc.filmslist

import com.example.tinkoffprojectsfilms.ui.stateholders.FilmsListViewModel
import com.example.tinkoffprojectsfilms.ui.view.filmslist.FilmsAdapter
import com.example.tinkoffprojectsfilms.ui.view.filmslist.FilmsListFragment

class FilmsListFragmentComponent(
    val viewModel: FilmsListViewModel,
    val fragment: FilmsListFragment
) {
    val adapter = FilmsAdapter(fragment)
}