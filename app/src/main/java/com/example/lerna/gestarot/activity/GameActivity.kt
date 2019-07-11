package com.example.lerna.gestarot.activity

import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.support.v7.app.AppCompatActivity
import android.widget.*
import com.example.lerna.gestarot.R
import com.example.lerna.gestarot.activity.HomeActivity.Companion.NBPLAYER
import kotlinx.android.synthetic.main.activity_game.*
import kotlinx.android.synthetic.main.alert_type_prise.view.*

class GameActivity : AppCompatActivity() {

    private val nbPlayer : Int by lazy { intent?.extras?.getInt(NBPLAYER) ?: 0 }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game)


        val playerRow = TableRow(this)
        playerRow.layoutParams = TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.MATCH_PARENT)
        val typeTv = (layoutInflater.inflate(R.layout.item_score, null) as TextView)
        typeTv.text = "Type"
        typeTv.layoutParams = TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.MATCH_PARENT)
        playerRow.addView(typeTv)

        //for (i in 0 until nbPlayer) {
        //    playerRow.addView(layoutInflater.inflate(R.layout.item_player_three, null) as ImageView)
       // }

        if(nbPlayer < 4){
            playerRow.addView(layoutInflater.inflate(R.layout.item_player_three, null) as ImageView)
            playerRow.addView(layoutInflater.inflate(R.layout.item_player_four, null) as ImageView)
            playerRow.addView(layoutInflater.inflate(R.layout.item_player_five, null) as ImageView)
        } else if(nbPlayer < 5){
            playerRow.addView(layoutInflater.inflate(R.layout.item_player_three, null) as ImageView)
            playerRow.addView(layoutInflater.inflate(R.layout.item_player_four, null) as ImageView)
            playerRow.addView(layoutInflater.inflate(R.layout.item_player_five, null) as ImageView)
            playerRow.addView(layoutInflater.inflate(R.layout.item_player_four, null) as ImageView)
        }else{
            playerRow.addView(layoutInflater.inflate(R.layout.item_player_three, null) as ImageView)
            playerRow.addView(layoutInflater.inflate(R.layout.item_player_four, null) as ImageView)
            playerRow.addView(layoutInflater.inflate(R.layout.item_player_five, null) as ImageView)
            playerRow.addView(layoutInflater.inflate(R.layout.item_player_four, null) as ImageView)
            playerRow.addView(layoutInflater.inflate(R.layout.item_player_three, null) as ImageView)
        }

        tableLayout.addView(playerRow, TableLayout.LayoutParams(TableLayout.LayoutParams.MATCH_PARENT, TableLayout.LayoutParams.WRAP_CONTENT))
        tableLayout.requestLayout()

        buttonNextPlay.setOnClickListener {

            val alertNewTurn = AlertDialog.Builder(this)
            alertNewTurn.setTitle("Choix Type de prise")
            val rootView = layoutInflater.inflate(R.layout.alert_type_prise, null, false)


            rootView.roiCoeur.text = "D"


            alertNewTurn.setView(rootView)
            alertNewTurn.create().show()



            val scoreRow = TableRow(this)
            scoreRow.layoutParams = TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.MATCH_PARENT)
            val typeDePrise  = layoutInflater.inflate(R.layout.item_score, null) as TextView
            typeDePrise.text = "Garde"
            scoreRow.addView(typeDePrise)
            for (i in 0 until nbPlayer) {
                val scoreTv = layoutInflater.inflate(R.layout.item_score, null) as TextView
                scoreTv.text = i.toString()
                scoreRow.addView(scoreTv)
            }
            tableLayout.addView(scoreRow, TableLayout.LayoutParams(TableLayout.LayoutParams.MATCH_PARENT, TableLayout.LayoutParams.WRAP_CONTENT))
            tableLayout.requestLayout()
        }
    }

}
