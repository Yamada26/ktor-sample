package com.example.domain.model

import com.example.shared.error.AppException
import com.example.shared.logging.logger

data class User(
    val id: UserId,
    val name: String,
) {
    private val logger = logger<User>()

    init {
        if (name.isEmpty()) {
            logger.error { "Invalid username: '$name'" }
            throw AppException.Invalid("User name must not be empty")
        }
    }
}
