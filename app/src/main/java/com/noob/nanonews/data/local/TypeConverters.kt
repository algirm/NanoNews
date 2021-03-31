package com.noob.nanonews.data.local

import androidx.room.TypeConverter
import com.noob.nanonews.data.model.Source

class TypeConverters {

    @TypeConverter
    fun fromSource(source: Source): String = source.name

    @TypeConverter
    fun toSource(name: String): Source = Source(name, name)
}