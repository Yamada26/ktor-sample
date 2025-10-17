package com.example.domain.model

import com.example.shared.error.AppException

data class User(
    val id: Int,
    val name: String
) {
    init {
        if (name.isEmpty()) {
            throw AppException.Invalid("User name must not be empty")
        }
    }
}


