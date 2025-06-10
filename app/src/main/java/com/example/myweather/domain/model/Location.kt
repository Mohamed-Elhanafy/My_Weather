package com.example.myweather.domain.model

data class Location(val latitude: Double, val longitude: Double, val name: String){
    constructor() : this(0.0, 0.0, "")
}