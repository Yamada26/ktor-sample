package com.example.domain.model

import com.example.shared.error.AppException

@JvmInline
value class ItemId(
        val value: Int,
) {
    init {
        if (value < 1) {
            throw AppException.Invalid("Item id must be positive")
        }
    }
}
