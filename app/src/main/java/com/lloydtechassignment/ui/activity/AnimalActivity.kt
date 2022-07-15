package com.lloydtechassignment.ui.activity

import android.os.Bundle
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import com.lloydtechassignment.R
import com.lloydtechassignment.data.model.AnimalsRespItem
import com.lloydtechassignment.databinding.ActivityMainBinding
import com.lloydtechassignment.ui.adapters.AnimalListAdapter
import com.lloydtechassignment.ui.base.BaseActivity
import com.lloydtechassignment.util.*
import com.lloydtechassignment.viewmodel.AnimalViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

/**
 * First Activity which will fetch the animal list
 * */
class AnimalActivity : BaseActivity<AnimalViewModel, ActivityMainBinding>() {

    override val mViewModel by viewModel<AnimalViewModel>()
    private val mAdapter = AnimalListAdapter(this::onItemClicked)


    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.AppTheme) // Set AppTheme before setting content view.

        super.onCreate(savedInstanceState)
        setContentView(mViewBinding.root)

        initView()
        subscribeObservers()
    }

    override fun onStart() {
        super.onStart()
        getAnimalData()
    }

    /**Method is used to initialize views* */
    private fun initView() {
        mViewBinding.run {
            postsRecyclerView.adapter = mAdapter
        }
    }


    /**
     * Observe network changes i.e. Internet Connectivity
     * get Animal Data
     */
    private fun getAnimalData() {
        if (isNetworkAvailable()) {
            mViewBinding.networkStatusLayout.hide()
            if (mAdapter.itemCount == 0) mViewModel.getAllAnimalFacts()
        } else {
            mViewBinding.textViewNetworkStatus.text = getString(R.string.text_no_connectivity)
            mViewBinding.networkStatusLayout.apply {
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
        mViewModel.dataState.observe(this, Observer { dataState ->
            when (dataState) {
                is DataState.Success -> if (dataState.data?.isNotEmpty() == true) {
                    mAdapter.submitList(dataState.data.toMutableList())
                }
                is DataState.Loading -> showToast(getString(R.string.loading_str))
                is DataState.Failure -> showToast(getString(R.string.something_went_wrong))
            }
        })
    }

    override fun getViewBinding(): ActivityMainBinding = ActivityMainBinding.inflate(layoutInflater)

    private fun onItemClicked(animalsRespItem: AnimalsRespItem) {
        val intent = AnimalDetailActivity.getStartIntent(this, animalsRespItem)
        startActivity(intent)
    }
}