package com.example.geeksearch.user.event.event

import android.os.Parcel
import android.os.Parcelable

/**
 * Created by Tanvi Wakade on 28-04-2021.
 */
class EventModel() : Parcelable {
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

    constructor(parcel: Parcel) : this() {
        hckName = parcel.readString().toString()
        hckDes = parcel.readString().toString()
        eligibity = parcel.readString().toString()
        regOpenDate = parcel.readString().toString()
        regCloseDate = parcel.readString().toString()
        hckStart = parcel.readString().toString()
        hckEnd = parcel.readString().toString()
        prizes = parcel.readString().toString()
        domain = parcel.readString().toString()
        link = parcel.readString().toString()
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(hckName)
        parcel.writeString(hckDes)
        parcel.writeString(eligibity)
        parcel.writeString(regOpenDate)
        parcel.writeString(regCloseDate)
        parcel.writeString(hckStart)
        parcel.writeString(hckEnd)
        parcel.writeString(prizes)
        parcel.writeString(domain)
        parcel.writeString(link)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<EventModel> {
        override fun createFromParcel(parcel: Parcel): EventModel {
            return EventModel(parcel)
        }

        override fun newArray(size: Int): Array<EventModel?> {
            return arrayOfNulls(size)
        }
    }
}

