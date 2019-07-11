package com.example.lerna.gestarot.activity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.widget.Toast
import com.example.lerna.gestarot.R
import com.example.lerna.gestarot.bdd.model.User
import com.vicpin.krealmextensions.deleteAll
import com.vicpin.krealmextensions.queryFirst
import com.vicpin.krealmextensions.save
import kotlinx.android.synthetic.main.activity_account.*

class AccountActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_account)
        val user = User().queryFirst()
        accountPseudo.setText(user?.pseudo)

        btnEditAccount.setOnClickListener {

            user?.apply {
                this.pseudo = accountPseudo.text.toString()
            }?.save()

            finish()
        }
    }

}
