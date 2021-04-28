package com.example.geeksearch.event.event

import android.os.Parcel
import android.os.Parcelable

/**
 * Created by Tanvi Wakade on 28-04-2021.
 */
class EventModel() : Parcelable {
    lateinit var name: String
    lateinit var city: String
    lateinit var state: String

    constructor(parcel: Parcel) : this() {
        name = parcel.readString().toString()
        city = parcel.readString().toString()
        state = parcel.readString().toString()
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(name)
        parcel.writeString(city)
        parcel.writeString(state)
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