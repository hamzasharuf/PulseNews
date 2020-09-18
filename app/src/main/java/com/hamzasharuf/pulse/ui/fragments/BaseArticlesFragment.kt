package com.hamzasharuf.pulse.ui.fragments

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.hamzasharuf.pulse.R
import com.hamzasharuf.pulse.databinding.FragmentHomeBinding
import com.hamzasharuf.pulse.utils.MarginItemDecoration
import com.hamzasharuf.pulse.utils.Status
import com.hamzasharuf.pulse.utils.adapters.lists.news.NewsAdapter
import com.hamzasharuf.pulse.utils.adapters.lists.news.NewsClickListener
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber

abstract class BaseArticlesFragment : Fragment() {

    lateinit var mAdapter: NewsAdapter
    lateinit var binding: FragmentHomeBinding
    val viewModel: BaseArticlesViewModel by viewModels()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Timber.d("onCreateView: onCreateView called")
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        Timber.d("onActivityCreated: onActivityCreated called")
        setupRecycler()
        setupSwipeRefreshLayout()
        setupObservers()
        loadData(false)
    }

    private fun setupObservers() {
        viewModel.allNews.observe(viewLifecycleOwner, {
            when (it.status) {
                Status.SUCCESS -> {
                    binding.swipeRefresh.isRefreshing = false
                    binding.loadingIndicator.visibility = View.GONE
                    mAdapter.submitList(it.data)
                }
                Status.ERROR -> {
                    binding.swipeRefresh.isRefreshing = false
                    binding.loadingIndicator.visibility = View.GONE
                    Toast.makeText(requireContext(), "${it.message}", Toast.LENGTH_SHORT).show()
                    Timber.d("setupObservers: ${it.message}")
                }
                Status.LOADING -> {
                    // binding.loadingIndicator.visibility = View.VISIBLE
                    binding.swipeRefresh.isRefreshing = true
                }
            }
        })

    }

    private fun setupSwipeRefreshLayout() {
        binding.swipeRefresh.setColorSchemeColors(
            ContextCompat.getColor(requireContext(), R.color.swipe_color_1),
            ContextCompat.getColor(requireContext(), R.color.swipe_color_2),
            ContextCompat.getColor(requireContext(), R.color.swipe_color_3),
            ContextCompat.getColor(requireContext(), R.color.swipe_color_4)
        );

        binding.swipeRefresh.setOnRefreshListener {
            loadData(true)
        }
    }

    private fun setupRecycler() {

        mAdapter = NewsAdapter(NewsClickListener {
            // Direct to the details fragment
        })

        with(binding.recyclerView) {
            layoutManager = LinearLayoutManager(requireContext())
            hasFixedSize()
            addItemDecoration(MarginItemDecoration(16))
            adapter = mAdapter
        }
    }

    abstract fun loadData(isRefreshing: Boolean)

}