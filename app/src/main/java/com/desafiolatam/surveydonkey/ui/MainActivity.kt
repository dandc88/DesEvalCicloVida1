package com.desafiolatam.surveydonkey.ui

import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment

import com.desafiolatam.surveydonkey.R
import com.desafiolatam.surveydonkey.databinding.ActivityMainBinding

import com.desafiolatam.surveydonkey.ui.fragment.EndFragment
import com.desafiolatam.surveydonkey.ui.fragment.FirstQuestionFragment
import com.desafiolatam.surveydonkey.ui.fragment.FourthQuestionFragment
import com.desafiolatam.surveydonkey.ui.fragment.SecondQuestionFragment
import com.desafiolatam.surveydonkey.ui.fragment.StartFragment
import com.desafiolatam.surveydonkey.ui.fragment.ThirdQuestionFragment
import com.desafiolatam.surveydonkey.viewmodel.MainViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, StartFragment()) // Fragmento inicial
                .commitNow()
        }

        binding.fab.setOnClickListener {
            navigateToNextFragment()
        }
    }

    private fun navigateToNextFragment() {
        when (val currentItem = supportFragmentManager.findFragmentById(R.id.fragment_container)) {
            is StartFragment -> showFragment(FirstQuestionFragment())
            is FirstQuestionFragment -> showFragment(SecondQuestionFragment())
            is SecondQuestionFragment -> showFragment(ThirdQuestionFragment())
            is ThirdQuestionFragment -> showFragment(FourthQuestionFragment())
            is FourthQuestionFragment ->  showFragment(EndFragment())
            else -> Log.d("MainActivity", "Fragmento actual no reconocido: $currentItem")
        }
    }

    private fun showFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, fragment)
            .addToBackStack(null)
            .commit()


    }






}