package com.example.lerna.gestarot.bdd.model

import io.realm.RealmList
import io.realm.RealmObject

open class Turn : RealmObject() {

    var numeroDeTour: Int? = null
    var typeDePrise: Int? = null
    var listScore: RealmList<Score>? = null
    var gameId: Long? = null
    var userPreneur: String? = null

}

enum class TypeDePrise(val value:Int, val diminutif:String) {
    PETITE(25, "Pet"),
    GARDE(50, "Gar"),
    GARDESANG(100, "GCe"),
    GARDECONTRE(200, "GCo"),
    GRANDCHELEM(300, "Gch")
}
