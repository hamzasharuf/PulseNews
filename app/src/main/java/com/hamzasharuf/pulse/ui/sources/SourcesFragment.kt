package com.hamzasharuf.pulse.ui.sources

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.hamzasharuf.pulse.R
import com.hamzasharuf.pulse.databinding.FragmentSourcesBinding
import com.hamzasharuf.pulse.ui.MainActivityViewModel
import com.hamzasharuf.pulse.utils.Status
import com.hamzasharuf.pulse.utils.adapters.lists.publications.SourcesClickListener
import com.hamzasharuf.pulse.utils.adapters.lists.publications.SourcesListAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@ExperimentalCoroutinesApi
@AndroidEntryPoint
class SourcesFragment : Fragment() {

    private val viewModel: SourcesViewModel by viewModels()
    private val sharedViewModel: MainActivityViewModel by activityViewModels()
    private lateinit var binding: FragmentSourcesBinding
    private lateinit var mAdapter: SourcesListAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_sources, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        setupRecycler()
        setObservers()
        if (!viewModel.isSourcesListAvailable) viewModel.getSources()

    }

    private fun setObservers() {
        viewModel.sources.observe(viewLifecycleOwner, {
            when (it.status) {
                Status.SUCCESS -> {
                    binding.progressbar.visibility = View.GONE
                    mAdapter.submitList(it.data)
                }
                Status.ERROR -> {
                    binding.progressbar.visibility = View.GONE
                    Toast.makeText(requireContext(), "${it.message}", Toast.LENGTH_SHORT).show()
                    Log.d(TAG, "setObservers: ${it.message}")
                }
                Status.LOADING -> {
                    binding.progressbar.visibility = View.VISIBLE
                }
            }
        })
    }

    private fun setupRecycler() {
        mAdapter = SourcesListAdapter(SourcesClickListener { item, position ->
            item.isChecked = !item.isChecked
            mAdapter.notifyItemChanged(position)
        })
        with(binding.recyclerview) {
            layoutManager = LinearLayoutManager(requireContext())
            setHasFixedSize(true)
            adapter = mAdapter
            addItemDecoration(DividerItemDecoration(requireContext(), (layoutManager as LinearLayoutManager).orientation))
        }

    }



    companion object {
        private const val TAG = "debug: SourcesFragment"
    }

}