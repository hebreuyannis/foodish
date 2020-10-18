package com.hebreuyannis.foodishapp.app.foodish

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import com.bumptech.glide.Glide
import com.hebreuyannis.foodishapp.R
import com.hebreuyannis.foodishapp.app.MainApplication
import com.hebreuyannis.foodishapp.app.favorite.FavoriteActivity
import com.hebreuyannis.foodishapp.app.foodish.FoodishViewModel.Command
import com.hebreuyannis.foodishapp.app.https.HttpsRequester
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
        viewmodel.refreshFoodish()
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
                favButton.setImageDrawable(
                    ContextCompat.getDrawable(
                        this,
                        android.R.drawable.star_off
                    )
                )
                Glide.with(this)
                    .load(command.url)
                    .into(displayImage)
            }
            is Command.FavoriteFoodish -> {
                favButton.setImageDrawable(
                    ContextCompat.getDrawable(
                        this,
                        android.R.drawable.star_on
                    )
                )
            }
            Command.ErrorLoading -> Timber.d("error loading")
            Command.DeleteFavoriteFoodish -> {
                favButton.setImageDrawable(
                    ContextCompat.getDrawable(
                        this,
                        android.R.drawable.star_off
                    )
                )
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.main_foodish_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.fav -> startActivity(Intent(this, FavoriteActivity::class.java))
        }
        return super.onOptionsItemSelected(item)
    }
}

//TODO: faire gestion d'erreur exception + snackbar