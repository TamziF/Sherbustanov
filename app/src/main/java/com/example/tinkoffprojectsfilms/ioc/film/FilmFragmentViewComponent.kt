package com.example.tinkoffprojectsfilms.ioc.film

import com.example.tinkoffprojectsfilms.databinding.FragmentFilmBinding

class FilmFragmentViewComponent(
    binding: FragmentFilmBinding,
    fragmentComponent: FilmFragmentComponent
) {
    val controller: FilmFragmentViewController = FilmFragmentViewController(
        binding,
        fragmentComponent.fragment,
        fragmentComponent.viewModel
    )
}