package com.example.lerna.gestarot.bdd.model

import io.realm.RealmObject

open class Score : RealmObject() {

    var gameId: Long? = null
    var idUser: String? = null
    var score: Int? = null

}
