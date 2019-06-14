package com.kingtech.kotlinapp.room

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.kingtech.kotlinapp.R
import com.kingtech.kotlinapp.room.entity.Word
import kotlinx.android.synthetic.main.recycler_item.view.*

class WordRecyclerAdapter : RecyclerView.Adapter<WordRecyclerAdapter.WordViewHolder>() {

    private var wordList = emptyList<Word>() // Cached copy of words

    override fun onCreateViewHolder(parent: ViewGroup, position: Int): WordViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.recycler_item, parent, false)
        return WordViewHolder(view)

    }

    override fun getItemCount(): Int {
        return wordList.size
    }

    override fun onBindViewHolder(wordViewHolder: WordViewHolder, position: Int) {
        val word = wordList[position]
        wordViewHolder.name.text = word.word
        wordViewHolder.des.text = word.description
    }

    internal fun setWords(wordList: List<Word>) {
        this.wordList = wordList
        notifyDataSetChanged()
    }

    class WordViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val name: TextView = itemView.findViewById(R.id.tv_word)
        val des: TextView = itemView.findViewById(R.id.tv_description)
    }

}