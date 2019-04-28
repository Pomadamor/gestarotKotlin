package com.example.lerna.gestarot

import android.os.Parcel
import android.os.Parcelable
import io.realm.RealmObject

open class User : RealmObject()
{
    var pseudo:String = ""
    var email:String  = ""
    var idUser:String = ""
}