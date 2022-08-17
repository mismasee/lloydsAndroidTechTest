package com.lloydtechassignment.presentation.fragment

import android.os.Bundle
import android.view.View
import androidx.core.content.ContextCompat
import com.lloydtechassignment.R
import com.lloydtechassignment.presentation.models.AnimalUIModel
import com.lloydtechassignment.databinding.FragmentAnimalListBinding
import com.lloydtechassignment.presentation.activity.AnimalDetailActivity
import com.lloydtechassignment.presentation.adapters.AnimalListAdapter
import com.lloydtechassignment.presentation.base.BaseFragment
import com.lloydtechassignment.util.*
import com.lloydtechassignment.presentation.viewmodel.AnimalViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class AnimalListFragment : BaseFragment<AnimalViewModel,FragmentAnimalListBinding>() {

    companion object {
        val FRAGMENT_NAME: String = AnimalListFragment::class.java.name

        @JvmStatic
        fun newInstance() = AnimalListFragment()
    }

    override fun getViewBinding() = FragmentAnimalListBinding.inflate(layoutInflater)
    override val viewmodel by viewModel<AnimalViewModel>()
    private val adapter = AnimalListAdapter(this::onItemClicked)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initView()
        subscribeObservers()
    }

    override fun onStart() {
        super.onStart()
        getAnimalData()
    }

    /**Method is used to initialize views* */
    private fun initView() {
        binding.run {
            postsRecyclerView.adapter = adapter
            ivFav.setOnClickListener { navigateToFavorites() }
        }
    }

    /**
     * Observe network changes i.e. Internet Connectivity
     * get Animal Data
     */
    private fun getAnimalData() {
        if (context?.isNetworkAvailable() == true) {
             binding.networkStatusLayout.hide()
            if (adapter.itemCount == 0) viewmodel.getAllAnimalFacts()
        } else {
            binding.textViewNetworkStatus.text = getString(R.string.text_no_connectivity)
            binding.networkStatusLayout.apply {
                show()
                setBackgroundColor(
                    ContextCompat.getColor(
                        context, R.color.colorStatusNotConnected
                    )
                )
            }
        }
    }

    /**
     * Method is used to observe viewmodel livedata
     * */
    private fun subscribeObservers() {
        viewmodel.dataState.observe(viewLifecycleOwner, androidx.lifecycle.Observer { dataState ->
            when (dataState) {
                is DataState.Success -> if (dataState.data?.isNotEmpty() == true) {
                    adapter.submitList(dataState.data.toMutableList())
                }
                is DataState.Loading -> activity?.showToast(getString(R.string.loading_str))
                is DataState.Failure -> activity?.showToast(getString(R.string.something_went_wrong))
            }
        })
    }

    private fun onItemClicked(animalUIModel: AnimalUIModel) {
        context?.let {
            val intent = AnimalDetailActivity.getStartIntent(it, animalUIModel)
            startActivity(intent)
        }
    }

    private fun navigateToFavorites() {
        parentFragmentManager.beginTransaction()
            .replace(R.id.container,
                FavouriteFragment.newInstance(),
                FavouriteFragment.FRAGMENT_NAME
            )
            .addToBackStack(FavouriteFragment.FRAGMENT_NAME)
            .commitAllowingStateLoss()
    }
}