package com.example.shared.logging

import io.github.oshai.kotlinlogging.KotlinLogging

/**
 * Kotlin Logging を利用した共通ロガープロバイダ。
 * 各クラスから logger<T>() で型安全に取得できる。
 */
inline fun <reified T> logger() = KotlinLogging.logger(T::class.qualifiedName ?: "Anonymous")
