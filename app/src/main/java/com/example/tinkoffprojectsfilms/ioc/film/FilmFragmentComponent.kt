package com.example.tinkoffprojectsfilms.ioc.film

import com.example.tinkoffprojectsfilms.ui.stateholders.FilmInfoViewModel
import com.example.tinkoffprojectsfilms.ui.view.film.FilmFragment

class FilmFragmentComponent(
    val viewModel: FilmInfoViewModel,
    val fragment: FilmFragment
)