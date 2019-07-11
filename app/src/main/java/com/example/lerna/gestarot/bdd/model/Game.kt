package com.example.lerna.gestarot.bdd.model

import io.realm.RealmList
import io.realm.RealmObject

open class Game() : RealmObject() {

    var date: Long? = System.currentTimeMillis()
    var listPlayers: RealmList<String>? = null

}