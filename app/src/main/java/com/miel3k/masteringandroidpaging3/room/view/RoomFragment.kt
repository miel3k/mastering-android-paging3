package com.miel3k.masteringandroidpaging3.room.view

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import com.miel3k.masteringandroidpaging3.R
import com.miel3k.masteringandroidpaging3.databinding.FragmentRealmBinding
import com.miel3k.masteringandroidpaging3.room.viewmodel.RoomViewModel
import com.miel3k.masteringandroidpaging3.users.view.UsersLoadStateAdapter
import com.miel3k.masteringandroidpaging3.users.view.UsersPagingAdapter
import com.miel3k.masteringandroidpaging3.utils.lifecycleBinding
import com.miel3k.masteringandroidpaging3.utils.switchView
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

/**
 * Created by jmielczarek on 18/09/2022
 */
@AndroidEntryPoint
class RoomFragment : Fragment(R.layout.fragment_room) {

    private val binding: FragmentRealmBinding by lifecycleBinding {
        FragmentRealmBinding.bind(requireView())
    }
    private val viewModel by viewModels<RoomViewModel>()
    private val usersPagingAdapter by lazy { UsersPagingAdapter() }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
        setupRefresh()
        setupBackButton()
        setupToolbar()
        setupPagingDataObserver()
        setupLoadStateFlowObserver()
    }

    private fun setupRecyclerView() {
        val loadStateAdapter = UsersLoadStateAdapter(usersPagingAdapter::retry)
        binding.rvUsers.adapter = usersPagingAdapter.withLoadStateFooter(loadStateAdapter)
    }

    private fun setupRefresh() {
        binding.srlUsers.setOnRefreshListener {
            usersPagingAdapter.refresh()
        }
    }

    private fun setupBackButton() {
        binding.tbUsers.ibBack.setOnClickListener {
            activity?.onBackPressedDispatcher?.onBackPressed()
        }
    }

    private fun setupToolbar() {
        binding.tbUsers.tvTitle.text = requireContext().getString(R.string.room)
    }

    private fun setupPagingDataObserver() {
        viewModel.userItemPagingData.observe(viewLifecycleOwner) {
            viewLifecycleOwner.lifecycleScope.launch {
                usersPagingAdapter.submitData(it)
            }
        }
    }

    private fun setupLoadStateFlowObserver() {
        viewLifecycleOwner.lifecycleScope.launchWhenCreated {
            usersPagingAdapter.loadStateFlow.collectLatest {
                binding.vsUsers.switchView(
                    usersPagingAdapter.isEmpty(),
                    binding.rvUsers,
                    binding.vUsersEmptyState.root
                )
                val refreshState = it.refresh
                binding.srlUsers.isRefreshing = refreshState is LoadState.Loading
            }
        }
    }
}