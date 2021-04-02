package com.example.geeksearch

class Organisation {

    lateinit var orgName: String
    lateinit var orgEmail: String
    lateinit var orgLocation: String
    lateinit var orgPassword: String


    constructor()
    constructor(
        orgName: String,
        orgEmail: String,
        orgLocation: String,
        orgPassword: String
    ) : this() {
        this.orgName = orgName
        this.orgEmail = orgEmail
        this.orgLocation = orgLocation
        this.orgPassword = orgPassword
    }
}