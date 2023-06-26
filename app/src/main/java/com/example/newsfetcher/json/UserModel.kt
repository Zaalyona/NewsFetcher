package com.example.newsfetcher.json


data class UserModel(
    val id: String,
    val email: String,
    val roles: List<String>,
    val apiKey: String,
    val profile: ProfileModel,
    val username: String,
    val createdAt: String,
    val updatedAt: String
)
