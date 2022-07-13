package com.lloydtechassignment.ui.activity

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.os.Bundle
import androidx.activity.viewModels
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import com.lloydtechassignment.R
import com.lloydtechassignment.data.model.AnimalsRespItem
import com.lloydtechassignment.databinding.ActivityMainBinding
import com.lloydtechassignment.ui.adapters.AnimalListAdapter
import com.lloydtechassignment.ui.base.BaseActivity
import com.lloydtechassignment.util.*
import com.lloydtechassignment.viewmodel.AnimalViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity<AnimalViewModel, ActivityMainBinding>() {

    override val mViewModel: AnimalViewModel by viewModels()
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
        handleNetworkChanges()
    }

    private fun initView() {
        mViewBinding.run {
            postsRecyclerView.adapter = mAdapter
        }
    }


    /**
     * Observe network changes i.e. Internet Connectivity
     */
    private fun handleNetworkChanges() {
        NetworkUtils.getNetworkLiveData(applicationContext).observe(this) { isConnected ->
            if (!isConnected) {
                mViewBinding.textViewNetworkStatus.text = getString(R.string.text_no_connectivity)
                mViewBinding.networkStatusLayout.apply {
                    show()
                    setBackgroundColor(ContextCompat.getColor(
                        this@MainActivity,R.color.colorStatusNotConnected))
                }
            } else {
                mViewBinding.networkStatusLayout.apply {
                    setBackgroundColor(ContextCompat.getColor(
                        this@MainActivity,R.color.colorStatusNotConnected))

                    animate()
                        .alpha(1f)
                        .setStartDelay(ANIMATION_DURATION)
                        .setDuration(ANIMATION_DURATION)
                        .setListener(object : AnimatorListenerAdapter() {
                            override fun onAnimationEnd(animation: Animator) {
                                hide()
                            }
                        })
                }
                if (mAdapter.itemCount == 0) mViewModel.getAllAnimalFacts()
            }
        }
    }




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