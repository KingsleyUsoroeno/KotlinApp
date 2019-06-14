package com.kingtech.kotlinapp.room

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import com.kingtech.kotlinapp.R
import com.kingtech.kotlinapp.room.database.WordViewModel
import com.kingtech.kotlinapp.room.entity.Word
import kotlinx.android.synthetic.main.activity_word.*

class WordActivity : AppCompatActivity() {

    lateinit var wordRecyclerAdapter: WordRecyclerAdapter
    lateinit var wordViewModel: WordViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_word)

        wordViewModel = ViewModelProviders.of(this).get(WordViewModel::class.java)

        wordRecyclerView.setHasFixedSize(true)
        wordRecyclerView.layoutManager = LinearLayoutManager(this)
        wordRecyclerAdapter = WordRecyclerAdapter()
        wordRecyclerView.adapter = wordRecyclerAdapter
        wordViewModel.allWords!!.observe(this, Observer {
            if (it != null) {
                wordRecyclerAdapter.setWords(it)
            }
        })
    }
}
