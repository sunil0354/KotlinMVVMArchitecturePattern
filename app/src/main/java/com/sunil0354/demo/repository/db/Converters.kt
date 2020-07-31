package com.sunil0354.demo.repository.db

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.sunil0354.demo.repository.model.*

class Converters {
    val gson = Gson()

    @TypeConverter
    fun fromHighlightResultString(jsonData: String?): HighlightResult? {
        return if (jsonData == null) null else gson.fromJson(jsonData, object : TypeToken<HighlightResult?>() {}.type)
    }

    @TypeConverter
    fun fromHighlightResultArrayList(list: HighlightResult?): String? {
        return if(list == null) null else gson.toJson(list)
    }

    @TypeConverter
    fun fromIncludedTestsString(jsonData: String?): IncludedTests? {
        return if (jsonData == null) null else gson.fromJson(jsonData, object : TypeToken<IncludedTests?>() {}.type)
    }

    @TypeConverter
    fun fromIncludedTestsArrayList(list: IncludedTests?): String? {
        return if(list == null) null else gson.toJson(list)
    }

    @TypeConverter
    fun fromKeywordString(jsonData: String?): Keyword? {
        return if (jsonData == null) null else gson.fromJson(jsonData, object : TypeToken<Keyword?>() {}.type)
    }

    @TypeConverter
    fun fromKeywordArrayList(list: Keyword?): String? {
        return if(list == null) null else gson.toJson(list)
    }

    @TypeConverter
    fun fromCategoryString(jsonData: String?): Category? {
        return if (jsonData == null) null else gson.fromJson(jsonData, object : TypeToken<Category?>() {}.type)
    }

    @TypeConverter
    fun fromCategoryArrayList(list: Category?): String? {
        return if(list == null) null else gson.toJson(list)
    }

    @TypeConverter
    fun fromItemNameString(jsonData: String?): ItemName? {
        return if (jsonData == null) null else gson.fromJson(jsonData, object : TypeToken<ItemName?>() {}.type)
    }

    @TypeConverter
    fun fromItemNameArrayList(list: ItemName?): String? {
        return if(list == null) null else gson.toJson(list)
    }

    @TypeConverter
    fun fromAnyArrayList(list: List<Any>?): String? {
        return if(list == null) null else gson.toJson(list)
    }

    @TypeConverter
    fun fromAnyString(jsonData: String?): List<Any>? {
        return if (jsonData == null) null else gson.fromJson(jsonData, object : TypeToken<List<Any>?>() {}.type)
    }
}
