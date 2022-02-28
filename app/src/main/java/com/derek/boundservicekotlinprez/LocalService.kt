package com.derek.boundservicekotlinprez

import android.app.Service
import android.content.Intent
import android.os.Binder
import android.os.IBinder
import java.util.*

class LocalService : Service() {

    // binder
    private val binder = LocalBinder()

    private val mGenerator = Random()

    val randomNumber : Int
        get() = mGenerator.nextInt(100);

   /* public int getRandomNumber(){
        retrun mGenerator.nextIn(100);
    }*/

    inner class LocalBinder : Binder() {
        fun getService() : LocalService {
            return this@LocalService
        }

    }

    override fun onBind(intent: Intent) : IBinder {
        return binder
    }

}


