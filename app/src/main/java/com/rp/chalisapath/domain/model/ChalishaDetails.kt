package com.rp.chalisapath.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class ChalishaDetails(
    val explanation: String,
    val hindi: String,
    val translation: String,
    val transliteration: String,
    val verse: Int
)