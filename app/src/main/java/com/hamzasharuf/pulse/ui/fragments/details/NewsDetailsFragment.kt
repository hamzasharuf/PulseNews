package com.hamzasharuf.pulse.ui.fragments.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.hamzasharuf.pulse.R
import com.hamzasharuf.pulse.databinding.FragmentNewsDetailsBinding
import com.hamzasharuf.pulse.ui.NavigationSharedViewModel
import com.hamzasharuf.pulse.utils.states.ScreenState
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class NewsDetailsFragment : Fragment() {

    private lateinit var binding: FragmentNewsDetailsBinding
    private val viewModel: NewsDetailsViewModel by viewModels()
    private val sharedViewModel: NavigationSharedViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_news_details, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        requireActivity().onBackPressedDispatcher.addCallback((onBackPressed))
    }

    private val onBackPressed = object: OnBackPressedCallback(true) {
        override fun handleOnBackPressed() {

            // TODO : Make sure the appbar animation finished before closing [Try to use: DetailsAppbarAnimationState.kt]
            // TODO : Check the popBackStack before navigation to ensure it's not empty and that the previous fragment is the news fragment
            sharedViewModel.setScreenState(ScreenState.NewsScreenState)
            findNavController().popBackStack()
        }

    }

}