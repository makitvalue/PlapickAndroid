package com.example.plapick.Models

class LocationModel(val id: Int, val name: String, val type: Int, var status: Int ) {
    companion object {
        const val PARENT = 0
        const val CHILD = 1
        const val SELECTED = 1
        const val NO_SELECTED = 0
    }
}