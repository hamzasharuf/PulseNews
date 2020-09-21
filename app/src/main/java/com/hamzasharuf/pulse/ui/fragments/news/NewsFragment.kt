package com.hamzasharuf.pulse.ui.fragments.news

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.DefaultItemAnimator
import com.hamzasharuf.pulse.R
import com.hamzasharuf.pulse.data.models.NewsSection
import com.hamzasharuf.pulse.databinding.FragmentNewsBinding
import com.hamzasharuf.pulse.ui.NavigationSharedViewModel
import com.hamzasharuf.pulse.utils.adapters.lists.news.NewsAdapter
import com.hamzasharuf.pulse.utils.adapters.lists.news.NewsClickListener
import com.hamzasharuf.pulse.utils.states.ScreenState
import com.hamzasharuf.pulse.utils.states.Status
import com.hamzasharuf.pulse.utils.view.MarginItemDecoration
import com.hamzasharuf.pulse.utils.view.SpeedyLinearLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_news.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


@AndroidEntryPoint
class NewsFragment(val section: NewsSection = NewsSection.HOME) : Fragment() {

    private lateinit var mAdapter: NewsAdapter
    private lateinit var binding: FragmentNewsBinding
    private val viewModel: NewsViewModel by viewModels()
    private val sharedViewModel: NavigationSharedViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_news, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
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

                    GlobalScope.launch {
                        delay(1000)
                        kotlin.runCatching {
                            // If the user changes the tab before scrolling, it won't crash
                            recycler_view.smoothScrollToPosition(0)
                        }

                    }


                }
                Status.ERROR -> {
                    binding.swipeRefresh.isRefreshing = false
                    binding.loadingIndicator.visibility = View.GONE
                    Toast.makeText(requireContext(), "${it.message}", Toast.LENGTH_SHORT).show()
                }
                Status.LOADING -> {
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
        )

        binding.swipeRefresh.setOnRefreshListener {
            loadData(true)
        }
    }

    private fun setupRecycler() {
        mAdapter = NewsAdapter(NewsClickListener {
            sharedViewModel.setScreenState(ScreenState.DetailsScreenState)
        })

        with(binding.recyclerView) {
            layoutManager = SpeedyLinearLayoutManager(requireContext())
            hasFixedSize()
            addItemDecoration(MarginItemDecoration(32))
            adapter = mAdapter
            itemAnimator = DefaultItemAnimator()
        }
    }

    private fun loadData(isRefreshing: Boolean) {
            if (isRefreshing || !viewModel.isNewsAvailable)
                viewModel.getNews(section)
    }

}