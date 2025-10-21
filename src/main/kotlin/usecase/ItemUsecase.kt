package com.example.usecase

import com.example.domain.repository.IItemRepository
import com.example.shared.logging.logger

data class ItemDTO(
    val id: Int,
    val name: String,
)

class ItemUsecase(
    private val itemRepository: IItemRepository,
) {
    private val logger = logger<ItemUsecase>()

    fun getAllItems(): List<ItemDTO> {
        val items = itemRepository.findAll()

        logger.debug { "items: $items" }

        return items.map { ItemDTO(it.id.value, it.name) }
    }
}
