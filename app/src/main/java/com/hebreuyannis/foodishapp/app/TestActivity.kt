package com.hebreuyannis.foodishapp.app

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.hebreuyannis.foodishapp.R
import com.hebreuyannis.foodishapp.app.favorite.FavoriteFoodishRepository
import kotlinx.android.synthetic.main.activity_test.*
import javax.inject.Inject

class TestActivity : AppCompatActivity() {


    @Inject
    lateinit var repository: FavoriteFoodishRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        (application as MainApplication).appComponent.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test)

        repository.add("https://foodish-api.herokuapp.com/images/burger/burger58.jpg")
        //Timber.d("viewmodel ${viewModel.getList().size}")
        setTextToLabel("lenght : " + repository.getList().size.toString())
    }

    private fun setTextToLabel(msg: String) {
        msgtest.text = msg
    }
}