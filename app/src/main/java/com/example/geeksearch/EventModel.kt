package com.example.geeksearch

class EventModel {
    lateinit var hckName: String
    lateinit var hckDes: String
    lateinit var eligibity: String
    lateinit var regOpenDate: String
    lateinit var regCloseDate: String
    lateinit var hckStart: String
    lateinit var hckEnd: String
    lateinit var prizes: String
    lateinit var domain: String
    lateinit var link: String

    constructor()

    constructor(
        hckName: String,
        hckDes: String,
        eligibity: String,
        regOpenDate: String,
        regCloseDate: String,
        hckStart: String,
        hckEnd: String,
        prizes: String,
        domain: String,
        link: String
    ) : this() {
        this.hckDes = hckDes
        this.hckName = hckName
        this.eligibity = eligibity
        this.regOpenDate = regOpenDate
        this.regCloseDate = regCloseDate
        this.hckStart = hckStart
        this.hckEnd = hckEnd
        this.prizes = prizes
        this.domain = domain
        this.link = link
    }

}