package com.example.presentation.controller

import com.example.presentation.form.GetItemsResponse
import com.example.presentation.form.ItemInfo
import com.example.usecase.ItemUsecase

class ItemController(private val itemUsecase: ItemUsecase) {
    fun getItems(): GetItemsResponse {
        val items = itemUsecase.getAllItems()
        return GetItemsResponse(items.map { ItemInfo(it.id, it.name) })
    }
}