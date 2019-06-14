package com.kingtech.kotlinapp.room.database

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.LiveData
import com.kingtech.kotlinapp.room.entity.Word

class WordViewModel(application: Application) : AndroidViewModel(application) {
    private var wordRepo: WordRepo? = null
    var allWords: LiveData<List<Word>>? = null

    // called as Soon as these Class is instantiated
    init {
        val wordDao = WordDatabase.getDatabase(application)!!.wordDao()
        wordRepo = WordRepo(wordDao)
        allWords = wordRepo!!.allword
    }

    fun insertWord(word: Word) {
        wordRepo!!.insertWord(word)
    }
}