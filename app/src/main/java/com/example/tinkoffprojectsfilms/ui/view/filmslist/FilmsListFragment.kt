package com.example.tinkoffprojectsfilms.ui.view.filmslist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.tinkoffprojectsfilms.App
import com.example.tinkoffprojectsfilms.databinding.FragmentFilmsListBinding
import com.example.tinkoffprojectsfilms.ioc.filmslist.FilmsListFragmentComponent
import com.example.tinkoffprojectsfilms.ioc.filmslist.FilmsListFragmentViewComponent
import com.example.tinkoffprojectsfilms.ui.stateholders.FilmsListViewModel

class FilmsListFragment : Fragment() {

    private lateinit var binding: FragmentFilmsListBinding

    private val applicationComponent
        get() = App.get(requireContext()).applicationComponent

    private lateinit var fragmentComponent: FilmsListFragmentComponent
    private var fragmentViewComponent: FilmsListFragmentViewComponent? = null
    private val viewModel: FilmsListViewModel by viewModels { applicationComponent.viewModelFactory }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        fragmentComponent = FilmsListFragmentComponent(
            viewModel,
            this
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentFilmsListBinding.inflate(inflater, container, false)

        fragmentViewComponent = FilmsListFragmentViewComponent(
            binding,
            fragmentComponent
        ).apply {
            controller.setUpViews()
        }

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()

        fragmentViewComponent = null
    }
}