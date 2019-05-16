package com.example.lerna.gestarot.activity

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.ImageView
import android.widget.TableLayout
import android.widget.TableRow
import android.widget.TextView
import com.example.lerna.gestarot.R
import com.example.lerna.gestarot.activity.HomeActivity.Companion.NBPLAYER
import kotlinx.android.synthetic.main.activity_game.*

class GameActivity : AppCompatActivity() {

    private val nbPlayer : Int by lazy { intent?.extras?.getInt(NBPLAYER) ?: 0 }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game)


        val playerRow = TableRow(this)
        playerRow.layoutParams = TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.MATCH_PARENT)
        val typeTv = (layoutInflater.inflate(R.layout.score_item, null) as TextView)
        typeTv.text = "Type"
        typeTv.layoutParams = TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.MATCH_PARENT)
        playerRow.addView(typeTv)
        for (i in 0 until nbPlayer) {
            playerRow.addView(layoutInflater.inflate(R.layout.player_item, null) as ImageView)
        }
        tableLayout.addView(playerRow, TableLayout.LayoutParams(TableLayout.LayoutParams.MATCH_PARENT, TableLayout.LayoutParams.WRAP_CONTENT))
        tableLayout.requestLayout()

        buttonNextPlay.setOnClickListener {
            val scoreRow = TableRow(this)
            scoreRow.layoutParams = TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.MATCH_PARENT)
            val typeDePrise  = layoutInflater.inflate(R.layout.score_item, null) as TextView
            typeDePrise.text = "Garde"
            scoreRow.addView(typeDePrise)
            for (i in 0 until nbPlayer) {
                val scoreTv = layoutInflater.inflate(R.layout.score_item, null) as TextView
                scoreTv.text = i.toString()
                scoreRow.addView(scoreTv)
            }
            tableLayout.addView(scoreRow, TableLayout.LayoutParams(TableLayout.LayoutParams.MATCH_PARENT, TableLayout.LayoutParams.WRAP_CONTENT))
            tableLayout.requestLayout()
        }
    }

}
