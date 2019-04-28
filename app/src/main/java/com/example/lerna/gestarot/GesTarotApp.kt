package com.example.lerna.gestarot

import android.app.Application
import io.realm.Realm

class GesTarotApp: Application(){
    override fun onCreate() {
        super.onCreate()
        Realm.init(this)
    }
}
