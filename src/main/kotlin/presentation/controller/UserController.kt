package com.example.presentation.controller

import com.example.infrastructure.exposed.repository.ExposedUserRepository
import com.example.presentation.form.GetItemsResponse
import com.example.presentation.form.GetUsersResponse
import com.example.presentation.form.ItemInfo
import com.example.presentation.form.UserInfo
import com.example.usecase.ItemUsecase

class UserController() {
    fun getUsers(): GetUsersResponse {
        val userRepository = ExposedUserRepository()

        // 動作確認のためリポジトリから直接取得する
        val users = userRepository.findAll()
        return GetUsersResponse(users.map { UserInfo(it.id, it.name) })
    }
}