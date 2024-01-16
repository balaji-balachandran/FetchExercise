package com.example.fetchexercise.Model

/**
 * Entry object
 * @property id ID of the Entry
 * @property listId ListID of the Entry
 * @property name Name of the Entry
 */
data class Entry(
    val id: Int,
    val listId: Int,
    val name : String?
)

