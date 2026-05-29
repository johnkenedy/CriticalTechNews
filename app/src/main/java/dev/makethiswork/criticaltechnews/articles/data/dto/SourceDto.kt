package dev.makethiswork.criticaltechnews.articles.data.dto

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class SourceDto(
    val id: String?,
    val name: String?,
)
