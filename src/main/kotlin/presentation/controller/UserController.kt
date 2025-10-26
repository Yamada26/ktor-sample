package com.example.presentation.controller

import com.example.presentation.form.GetUsersResponse
import com.example.presentation.form.UserInfo
import com.example.shared.logging.logger
import com.example.usecase.UserUsecase

class UserController(
        private val userUsecase: UserUsecase,
) {
    private val logger = logger<UserController>()

    fun getUsers(): GetUsersResponse {
        val users = userUsecase.getAllUsers()

        logger.debug { "users: $users" }

        return GetUsersResponse(users.map { UserInfo(it.id, it.name) })
    }
}
