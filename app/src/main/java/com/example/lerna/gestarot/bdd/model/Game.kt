package com.example.lerna.gestarot.bdd.model

import io.realm.RealmList
import io.realm.RealmObject

open class Game(val date: Long = System.currentTimeMillis()) : RealmObject() {

    var listPlayers: RealmList<String>? = null

}