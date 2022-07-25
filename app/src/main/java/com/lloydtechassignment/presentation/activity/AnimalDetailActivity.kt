package com.lloydtechassignment.presentation.activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.bumptech.glide.Glide
import com.lloydtechassignment.R
import com.lloydtechassignment.data.models.AnimalUIModel
import com.lloydtechassignment.databinding.ActivityAnimalDetailsBinding
import com.lloydtechassignment.domain.model.AnimalsRespItem
import com.lloydtechassignment.presentation.base.BaseActivity
import com.lloydtechassignment.util.showToast
import com.lloydtechassignment.viewmodel.AnimalDetailViewmodel
import org.koin.androidx.viewmodel.ext.android.viewModel

/**
 * Animal Detail Activity to show Animals detail
 * */
class AnimalDetailActivity : BaseActivity<AnimalDetailViewmodel, ActivityAnimalDetailsBinding>() {

    companion object {
        private const val EXTRA_DATA = "extra_data"

        /**
         * Method is used to send Extra Data to the Activity and returning its intent
         * [context] is used to send Context of calling activity
         * [animalUIModel] is the data parameter*/
        fun getStartIntent(
            context: Context,
            animalUIModel: AnimalUIModel
        ) = Intent(context, AnimalDetailActivity::class.java).apply {
            putExtra(EXTRA_DATA, animalUIModel)
        }
    }

    override val viewmodel by viewModel<AnimalDetailViewmodel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(viewBinding.root)

        viewmodel.animalData = intent.getParcelableExtra(EXTRA_DATA)
    }


    override fun getBinding(): ActivityAnimalDetailsBinding =
        ActivityAnimalDetailsBinding.inflate(layoutInflater)

    override fun onStart() {
        super.onStart()
        setAnimalData()
    }

    /**
     * Set Animal Data is used for setting Animal Data on Animal Detail Page
     * */
    private fun setAnimalData() {
        viewmodel.animalData?.let {
            viewBinding.apply {
                content.apply {
                    animalTitle.text = it.name
                    animalGeo.text = it.habitat
                    ivMarkFav.setOnClickListener {
                        viewmodel.markFavorite()
                        showToast(getString(R.string.marked_fav))
                    }
                }
                toolbar.title = it.name
                Glide.with(this@AnimalDetailActivity)
                    .load(it.imageLink) // image url
                    .into(viewBinding.imageView)
            }

        }
    }

}