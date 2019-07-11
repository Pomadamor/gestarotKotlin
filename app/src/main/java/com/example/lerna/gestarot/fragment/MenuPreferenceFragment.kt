package com.example.lerna.gestarot.fragment

import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.lerna.gestarot.R
import com.example.lerna.gestarot.activity.HomeActivity
import com.example.lerna.gestarot.activity.menu.InfoActivity
import com.example.lerna.gestarot.activity.menu.LegalTermActivity
import com.example.lerna.gestarot.bdd.model.User
import com.vicpin.krealmextensions.deleteAll
import com.vicpin.krealmextensions.queryFirst
import com.vicpin.krealmextensions.save
import kotlinx.android.synthetic.main.activity_account.*
import kotlinx.android.synthetic.main.fragment_preference.*

class MenuPreferenceFragment() : Fragment() {

    companion object {
        fun getFragment(): MenuPreferenceFragment{
            val fragment = MenuPreferenceFragment()
            return fragment
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_preference, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        option_preference_open?.setOnClickListener {
            (activity as HomeActivity).hidePreference()
        }

        preference_option?.setOnClickListener {
            (activity as HomeActivity).hidePreference()
        }

        accountLegales.setOnClickListener {
            startActivity(Intent(context, LegalTermActivity::class.java))
        }

        accountPropos.setOnClickListener {
            startActivity(Intent(context, InfoActivity::class.java))
        }

        accountDeco.setOnClickListener {
            User().deleteAll()
            (activity as HomeActivity).disconnect()
        }
    }

    fun alphaDown(){
        option_preference_open?.animate()?.alpha(0f)?.duration = 250
    }

    fun alphaUp(){
        option_preference_open?.animate()?.alpha(1f)?.duration = 500
    }
}
