package com.example.domain.model

import com.example.shared.error.AppException

data class Item(
    val id: Int,
    val name: String
) {
    init {
        if (name.isEmpty()) {
            throw AppException.Invalid("Item name must not be empty")
        }
    }
}
