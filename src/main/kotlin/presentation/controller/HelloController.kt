package com.example.presentation.controller

import com.example.presentation.form.GetHelloResponse

class HelloController {
    fun getHello(): GetHelloResponse {
        return GetHelloResponse("hello!")
    }
}