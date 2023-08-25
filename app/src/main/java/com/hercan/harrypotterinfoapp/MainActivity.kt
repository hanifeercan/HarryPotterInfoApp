package com.hercan.harrypotterinfoapp

import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.hercan.harrypotterinfoapp.databinding.ActivityMainBinding
import com.hercan.harrypotterinfoapp.presentation.viewbinding.viewBinding
import com.hercan.harrypotterinfoapp.viewmodel.ActivityViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val binding by viewBinding(ActivityMainBinding::inflate)
    private val viewModel: ActivityViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        lifecycleScope.launchWhenCreated {
            viewModel.isConnected.collectLatest {
                binding.apply {
                    if (!it) notConnected()
                }
            }
        }
    }

    private fun notConnected() {
        val message = getString(R.string.not_connect_message)
        Toast.makeText(applicationContext, message, Toast.LENGTH_LONG).show()
    }
}