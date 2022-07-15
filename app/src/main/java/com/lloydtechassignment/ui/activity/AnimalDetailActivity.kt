package com.lloydtechassignment.ui.activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.bumptech.glide.Glide
import com.lloydtechassignment.data.model.AnimalsRespItem
import com.lloydtechassignment.databinding.ActivityAnimalDetailsBinding
import com.lloydtechassignment.ui.base.BaseActivity
import com.lloydtechassignment.viewmodel.AnimalDetailViewmodel
import org.koin.androidx.viewmodel.ext.android.viewModel

/**
 * Animal Detail Activity to show Animals detail
 * */
class AnimalDetailActivity() : BaseActivity<AnimalDetailViewmodel,ActivityAnimalDetailsBinding>() {

    override val mViewModel by viewModel<AnimalDetailViewmodel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(mViewBinding.root)

        mViewModel.animalData = intent.getParcelableExtra(EXTRA_DATA)
    }


    override fun getViewBinding(): ActivityAnimalDetailsBinding =
        ActivityAnimalDetailsBinding.inflate(layoutInflater)

    companion object {
        private const val EXTRA_DATA = "extra_data"

        /**
         * Method is used to send Extra Data to the Activity and returning its intent
         * [context] is used to send Context of calling activity
         * [animalsRespItem] is the data parameter*/
        fun getStartIntent(
            context: Context,
            animalsRespItem: AnimalsRespItem
        ) = Intent(context, AnimalDetailActivity::class.java).apply { putExtra(EXTRA_DATA, animalsRespItem) }
    }

    override fun onStart() {
        super.onStart()
        setAnimalData()
    }

    /**
     * Set Animal Data is used for setting Animal Data on Animal Detail Page
     * */
    private fun setAnimalData(){
        mViewModel.animalData?.let {
            mViewBinding.content.apply {
                animalTitle.text = it.name
                animalGeo.text = it.geo_range
            }
            mViewBinding.toolbar.title = it.name
            Glide.with(this)
                .load(it.image_link) // image url
                .into(mViewBinding.imageView)
        }
    }

}