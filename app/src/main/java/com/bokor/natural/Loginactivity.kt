package com.bokor.natural

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.google.firebase.FirebaseException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.PhoneAuthCredential
import com.google.firebase.auth.PhoneAuthProvider
import kotlinx.android.synthetic.main.activity_loginactivity.*
import java.util.concurrent.TimeUnit

class Loginactivity : AppCompatActivity() {
    val  mAuth = FirebaseAuth.getInstance()
    private lateinit var verificationId : String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_loginactivity)

        login.setOnClickListener {
            if (nohp.text.toString().isEmpty()){

            }else{
                val mNoTelp = "${kodenegara.text}${nohp.text}"
                PhoneAuthProvider.getInstance().verifyPhoneNumber(
                    mNoTelp,
                    30,
                    TimeUnit.SECONDS,
                    this@Loginactivity,
                    object : PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
                        override fun onVerificationCompleted(p0: PhoneAuthCredential) {
                            Log.d("onVerficationCompleted", p0?.smsCode.toString())
                        }

                        override fun onVerificationFailed(p0: FirebaseException) {
                            Log.d("onVerficationFailed", p0?.message.toString())
                        }

                        override fun onCodeSent(
                            p0: String,
                            p1: PhoneAuthProvider.ForceResendingToken
                        ) {
                            super.onCodeSent(p0, p1)
                            verificationId = p0
                            Toast.makeText(this@Loginactivity, "Kode telah dikirim",Toast.LENGTH_SHORT).show()
                            Intent(this@Loginactivity, MainActivity::class.java).apply {
                                this.putExtra("verificationId", verificationId)
                                startActivity(this)
                            }
                        }
                    }
                )
            }
        }
    }
}
