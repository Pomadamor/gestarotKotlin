package com.example.lerna.gestarot.manager

import com.example.lerna.gestarot.bdd.model.Game
import com.example.lerna.gestarot.bdd.model.Score
import com.example.lerna.gestarot.bdd.model.Turn
import com.example.lerna.gestarot.bdd.model.TypeDePrise
import com.vicpin.krealmextensions.queryAll
import com.vicpin.krealmextensions.save
import io.realm.RealmList

object GameManager {

    var currentGame : Game? = null
    private var lastTurn : Turn? = null
    private var currentTurn : Turn? = null

    //
    fun startGame(listOfPlayers: List<String>) {
        Game().apply {
            this.listPlayers = RealmList(*listOfPlayers.toTypedArray())
        }.also {
            currentGame = it
            it.save()
        }
    }

    fun startTurn(typeDePrise: Int, userPreneur: String) {
        if (currentTurn != null)
            lastTurn = currentTurn
        currentTurn = Turn().apply {
            this.typeDePrise = typeDePrise
            this.gameId = currentGame?.date
            this.numeroDeTour = (lastTurn?.numeroDeTour ?: -1) + 1
            this.userPreneur = userPreneur
        }
        currentTurn?.save()
    }

    fun addScore(listWinners: List<String>, listLosers: List<String>, pointsToAdd: Int) {
        val multiplier = if(currentGame?.listPlayers?.size == 4) 3 else 2        
        currentTurn?.apply {
            listScore.apply {
                val scoreList = mutableListOf<Score>()
                listLosers.forEach { losersId ->
                    scoreList.add(
                        Score().apply {
                            this.idUser = losersId
                            this.gameId = currentGame?.date
                            this.score = -(if(losersId == currentTurn?.userPreneur) (pointsToAdd * multiplier) else pointsToAdd)
                        }.also {
                            it.save()
                        }
                    )
                }
                listWinners.forEach { winnersId ->
                    scoreList.add(
                        Score().apply {
                            this.idUser = winnersId
                            this.gameId = currentGame?.date
                            this.score = if (winnersId == currentTurn?.userPreneur) (pointsToAdd * multiplier) else pointsToAdd
                        }.also {
                            it.save()
                        }
                    )
                }
                var x = 0
                scoreList.forEach {
                    this?.set(x, it)
                    x ++
                }
            }
        }?.save()
    }

    fun retrieveListOfScores() : List<Score> {
        val listOfPlayers = currentGame?.listPlayers?.toList()
        val scoreList = mutableListOf<Score>()
        Score().queryAll().filterNot { it.gameId == currentGame?.date }.also {listScores ->
            for(i:Int in 0..listScores.size) {
                if (i % (listOfPlayers?.size ?: 0) == 0) {
                    listScores.subList(i-2 , i).forEach {score ->
                        listOfPlayers?.size?.also {
                            for(j:Int in 0..it) {
                                if (score.idUser == listOfPlayers[j]) scoreList.add(j, score)
                            }
                        }
                    }
                }
            }
        }
        return scoreList
    }
}

fun deroulement() {
    GameManager.startGame(listOf("kevin", "lerna", "bruno"))
    GameManager.startTurn(TypeDePrise.GARDE.value, "bruno")
    GameManager.addScore(
        listWinners = listOf("kevin", "lerna"),
        listLosers = listOf("bruno"),
        pointsToAdd = 42
    )
    var listTotalOfScores= GameManager.retrieveListOfScores()
    //TODO display this list

    GameManager.startTurn(TypeDePrise.PETITE.value, "lerna")
    GameManager.addScore(
        listWinners = listOf("lerna"),
        listLosers = listOf("bruno", "kevin"),
        pointsToAdd = 36
    )
    listTotalOfScores = GameManager.retrieveListOfScores()
    //TODO display this list

    GameManager.startTurn(TypeDePrise.PETITE.value, "lerna")
    GameManager.addScore(
        listWinners = listOf("lerna"),
        listLosers = listOf("bruno", "kevin"),
        pointsToAdd = 36
    )
    listTotalOfScores = GameManager.retrieveListOfScores()
    //TODO display this list

}