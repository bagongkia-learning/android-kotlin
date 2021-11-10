package com.bca.learning.newsapp.db

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [],
    version = 1,
    exportSchema = false
)
abstract class ArticleDatabase : RoomDatabase() {
//gzeinnumer
    abstract fun articlesDao(): ArticleDao
    abstract fun remoteKeysDao(): RemoteKeysDao

}