package com.example.usecase

import com.example.domain.model.Item
import com.example.domain.repository.IItemRepository
import com.example.shared.logging.logger
import com.example.usecase.shared.ITransactionManager

data class ItemDTO(
        val id: Int,
        val name: String,
)

data class CreateItemDTO(
        val id: Int,
        val name: String,
)

data class CreateItemCommand(
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
                    return@runInTransaction itemRepository.findAll()
                }

        logger.debug { "items: $items" }

        return items.map { ItemDTO(it.id!!.value, it.name) }
    }

    fun createItem(command: CreateItemCommand): CreateItemDTO {
        val newItem =
                txManager.runInTransaction {
                    val item = itemRepository.save(Item(null, command.name))
                    logger.info { "Created item: $item" }
                    return@runInTransaction item
                }

        return CreateItemDTO(id = newItem.id!!.value, name = newItem.name)
    }
}
