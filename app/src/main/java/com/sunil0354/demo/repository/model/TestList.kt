package com.sunil0354.demo.repository.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

class TestList : ArrayList<TestListItem>()

@Entity(tableName = "testlistitem_cart")
class TestListItem(
    @PrimaryKey val itemId: String,
    @SerializedName("Best-sellers")  val Best_sellers: String? = null,
    @SerializedName("Tests") val tests: String? = null,
    val Keyword: String? = null,
    @SerializedName("S.no") val S_no: Int=0,
    val _highlightResult: HighlightResult? = null,
    val availableAt: Int=0,
    val category: String? = null,
    val fasting: Int=0,
    val itemName: String? = null,
    val labName: String? = null,
    val minPrice: Int=0,
    val objectID: String? = null,
    val popular: String? = null,
    val testCount: Int=0,
    val type: String? = null,
    val url: String? = null
)

data class HighlightResult(
    val Tests: IncludedTests,
    val Keyword: Keyword,
    val category: Category,
    val itemName: ItemName
)

data class IncludedTests(
    val matchLevel: String,
    val matchedWords: List<Any>,
    val value: String
)

data class ItemName(
    val matchLevel: String,
    val matchedWords: List<Any>,
    val value: String
)

data class Category(
    val matchLevel: String,
    val matchedWords: List<Any>,
    val value: String
)

data class Keyword(
    val matchLevel: String,
    val matchedWords: List<Any>,
    val value: String
)