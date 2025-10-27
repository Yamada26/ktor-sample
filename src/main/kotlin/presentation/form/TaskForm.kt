package com.example.presentation.form

data class GetTaskRequest(
        val id: Int,
)

data class GetTaskResponse(
        val id: Int,
        val name: String,
        val status: String,
)

data class CreateTaskRequest(
        val name: String,
)

data class CreateTaskResponse(
        val id: Int,
        val name: String,
        val status: String,
)
