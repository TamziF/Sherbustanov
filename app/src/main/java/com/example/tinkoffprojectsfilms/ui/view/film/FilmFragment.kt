package com.example.tinkoffprojectsfilms.ui.view.film

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResultListener
import androidx.fragment.app.viewModels
import com.example.tinkoffprojectsfilms.App
import com.example.tinkoffprojectsfilms.R
import com.example.tinkoffprojectsfilms.databinding.FragmentFilmBinding
import com.example.tinkoffprojectsfilms.ioc.ApplicationComponent
import com.example.tinkoffprojectsfilms.ioc.film.FilmFragmentComponent
import com.example.tinkoffprojectsfilms.ioc.film.FilmFragmentViewComponent
import com.example.tinkoffprojectsfilms.ui.stateholders.FilmInfoViewModel

class FilmFragment : Fragment() {

    private lateinit var binding: FragmentFilmBinding

    private val applicationComponent: ApplicationComponent
        get() = App.get(requireContext()).applicationComponent

    private lateinit var fragmentComponent: FilmFragmentComponent
    private var fragmentViewComponent: FilmFragmentViewComponent? = null
    private val viewModel: FilmInfoViewModel by viewModels { applicationComponent.viewModelFactory }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setFragmentResultListener(R.string.request_key.toString()) { _, bundle ->
            bundle.getInt(R.string.bundle_key.toString()).let { viewModel.setFilmId(it) }
        }

        fragmentComponent = FilmFragmentComponent(
            viewModel,
            this
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentFilmBinding.inflate(inflater, container, false)

        fragmentViewComponent = FilmFragmentViewComponent(
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