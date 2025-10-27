package com.example.domain.model

import com.example.shared.error.AppException

@JvmInline
value class TaskId(
        val value: Int,
) {
    init {
        if (value < 1) {
            throw AppException.Invalid("Task id must be positive")
        }
    }
}
