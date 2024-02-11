package com.example.tinkoffprojectsfilms.ioc.filmslist

import com.example.tinkoffprojectsfilms.databinding.FragmentFilmsListBinding

class FilmsListFragmentViewComponent(
    binding: FragmentFilmsListBinding,
    fragmentComponent: FilmsListFragmentComponent
) {
    val controller: FilmsListViewController = FilmsListViewController(
        binding,
        fragmentComponent.adapter,
        fragmentComponent.fragment,
        fragmentComponent.viewModel
    )
}