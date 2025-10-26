package com.example.usecase

import com.example.domain.repository.IUserRepository
import com.example.shared.logging.logger
import com.example.usecase.shared.ITransactionManager

data class UserDTO(
        val id: Int,
        val name: String,
)

class UserUsecase(
        private val userRepository: IUserRepository,
        private val txManager: ITransactionManager,
) {
    private val logger = logger<UserUsecase>()

    fun getAllUsers(): List<UserDTO> {
        val users = txManager.runInTransaction { userRepository.findAll() }

        logger.debug { "users: $users" }

        return users.map { UserDTO(it.id.value, it.name) }
    }
}
