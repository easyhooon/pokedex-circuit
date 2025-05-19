package com.easyhooon.pokedex.core.common

sealed class InsertFavoriteResult {
    data object Success : InsertFavoriteResult()
    data object Duplicate : InsertFavoriteResult()
    data object LimitExceeded : InsertFavoriteResult()
}
