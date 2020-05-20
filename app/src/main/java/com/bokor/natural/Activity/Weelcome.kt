package com.bokor.natural.Activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bokor.natural.R
import kotlinx.android.synthetic.main.activity_weelcome.*

class Weelcome : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_weelcome)
//        masuk.setOnClickListener {
//            val intent = Intent (this, PhoneAuth::class.java)
//            startActivity(intent)
//        }
        btnlogin.setOnClickListener {
            val intent = Intent (this, Loginactivity::class.java)
            startActivity(intent)
        }
        btnregister.setOnClickListener {
            val intent = Intent (this, Register::class.java)
            startActivity(intent)
        }
    }
}
