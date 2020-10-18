package com.hebreuyannis.foodishapp.app.favorite

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.hebreuyannis.foodishapp.R
import com.hebreuyannis.foodishapp.app.MainApplication
import kotlinx.android.synthetic.main.activity_favorite.*
import javax.inject.Inject

class FavoriteActivity : AppCompatActivity() {

    private lateinit var recyclerAdapter: FavoriteRecyclerAdapter
    private var favoriteComponent: FavoriteComponent? = null

    @Inject
    lateinit var favoriteViewModel: FavoriteViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        favoriteComponent =
            (application as MainApplication).appComponent.repository().favoriteComponent

        super.onCreate(savedInstanceState)
        if (favoriteComponent == null) {
            setContentView(R.layout.activiy_favorite_noitem)
        } else {
            setContentView(R.layout.activity_favorite)
            favoriteComponent!!.inject(this)

            recyclerAdapter =
                FavoriteRecyclerAdapter(
                    itemClickListener = {})
            recycler.adapter = recyclerAdapter
            recyclerAdapter.submitList(favoriteViewModel.favoriteFoodishRepository.getList())
        }
    }


}