package com.example.lerna.gestarot
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.view.Menu
import android.view.MenuItem
import android.widget.EditText
import com.example.lerna.gestarot.HomeActivity.Companion.USER
import com.example.lerna.gestarot.HomeActivity.Companion.TYPES_USER
import com.vicpin.krealmextensions.deleteAll
import com.vicpin.krealmextensions.save
import kotlinx.android.synthetic.main.activity_connect.*

class ConnectActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_connect)

        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        btnPartiConnect.setOnClickListener {
            val alertParti = AlertDialog.Builder(this)
            val pseudoEt = EditText(this)
            alertParti.setTitle("Pseudo")
            alertParti.setView(pseudoEt)
            alertParti.setPositiveButton("Ok") { _, _ ->
                val pseudo = pseudoEt.text
                User().deleteAll()
                User().apply{
                    this.pseudo=pseudo.toString()
                    this.email="plop@plop"
                    this.idUser="42"
                }.save()
                setResult(TYPES_USER)
                finish()
            }
            alertParti.setNegativeButton("Annuler") {dialog, _ ->
                dialog.dismiss()
            }
            alertParti.show()


        }
    }

}
