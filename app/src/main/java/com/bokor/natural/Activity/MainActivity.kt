package com.bokor.natural.Activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bokor.natural.Navigation.Akun
import com.bokor.natural.Navigation.Home
import com.bokor.natural.Navigation.Inbox
import com.bokor.natural.Navigation.Pesanan
import com.bokor.natural.R
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportFragmentManager.beginTransaction()
            .replace(com.bokor.natural.R.id.koordinator_layout, Home())
            .commit()

        bottom.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                com.bokor.natural.R.id.navigation_awal -> {
                    supportFragmentManager.beginTransaction()
                        .replace(com.bokor.natural.R.id.koordinator_layout, Home())
                        .commit()
                    return@setOnNavigationItemSelectedListener true
                }

                com.bokor.natural.R.id.navigation_pesanan -> {
                    supportFragmentManager.beginTransaction()
                        .replace(com.bokor.natural.R.id.koordinator_layout, Pesanan())
                        .commit()
//                    intent = Intent(applicationContext, Help::class.java)
//                    startActivity(intent)
                    return@setOnNavigationItemSelectedListener true
                }

                com.bokor.natural.R.id.navigation_inbox -> {
                    supportFragmentManager.beginTransaction()
                        .replace(com.bokor.natural.R.id.koordinator_layout, Inbox())
                        .commit()
                    return@setOnNavigationItemSelectedListener true
                }

                com.bokor.natural.R.id.navigation_akun -> {
                    supportFragmentManager.beginTransaction()
                        .replace(com.bokor.natural.R.id.koordinator_layout, Akun())
                        .commit()
                    return@setOnNavigationItemSelectedListener true
                }


            }
            return@setOnNavigationItemSelectedListener false
        }


    }
}
