package com.example.lerna.gestarot.bdd.model

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey

open class User : RealmObject()
{

    var pseudo:String = ""
    var email:String  = ""

    @PrimaryKey
    var idUser:String = ""
    var img: Int = 0
    var photo: String? = null
}