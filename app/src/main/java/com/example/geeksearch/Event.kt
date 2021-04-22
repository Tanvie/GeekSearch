package com.example.geeksearch

class Event {
    lateinit var hckname: String
    lateinit var hckDes: String
    lateinit var eligibity: String
    lateinit var regOpenDate: String
    lateinit var regCloseDate: String
    lateinit var hckStart: String
    lateinit var hckEnd: String
    lateinit var prizes: String
    lateinit var domain: String

    constructor()
    constructor(
        hckname: String,
        hckDes: String,
        eligibity: String,
        regOpenDate: String,
        regCloseDate: String,
        hckStart: String,
        hckEnd: String,
        prizes: String,
        domain: String
    ) : this() {
        this.hckname = hckname
        this.hckDes = hckDes
        this.eligibity = eligibity
        this.regOpenDate = regOpenDate
        this.regCloseDate = regCloseDate
        this.hckStart = hckStart
        this.hckEnd = hckEnd
        this.prizes = prizes
        this.domain = domain
    }

}