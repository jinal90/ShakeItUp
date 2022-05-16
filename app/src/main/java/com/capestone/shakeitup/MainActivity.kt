package com.capestone.shakeitup

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.viewModels
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.lifecycle.Observer
import com.capestone.shakeitup.service.Status
import com.capestone.shakeitup.viewmodel.CocktailListViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val viewModel: CocktailListViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        // Handle the splash screen transition.
        val splashScreen = installSplashScreen()
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel.getAlcoholicCocktails().observe(this, Observer {
            it?.let { resource ->
                when (resource.status) {
                    Status.SUCCESS -> {
                        resource.data?.let {
                                list -> Log.d("Test list", "Test 1234 -> first alcoholic drink id:" +
                                " ${list.cocktailList[0].idDrink}")
                        }
                    }
                    Status.ERROR -> {
                        Toast.makeText(this, it.message, Toast.LENGTH_LONG).show()
                    }
                    Status.LOADING -> {
                        Log.d("Test alcoholic", "Test 1234 -> alcoholic Loading")
                    }
                }
            }
        })

        viewModel.getNonAlcoholicCocktails().observe(this, Observer {
            it?.let { resource ->
                when (resource.status) {
                    Status.SUCCESS -> {
                        resource.data?.let {
                                list -> Log.d("Test list", "Test 1234 -> first non alcoholic drink id:" +
                                " ${list.cocktailList[0].idDrink}")
                        }
                    }
                    Status.ERROR -> {
                        Toast.makeText(this, it.message, Toast.LENGTH_LONG).show()
                    }
                    Status.LOADING -> {
                        Log.d("Test nonalcoholic", "Test 1234 -> nonalcoholic Loading")
                    }
                }
            }
        })

        viewModel.getCocktailDetails("12560").observe(this, Observer {
            it?.let { resource ->
                when (resource.status) {
                    Status.SUCCESS -> {
                        resource.data?.let {
                                list -> Log.d("Test list", "Test 1234 -> Drink id description:" +
                                    " ${list.cocktailDetailsList[0].strInstructions}")
                        }
                    }
                    Status.ERROR -> {
                        Toast.makeText(this, it.message, Toast.LENGTH_LONG).show()
                    }
                    Status.LOADING -> {
                        Log.d("Test detail", "Test 1234 -> detail Loading")
                    }
                }
            }
        })

        viewModel.getRandomCocktail().observe(this, Observer {
            it?.let { resource ->
                when (resource.status) {
                    Status.SUCCESS -> {
                        resource.data?.let {
                                list -> Log.d("Test list", "Test 1234 -> Random drink description:" +
                                " ${list.cocktailDetailsList[0].strInstructions}")
                        }
                    }
                    Status.ERROR -> {
                        Toast.makeText(this, it.message, Toast.LENGTH_LONG).show()
                    }
                    Status.LOADING -> {
                        Log.d("Test Random", "Test 1234 -> Random Loading")
                    }
                }
            }
        })
    }
}