package com.kingtech.kotlinapp.room.database

import android.arch.lifecycle.LiveData
import android.support.annotation.WorkerThread
import com.kingtech.kotlinapp.room.dao.WordDao
import com.kingtech.kotlinapp.room.entity.Word

class WordRepo(private var wordDao: WordDao) {

    // performing a task on a Background Thread
    @WorkerThread
    fun insertWord(word: Word) {
        wordDao.insertWord(word)
    }

    // Get all words by a Particular Name
    fun getAllWordsByName(name: String): LiveData<List<Word>> {
        return wordDao.getAllWordsByName(name)
    }

    // Get all words
    var allword: LiveData<List<Word>> = wordDao.getAllWords()
}