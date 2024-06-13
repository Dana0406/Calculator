package com.example.calculator.presentation.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import com.example.calculator.databinding.ActivityMainBinding
import com.example.calculator.presentation.adapter.ButtonItemAdapter
import com.example.calculator.presentation.viewModels.CalculatorViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var buttonItemAdapter: ButtonItemAdapter

    private val viewModel: CalculatorViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        buttonItemAdapter = ButtonItemAdapter()

        prepareButtonRecyclerView()
        observeViewModel()
        buttonClicked()
    }

    private fun prepareButtonRecyclerView() {
        val layoutManager = GridLayoutManager(this, 4)
        layoutManager.spanSizeLookup = object : GridLayoutManager.SpanSizeLookup() {
            override fun getSpanSize(position: Int): Int {
                return if (buttonItemAdapter.getItemViewType(position) == 2) 2 else 1
            }
        }
        binding.buttonRecyclerView.layoutManager = layoutManager
        binding.buttonRecyclerView.adapter = buttonItemAdapter
    }

    private fun observeViewModel() {
        viewModel.solutionText.observe(this, Observer {
            binding.solutionTextView.text = it
        })
        viewModel.resultText.observe(this, Observer {
            binding.resultTextView.text = it
        })
    }

    private fun buttonClicked() {
        buttonItemAdapter.onItemClick = { button ->
            viewModel.onButtonClick(button)
        }
    }
}
