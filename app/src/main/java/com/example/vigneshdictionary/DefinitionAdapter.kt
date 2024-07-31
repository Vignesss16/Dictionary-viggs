package com.example.vigneshdictionary

//new import log
import android.util.Log

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.vigneshdictionary.databinding.DefinitionRecyclerRowBinding

class DefinitionAdapter(private var definitionList: List<WordMeaning>) : RecyclerView.Adapter<DefinitionAdapter.DefinitionViewHolder>() {

    class DefinitionViewHolder(private val binding: DefinitionRecyclerRowBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(meaning: WordMeaning) {
            binding.partOfSpeechTextview.text = meaning.partOfSpeech
            binding.definitionsListTextview.text = meaning.definitionsList.joinToString("\n\n") {
                val currentIndex = meaning.definitionsList.indexOf(it)
                "${currentIndex + 1}. ${it.meaning}"
            }

            if (meaning.synonymsList.isEmpty()) {
                binding.synonymsListTitleTextview.visibility = View.GONE
                binding.synonymsListTextview.visibility = View.GONE
            } else {
                binding.synonymsListTitleTextview.visibility = View.VISIBLE
                binding.synonymsListTextview.visibility = View.VISIBLE
                binding.synonymsListTextview.text = meaning.synonymsList.joinToString(", ")
            }

            if (meaning.antonymsList.isEmpty()) {
                binding.antonymsListTitleTextview.visibility = View.GONE
                binding.antonymsListTextview.visibility = View.GONE
            } else {
                binding.antonymsListTitleTextview.visibility = View.VISIBLE
                binding.antonymsListTextview.visibility = View.VISIBLE
                binding.antonymsListTextview.text = meaning.antonymsList.joinToString(", ")
            }
        }
    }

//    fun updateData(newDefinitionList: List<WordMeaning>) {
//        definitionList = newDefinitionList
//        notifyDataSetChanged()
//    }
fun updateData(newDefinitionList: List<WordMeaning>?) {
    if (newDefinitionList != null) {
        definitionList = newDefinitionList
        notifyDataSetChanged()
    } else {
        // Handle the case where newDefinitionList is null
        Log.e("DefinitionAdapter", "updateData called with null newDefinitionList")
    }
}


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DefinitionViewHolder {
        val binding = DefinitionRecyclerRowBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return DefinitionViewHolder(binding)
    }

    override fun getItemCount(): Int = definitionList.size

    override fun onBindViewHolder(holder: DefinitionViewHolder, position: Int) {
        holder.bind(definitionList[position])
    }
}

