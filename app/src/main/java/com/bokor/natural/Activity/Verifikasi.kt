package com.bokor.natural.Activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.bokor.natural.R
import com.google.firebase.auth.PhoneAuthProvider
import kotlinx.android.synthetic.main.activity_loginactivity.*
import kotlinx.android.synthetic.main.activity_verifikasi.*

class Verifikasi : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_verifikasi)


        val verificationId = intent.getStringExtra("verificationId")

        btn_verifikasi.setOnClickListener {

            if (kodeverifikasi.text.toString().isEmpty()) {

            } else {
                val credential = PhoneAuthProvider.getCredential(
                    verificationId,
                    kodeverifikasi.text.toString()
                )

                Loginactivity().mAuth.signInWithCredential(credential).addOnSuccessListener {
                    Intent(this@Verifikasi, MainActivity::class.java).apply {
                        startActivity(this)
                    }
//                        .addOnFailureListener {
//                        Toast.makeText(this@Verifikasi, "Verifikasi Gagal", Toast.LENGTH_SHORT)
//                            .show()
//                    }
                }
            }
        }
    }
}
