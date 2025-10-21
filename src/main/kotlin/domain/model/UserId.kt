package com.example.domain.model

import com.example.shared.error.AppException

@JvmInline
value class UserId(
    val value: Int,
) {
    init {
        if (value < 1) {
            throw AppException.Invalid("User id must be positive")
        }
    }
}
