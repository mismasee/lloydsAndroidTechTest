package com.lloydtechassignment.presentation.activity

import android.os.Bundle
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import com.lloydtechassignment.R
import com.lloydtechassignment.domain.model.AnimalsRespItem
import com.lloydtechassignment.databinding.ActivityMainBinding
import com.lloydtechassignment.presentation.adapters.AnimalListAdapter
import com.lloydtechassignment.presentation.base.BaseActivity
import com.lloydtechassignment.util.*
import com.lloydtechassignment.viewmodel.AnimalViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

/**
 * First Activity which will fetch the animal list
 * */
class AnimalActivity : BaseActivity<AnimalViewModel, ActivityMainBinding>() {

    override val viewmodel by viewModel<AnimalViewModel>()
    private val adapter = AnimalListAdapter(this::onItemClicked)


    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.AppTheme) // Set AppTheme before setting content view.

        super.onCreate(savedInstanceState)
        setContentView(viewBinding.root)

        initView()
        subscribeObservers()
    }

    override fun onStart() {
        super.onStart()
        getAnimalData()
    }

    /**Method is used to initialize views* */
    private fun initView() {
        viewBinding.run {
            postsRecyclerView.adapter = adapter
        }
    }


    /**
     * Observe network changes i.e. Internet Connectivity
     * get Animal Data
     */
    private fun getAnimalData() {
        if (isNetworkAvailable()) {
            viewBinding.networkStatusLayout.hide()
            if (adapter.itemCount == 0) viewmodel.getAllAnimalFacts()
        } else {
            viewBinding.textViewNetworkStatus.text = getString(R.string.text_no_connectivity)
            viewBinding.networkStatusLayout.apply {
                show()
                setBackgroundColor(
                    ContextCompat.getColor(
                        this@AnimalActivity, R.color.colorStatusNotConnected
                    )
                )
            }
        }
    }


    /**
     * Method is used to observe viewmodel livedata
     * */
    private fun subscribeObservers() {
        viewmodel.dataState.observe(this, Observer { dataState ->
            when (dataState) {
                is DataState.Success -> if (dataState.data?.isNotEmpty() == true) {
                    adapter.submitList(dataState.data.toMutableList())
                }
                is DataState.Loading -> showToast(getString(R.string.loading_str))
                is DataState.Failure -> showToast(getString(R.string.something_went_wrong))
            }
        })
    }

    override fun getBinding(): ActivityMainBinding = ActivityMainBinding.inflate(layoutInflater)

    private fun onItemClicked(animalsRespItem: AnimalsRespItem) {
        val intent = AnimalDetailActivity.getStartIntent(this, animalsRespItem)
        startActivity(intent)
    }
}