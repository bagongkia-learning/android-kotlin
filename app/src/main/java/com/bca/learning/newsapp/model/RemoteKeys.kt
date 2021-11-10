package com.bca.learning.newsapp.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "remote_keys")
class RemoteKeys(
    @PrimaryKey val articleId: Long,
    val prevKey: Int?,
    val nextKey: Int?
)