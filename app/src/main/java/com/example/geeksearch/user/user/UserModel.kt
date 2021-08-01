package com.example.geeksearch.user.user

class UserModel {

    lateinit var userEmail: String
    lateinit var userName: String
    lateinit var userPhone: String
    lateinit var userLocation: String
    lateinit var userCollege: String
    lateinit var userDegree: String


    constructor()
    constructor(
        userEmail: String,
        userName: String,
        userPhone: String,
        userLocation: String,
        userCollege: String,
        userDegree: String
    ) : this() {

        this.userEmail = userEmail
        this.userName = userName
        this.userPhone = userPhone
        this.userLocation = userLocation
        this.userCollege = userCollege
        this.userDegree = userDegree

    }

}