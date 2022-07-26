package com.lloydtechassignment.presentation.fragment

import android.os.Bundle
import android.view.View
import com.lloydtechassignment.R
import com.lloydtechassignment.data.models.AnimalUIModel
import com.lloydtechassignment.databinding.FragmentFavouriteBinding
import com.lloydtechassignment.presentation.activity.AnimalDetailActivity
import com.lloydtechassignment.presentation.adapters.AnimalListAdapter
import com.lloydtechassignment.presentation.base.BaseFragment
import com.lloydtechassignment.util.DataState
import com.lloydtechassignment.util.hide
import com.lloydtechassignment.util.show
import com.lloydtechassignment.util.showToast
import com.lloydtechassignment.viewmodel.FavoriteViewmodel
import org.koin.androidx.viewmodel.ext.android.viewModel

class FavouriteFragment : BaseFragment<FavoriteViewmodel,FragmentFavouriteBinding>() {


    companion object {

        val FRAGMENT_NAME:String = FavouriteFragment::class.java.name

        @JvmStatic
        fun newInstance() = FavouriteFragment()
    }

    override fun getViewBinding() = FragmentFavouriteBinding.inflate(layoutInflater)
    override val viewmodel by viewModel<FavoriteViewmodel>()
    private val adapter = AnimalListAdapter(this::onItemClicked)


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.apply {
            postsRecyclerView.adapter = adapter
        }
        subscribeObservers()
        viewmodel.getAllFavoriteList()
    }

    /**
    * Method is used to observe viewmodel livedata
    * */
    private fun subscribeObservers() {
        viewmodel.dataState.observe(viewLifecycleOwner) { dataState ->
            when (dataState) {
                is DataState.Success -> if (dataState.data?.isNotEmpty() == true) {
                    adapter.submitList(dataState.data.toMutableList())
                    binding.noDataAvailable.hide()
                }else binding.noDataAvailable.show()

                is DataState.Loading -> activity?.showToast(getString(R.string.loading_str))

                is DataState.Failure -> activity?.showToast(getString(R.string.something_went_wrong))
            }
        }
    }

    private fun onItemClicked(animalUIModel: AnimalUIModel) {
        context?.let {
            val intent = AnimalDetailActivity.getStartIntent(it, animalUIModel)
            startActivity(intent)
        }
    }
}
