package com.example.presentation.controller

import com.example.infrastructure.exposed.repository.ExposedUserRepository
import com.example.presentation.form.GetItemsResponse
import com.example.presentation.form.GetUsersResponse
import com.example.presentation.form.ItemInfo
import com.example.presentation.form.UserInfo
import com.example.usecase.ItemUsecase
import com.example.usecase.UserUsecase

class UserController(private val userUsecase: UserUsecase) {
    fun getUsers(): GetUsersResponse {
        val users = userUsecase.getAllUsers()

        return GetUsersResponse(users.map { UserInfo(it.id, it.name) })
    }
}