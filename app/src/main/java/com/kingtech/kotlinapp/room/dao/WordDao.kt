package com.kingtech.kotlinapp.room.dao

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.Query
import com.kingtech.kotlinapp.room.entity.Word

@Dao
interface WordDao {

    @Insert
    fun insertWord(word: Word)

    @Query("SELECT * FROM word ORDER BY id ASC")
    fun getAllWords(): LiveData<List<Word>>

    @Query("DELETE FROM word")
    fun deleteAllWord()

    @Query("SELECT * FROM word WHERE word =:name")
    fun getAllWordsByName(name: String): LiveData<List<Word>>


}