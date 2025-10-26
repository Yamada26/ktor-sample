package com.example.shared.error

sealed class AppException(
        message: String,
) : Exception(message) {
    class Invalid(
            message: String,
    ) : AppException(message)

    class Unauthorized(
            message: String,
    ) : AppException(message)

    class Forbidden(
            message: String,
    ) : AppException(message)

    class NotFound(
            message: String,
    ) : AppException(message)

    class Conflict(
            message: String,
    ) : AppException(message)

    class Internal(
            message: String,
    ) : AppException(message)
}
