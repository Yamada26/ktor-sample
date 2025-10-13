package com.example.usecase

import com.example.domain.repository.IItemRepository

data class ItemDTO(
    val id: Int,
    val name: String
)

class ItemUsecase(private val itemRepository: IItemRepository) {
    fun getAllItems(): List<ItemDTO> {
        val items = itemRepository.findAll()
        return items.map { ItemDTO(it.id, it.name) }
    }
}