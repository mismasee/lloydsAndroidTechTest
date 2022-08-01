package com.lloydtechassignment.presentation.activity

import android.os.Bundle
import com.lloydtechassignment.R
import com.lloydtechassignment.databinding.ActivityMainBinding
import com.lloydtechassignment.presentation.base.BaseActivity
import com.lloydtechassignment.presentation.fragment.AnimalListFragment
import com.lloydtechassignment.viewmodel.AnimalViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

/**
 * First Activity which will fetch the animal list
 * */
class AnimalActivity : BaseActivity<AnimalViewModel, ActivityMainBinding>() {

    override val viewmodel by viewModel<AnimalViewModel>()
    override fun getBinding(): ActivityMainBinding = ActivityMainBinding.inflate(layoutInflater)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(viewBinding.root)

        navigateToAnimalList()
    }

    private fun navigateToAnimalList() {
        supportFragmentManager.beginTransaction()
            .replace(
                R.id.container,
                AnimalListFragment.newInstance(),
                AnimalListFragment.FRAGMENT_NAME
            ).commitAllowingStateLoss()
    }


}