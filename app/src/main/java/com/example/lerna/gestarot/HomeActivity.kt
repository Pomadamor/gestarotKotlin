package com.example.lerna.gestarot

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.support.v7.app.AppCompatActivity;
import android.widget.LinearLayout
import android.widget.TextView
import com.vicpin.krealmextensions.queryFirst

import kotlinx.android.synthetic.main.activity_home.*
import kotlinx.android.synthetic.main.content_home.*

class HomeActivity : AppCompatActivity() {

    companion object {
        const val CONNECT_HOME = 1
        const val TYPES_USER = 2
        const val USER = "a"
        const val NBPLAYER = ""

    }

    var userFragment : UserInfoFragment?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        if(user == null){
            user= User().queryFirst()
            userFragment = UserInfoFragment.getFragment(user?.pseudo ?: "Se connecter")
        }else{
            userFragment = UserInfoFragment.getFragment("")
        }
        supportFragmentManager.beginTransaction().replace(activity_home_user.id, userFragment!!).commit()
        supportFragmentManager.beginTransaction().replace(activity_home_user.id, userFragment!!).commit()

        activity_home_user.setOnClickListener{
            if(user != null){
                startActivity(Intent(this, AccountActivity::class.java))
                /*val alertParti = AlertDialog.Builder(this)
                alertParti.setTitle("Profil")
                val infosUsers = LinearLayout(this)

                val pseudoTv = TextView(this)
                val emailTv = TextView(this)

                infosUsers.addView(pseudoTv)
                infosUsers.addView(emailTv)

                pseudoTv.text= user?.pseudo
                emailTv.text= "mail"

                alertParti.setView(infosUsers)

                alertParti.show()*/
            }else startActivityForResult(Intent(this, ConnectActivity::class.java),CONNECT_HOME)
        }

        btnThreePlayer.setOnClickListener {
            val nbPlayer = 3
            val intent = Intent(this, GameActivity::class.java)
            intent.putExtra(NBPLAYER, nbPlayer)
            startActivity(intent)
        }
        btnFourPlayer.setOnClickListener {
            val nbPlayer = 4
            val intent = Intent(this, GameActivity::class.java)
            intent.putExtra(NBPLAYER, nbPlayer)
            startActivity(intent)
        }
        btnFivePlayer.setOnClickListener {
            val nbPlayer = 5
            val intent = Intent(this, GameActivity::class.java)
            intent.putExtra(NBPLAYER, nbPlayer)
            startActivity(intent)
        }
    }

    var user:User?= null
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(requestCode == CONNECT_HOME && resultCode == TYPES_USER){
            user= User().queryFirst()
            userFragment?.getPseudoTv()?.text=user?.pseudo
        }
    }



}
