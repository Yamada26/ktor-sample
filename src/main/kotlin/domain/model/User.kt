package com.example.domain.model

data class User(
    val id: Int,
    val name: String
) {
    init {
        require(name.isNotEmpty()) { "User name must not be empty" }
    }
}


