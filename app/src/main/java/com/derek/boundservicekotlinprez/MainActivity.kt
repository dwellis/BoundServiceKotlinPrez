package com.derek.boundservicekotlinprez

import android.app.Service
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.IBinder
import android.widget.Toast

class MainActivity : AppCompatActivity() {


     var mService : LocalService? = null
    private var mBound : Boolean = false





    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onStart() {
        super.onStart()


        Intent(this, LocalService::class.java).also {
            intent -> bindService(intent, connection, Context.BIND_AUTO_CREATE)
        }
    }

    override fun onResume() {
        super.onResume()

    }

    override fun onStop() {
        super.onStop()
        unbindService(connection)
        mBound = false
    }

    fun onButtonClick(view: android.view.View) {
        /*var bInetent = Intent(this,LocalService::class.java)
        bindService(bInetent,connection,Service.BIND_AUTO_CREATE) */
        if(mBound) {
            // call the method from local service
            val num : Int = mService!!?.randomNumber
            Toast.makeText(applicationContext, "Random number is $num", Toast.LENGTH_LONG).show()
        }

    }

    private val connection = object : ServiceConnection {
        override fun onServiceConnected(cn: ComponentName?, iBinder: IBinder?) {
            val binder = iBinder as LocalService.LocalBinder
            mService = binder.getService()
            mBound = true
        }

        override fun onServiceDisconnected(p0: ComponentName?) {
            mBound = false;
        }

    }

}