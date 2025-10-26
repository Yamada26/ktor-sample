package com.example.domain.model

import com.example.shared.error.AppException
import com.example.shared.logging.logger

class Item(
    val id: ItemId,
    val name: String,
) {
    private val logger = logger<Item>()

    init {
        if (name.isEmpty()) {
            logger.error { "Invalid item name: '$name'" }
            throw AppException.Invalid("Item name must not be empty")
        }
    }
}
