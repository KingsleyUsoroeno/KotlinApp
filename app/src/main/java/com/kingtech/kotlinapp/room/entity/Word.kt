package com.kingtech.kotlinapp.room.entity

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity
class Word(
    @PrimaryKey(autoGenerate = true)
    var id: Int,

    @ColumnInfo(name = "word")
    var word: String,

    var description: String
)

