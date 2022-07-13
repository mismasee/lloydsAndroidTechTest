package com.lloydtechassignment.ui.activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import com.bumptech.glide.Glide
import com.lloydtechassignment.data.model.AnimalsRespItem
import com.lloydtechassignment.databinding.ActivityAnimalDetailsBinding
import com.lloydtechassignment.ui.base.BaseActivity
import com.lloydtechassignment.viewmodel.AnimalDetailViewmodel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AnimalDetailActivity  : BaseActivity<AnimalDetailViewmodel,ActivityAnimalDetailsBinding>() {

    override val mViewModel: AnimalDetailViewmodel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(mViewBinding.root)

        mViewModel.animalData = intent.getParcelableExtra(EXTRA_DATA)
    }


    override fun getViewBinding(): ActivityAnimalDetailsBinding =
        ActivityAnimalDetailsBinding.inflate(layoutInflater)

    companion object {
        private const val EXTRA_DATA = "extra_data"

        fun getStartIntent(
            context: Context,
            animalsRespItem: AnimalsRespItem
        ) = Intent(context, AnimalDetailActivity::class.java).apply { putExtra(EXTRA_DATA, animalsRespItem) }
    }

    override fun onStart() {
        super.onStart()
        setAnimalData()
    }

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