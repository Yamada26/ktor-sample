package com.example.presentation.form

data class GetHelloResponse(val result: String)

data class PostHelloRequest(val name: String)
data class PostHelloResponse(val result: String)