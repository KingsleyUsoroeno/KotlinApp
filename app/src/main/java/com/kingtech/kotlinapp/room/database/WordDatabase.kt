package com.kingtech.kotlinapp.room.database

import android.arch.persistence.db.SupportSQLiteDatabase
import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.content.Context
import com.kingtech.kotlinapp.room.dao.WordDao
import com.kingtech.kotlinapp.room.entity.Word

@Database(entities = [Word::class], version = 1)
abstract class WordDatabase : RoomDatabase() {
    abstract fun wordDao(): WordDao

    companion object {
        @Volatile
        private var INSTANCE: WordDatabase? = null

        private var databaseName: String = "wordDatabase"

        fun getDatabase(context: Context): WordDatabase? {
            if (INSTANCE == null) {
                synchronized(this) {
                    INSTANCE = Room.databaseBuilder(
                        context.applicationContext,
                        WordDatabase::class.java,
                        databaseName
                    ).fallbackToDestructiveMigrationFrom()
                        .addCallback(WordDatabaseCallback(INSTANCE!!))
                        .build()
                }

            }
            return INSTANCE
        }
    }

    private class WordDatabaseCallback(val database: WordDatabase) : RoomDatabase.Callback() {
        override fun onCreate(db: SupportSQLiteDatabase) {
            super.onCreate(db)
            populateDb(database.wordDao())

        }

        fun populateDb(wordDao: WordDao) {
            wordDao.insertWord(Word(1, "Android Developer", "Am an Android Developer"))
//            wordDao.insertWord(Word(2, "Scamper", "Am a Scamper"))
//            wordDao.insertWord(Word(3, "Dwayne Johnson", "Am a Wrestler"))
        }
    }
}