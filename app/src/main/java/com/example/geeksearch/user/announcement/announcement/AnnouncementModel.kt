package com.example.geeksearch.user.announcement.announcement

import android.os.Parcel
import android.os.Parcelable



class AnnouncementModel() : Parcelable {

    lateinit var hackDomain: String
    lateinit var hackEnd: String
    lateinit var hackName: String
    lateinit var hackStart: String
    lateinit var message: String
    lateinit var requirements: String
    lateinit var userEmail: String
    lateinit var userName: String

    constructor(parcel: Parcel) : this() {
        hackDomain = parcel.readString().toString()
        hackEnd = parcel.readString().toString()
        hackName = parcel.readString().toString()
        hackStart = parcel.readString().toString()
        message = parcel.readString().toString()
        requirements = parcel.readString().toString()
        userEmail = parcel.readString().toString()
        userName = parcel.readString().toString()
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(hackDomain)
        parcel.writeString(hackEnd)
        parcel.writeString(hackName)
        parcel.writeString(hackStart)
        parcel.writeString(message)
        parcel.writeString(requirements)
        parcel.writeString(userEmail)
        parcel.writeString(userName)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<AnnouncementModel> {
        override fun createFromParcel(parcel: Parcel): AnnouncementModel {
            return AnnouncementModel(parcel)
        }

        override fun newArray(size: Int): Array<AnnouncementModel?> {
            return arrayOfNulls(size)
        }
    }


}