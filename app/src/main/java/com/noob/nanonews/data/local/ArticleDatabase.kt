package com.noob.nanonews.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.noob.nanonews.data.model.Article

@Database(
    entities = [Article::class],
    version = 1
)
@TypeConverters(com.noob.nanonews.data.local.TypeConverters::class)
abstract class ArticleDatabase : RoomDatabase() {

    abstract fun getArticleDao(): ArticleDao
}