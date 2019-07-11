package com.example.lerna.gestarot.activity

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.graphics.Point
import android.os.Bundle
import android.support.v7.app.AppCompatActivity;
import com.example.lerna.gestarot.R
import com.example.lerna.gestarot.activity.menu.InfoActivity
import com.example.lerna.gestarot.activity.menu.LegalTermActivity
import com.example.lerna.gestarot.bdd.model.User
import com.example.lerna.gestarot.fragment.MenuPreferenceFragment
import com.example.lerna.gestarot.fragment.UserInfoFragment
import com.vicpin.krealmextensions.queryFirst
import kotlinx.android.synthetic.main.activity_home.*
import kotlinx.android.synthetic.main.content_home.*
import kotlinx.android.synthetic.main.fragment_preference.*

/**
 * Cette page permet de gérer la vue centrale de l'application.
 * @version 0.1
 * @author Ines
 */


class HomeActivity : AppCompatActivity() {

    companion object {
        const val CONNECT_HOME = 1
        const val TYPES_USER = 2
        const val USER = "a"
        const val NBPLAYER = ""

    }

    var userFragment : UserInfoFragment?=null
    var menuFragment : MenuPreferenceFragment?=MenuPreferenceFragment.getFragment()


    /**
     * Méthode par défaut qui permet de gérer l'affichage du Layout.
     *  @param savedInstanceState permet de recuperer les instances sauvegardées
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        if(user == null){
            user= User().queryFirst()
            userFragment =
                UserInfoFragment.getFragment(user?.pseudo ?: "Se connecter")
        }else{
            userFragment = UserInfoFragment.getFragment("Se connecter")
        }
        supportFragmentManager.beginTransaction()
            .replace(activity_home_user.id, userFragment!!)
            .replace(activity_home_menu.id, menuFragment!!)
            .commit()

        hidePreference()

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
            }else startActivityForResult(Intent(this, ConnectActivity::class.java),
                CONNECT_HOME
            )
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

        preference_option2.setOnClickListener {
            showPreference()
        }

    }

    var user: User?= null

    /**
     * Permet de géré l'affichage d'un utilisateur si celui ci est connecté
     * @param requestCode  Int, identifiant de la requête
     * @param resultCode: Int, résultat de la requête
     * @param data: Intent?, correspond au donnée contenu dans la requête
     */

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(requestCode == CONNECT_HOME && resultCode == TYPES_USER){
            user= User().queryFirst()
            userFragment?.getPseudoTv()?.text=user?.pseudo
        }
    }

    override fun onResume() {
        super.onResume()
        user= User().queryFirst()
        userFragment?.getPseudoTv()?.text=user?.pseudo
    }



    fun hidePreference(){
        activity_home_menu?.animate()?.translationX(screenWidth(this).toFloat())?.duration = 300
        activity_home_background?.animate()?.alpha(0f)?.duration = 250
    }

    fun showPreference(){
        activity_home_menu?.animate()?.translationX(0f)?.duration = 300
        activity_home_background?.animate()?.alpha(1f)?.duration = 500
    }

    fun disconnect() {
        userFragment?.getPseudoTv()?.text=" Se connecter "
    }
}

fun getDisplay(context: Context): Point {
    val display = (context as Activity).windowManager.defaultDisplay
    val size = Point()
    display.getSize(size)
    return size
}

fun screenWidth(context: Context) = getDisplay(context).x
fun screenHeight(context: Context) = getDisplay(context).y