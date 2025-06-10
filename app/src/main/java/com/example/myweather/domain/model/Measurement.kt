package com.example.myweather.domain.model

data class Measurement(
    val value: Double,
    val unit: String
) {
    override fun toString(): String {
        return "$value$unit"
    }

    constructor() :this(
        value = 0.0,
        unit = ""
    )
}