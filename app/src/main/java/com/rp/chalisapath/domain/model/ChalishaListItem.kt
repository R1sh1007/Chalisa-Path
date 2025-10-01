package com.rp.chalisapath.domain.model

import kotlinx.serialization.Serializable

 @Serializable
 data class ChalishaListItem(
     val id: Int,
     val image: String,
     val name: String
 )