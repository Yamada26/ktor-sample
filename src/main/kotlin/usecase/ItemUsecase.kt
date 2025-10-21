package com.example.usecase

import com.example.domain.repository.IItemRepository
import com.example.shared.logging.logger
import com.example.usecase.shared.ITransactionManager

data class ItemDTO(
    val id: Int,
    val name: String,
)

class ItemUsecase(
    private val itemRepository: IItemRepository,
    private val txManager: ITransactionManager,
) {
    private val logger = logger<ItemUsecase>()

    fun getAllItems(): List<ItemDTO> {
        val items =
            txManager.runInTransaction {
                logger.info { "Hello, I am in a transaction." }
                return@runInTransaction itemRepository.findAll()
            }

        logger.debug { "items: $items" }

        return items.map { ItemDTO(it.id.value, it.name) }
    }
}
