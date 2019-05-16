package com.example.lerna.gestarot.bdd.model

import io.realm.RealmObject

open class User : RealmObject()
{
    var pseudo:String = ""
    var email:String  = ""
    var idUser:String = ""
    var img: Int = 0
}