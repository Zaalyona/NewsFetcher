package com.example.newsfetcher.json

data class ProfileModel(
    val dob: String,
    val name: String,
    val about: String,
    val address: String,
    val company: String,
    val location: LocationModel
)
