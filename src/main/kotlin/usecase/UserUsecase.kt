package com.example.usecase

import com.example.domain.repository.IUserRepository

data class UserDTO(
    val id: Int,
    val name: String
)

class UserUsecase(private val userRepository: IUserRepository) {
    fun getAllUsers(): List<UserDTO> {
        val users = userRepository.findAll()
        return users.map { UserDTO(it.id, it.name) }
    }
}