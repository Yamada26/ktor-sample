package com.example.domain.repository

import com.example.domain.model.Item

interface IItemRepository {
    fun findAll(): List<Item>
}
