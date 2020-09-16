package com.hamzasharuf.pulse.ui.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.hamzasharuf.pulse.R
import com.hamzasharuf.pulse.databinding.FragmentHomeBinding
import com.hamzasharuf.pulse.utils.MarginItemDecoration
import com.hamzasharuf.pulse.utils.Status
import com.hamzasharuf.pulse.utils.adapters.lists.news.NewsAdapter
import com.hamzasharuf.pulse.utils.adapters.lists.news.NewsClickListener
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.coroutines.ExperimentalCoroutinesApi

@ExperimentalCoroutinesApi
@AndroidEntryPoint
class HomeFragment : Fragment() {

    private val viewModel: HomeViewModel by viewModels()
    private lateinit var binding: FragmentHomeBinding
    private lateinit var mAdapter: NewsAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(layoutInflater, R.layout.fragment_home, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setupRecycler()
        setObservers()
        if (!viewModel.isNewsAvailable) viewModel.getNews()
    }

    private fun setupRecycler() {

        // Setup the adapter
        mAdapter = NewsAdapter(NewsClickListener {
            val action = HomeFragmentDirections.actionNavigationHomeToArticleDetailsFragment(it)
            findNavController().navigate(action)
        })

        // Setup the Recyclerview
        rv_news_recycler.apply {
            layoutManager = LinearLayoutManager(requireContext())
            setHasFixedSize(true)
            addItemDecoration(
                MarginItemDecoration(resources.getDimension(R.dimen.news_recycler_item_margins).toInt())
            )
            adapter = mAdapter
        }
    }

    private fun setObservers() {
        viewModel.news.observe(viewLifecycleOwner, {
            when (it.status) {
                Status.SUCCESS -> {
                    pb_news_progressbar.visibility = View.GONE
                    mAdapter.submitList(it.data)
                }
                Status.ERROR -> {
                    pb_news_progressbar.visibility = View.GONE
                    Toast.makeText(requireContext(), "${it.message}", Toast.LENGTH_SHORT).show()
                    Log.d(TAG, "setObservers: ${it.message}")
                }
                Status.LOADING -> {
                    pb_news_progressbar.visibility = View.VISIBLE
                }
            }
        })
    }
    
    companion object{
        private const val TAG = "debug: HomeFragment"
    }
}