package com.example.vigneshdictionary

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.vigneshdictionary.databinding.ActivityMainBinding
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

     lateinit var binding: ActivityMainBinding
     lateinit var adapter: DefinitionAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Setting up search button click listener
        binding.actionBtn.setOnClickListener {
            val word = binding.inputField.text.toString()
//            if (word.isNotEmpty()) {
//                fetchDefinition(word)
//            } else {
//                Toast.makeText(this, "Please enter a word", Toast.LENGTH_SHORT).show()
//            }
           fetchDefinition(word)

        }

        // Setting up RecyclerView
        adapter = DefinitionAdapter(emptyList())
        binding.definitionRecyclerView.layoutManager = LinearLayoutManager(this)
        binding.definitionRecyclerView.adapter = adapter
    }

    private fun fetchDefinition(word: String) {
        setLoading(true)
        GlobalScope.launch {
            try {
                val response = RetrofitClient.apiService.fetchDefinition(word)
                if (response.body() == null) {
                    throw Exception("No response body")
                }
                runOnUiThread {
                    setLoading(false)
                    response.body()?.first()?.let {
                        updateUI(it)
                    }
                }
            } catch (e: Exception) {
                runOnUiThread {
                    setLoading(false)
                    Toast.makeText(applicationContext, "Error: ${e.message}", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    private fun updateUI(wordDefinition: WordDefinition) {
        binding.wordDisplay.text = wordDefinition.term
        binding.phoneticDisplay.text = wordDefinition.phoneticSpelling
        adapter.updateData(wordDefinition.meaningsList)
    }

    private fun setLoading(isLoading: Boolean) {
        if (isLoading) {
            binding.actionBtn.visibility = View.INVISIBLE
            binding.loadingSpinner.visibility = View.VISIBLE
        } else {
            binding.actionBtn.visibility = View.VISIBLE
            binding.loadingSpinner.visibility = View.INVISIBLE
        }
    }
}

