package com.example.presentation.form

data class UserInfo(
        val id: Int,
        val name: String,
)

data class GetUsersResponse(
        val users: List<UserInfo>,
)
