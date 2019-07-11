package com.example.lerna.gestarot.fragment


import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.lerna.gestarot.R
import kotlinx.android.synthetic.main.fragment_user_info.*

class UserInfoFragment() : Fragment() {

    companion object {
        const val FUCKTHENAME="fuckthename"
        fun getFragment(pseudo:String): UserInfoFragment {
            val args = Bundle()
            args.putString(FUCKTHENAME, pseudo)
            val fragment = UserInfoFragment()
            fragment.arguments=args
            return fragment
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_user_info, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        pseudo.text=arguments?.getString(FUCKTHENAME)

    }

    fun getPseudoTv():TextView{
        return pseudo
    }

}
