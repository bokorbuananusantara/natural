package com.bokor.natural

import android.content.Intent
import android.content.pm.ActivityInfo
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler

class Splashscreen : AppCompatActivity() {
    lateinit var handler: Handler

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splashscreen)
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
        handler = Handler()

        handler.postDelayed(Runnable {
            var intent = intent
            intent = Intent(this@Splashscreen, Weelcome::class.java)
            startActivity(intent)
            this@Splashscreen.finish()
        }, 5000)
    }
}
