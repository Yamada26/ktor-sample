package com.example.presentation.controller

import com.example.presentation.form.GetItemsResponse
import com.example.presentation.form.ItemInfo
import com.example.shared.logging.logger
import com.example.usecase.ItemUsecase

class ItemController(
        private val itemUsecase: ItemUsecase,
) {
    private val logger = logger<ItemController>()

    fun getItems(): GetItemsResponse {
        val items = itemUsecase.getAllItems()

        logger.debug { "items: $items" }

        return GetItemsResponse(items.map { ItemInfo(it.id, it.name) })
    }
}
