package com.hebreuyannis.foodishapp.app.ui

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.bumptech.glide.Glide
import com.hebreuyannis.foodishapp.R
import com.hebreuyannis.foodishapp.app.MainApplication
import com.hebreuyannis.foodishapp.app.https.HttpsRequester
import com.hebreuyannis.foodishapp.app.ui.FoodishViewModel.Command
import kotlinx.android.synthetic.main.activity_foodish.*
import timber.log.Timber
import javax.inject.Inject

class FoodishActivity : AppCompatActivity() {

    @Inject
    lateinit var implement: HttpsRequester

    @Inject
    lateinit var viewmodel: FoodishViewModel

    override fun onCreate(savedInstanceState: Bundle?) {

        (application as MainApplication).appComponent.favoriteComponent().create().inject(this)

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_foodish)

        initView()
        viewmodel.command.observe(this, Observer {
            processCommand(it)
        })
    }

    private fun initView() {

        resfreshButton.setOnClickListener {
            viewmodel.refreshFoodish()
        }

        favButton.setOnClickListener {
            viewmodel.favoriteFoodish(viewmodel.current)
        }
    }

    private fun processCommand(command: Command) {
        when (command) {
            is Command.LoadFoodish -> {
                favButton.setImageDrawable(getDrawable(android.R.drawable.star_off))
                Glide.with(this)
                    .load(command.url)
                    .into(displayImage)
            }
            is Command.FavoriteFoodish -> {
                favButton.setImageDrawable(getDrawable(android.R.drawable.star_on))
            }
            Command.ErrorLoading -> Timber.d("error loading")
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.main_foodish_menu, menu)
        return true
    }
}