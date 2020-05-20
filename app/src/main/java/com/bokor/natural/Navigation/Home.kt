package com.bokor.natural.Navigation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.bokor.natural.R
import com.bokor.natural.network.Networkmodule
import com.bokor.natural.saldo.ResponSaldo
import com.google.android.gms.common.api.Response
import kotlinx.android.synthetic.main.activity_home.*
import retrofit2.Call

class Home : Fragment() {


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.activity_home, container, false)
        return view

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getSaldo("FSSnC6UKtwXtrzgDf8nozC8pHhYZfluoYHrt3v4ixdQuZBBRo5")
    }

    private fun getSaldo(key: String) {
        Networkmodule.getService().getsaldo(key)

            .enqueue(object : retrofit2.Callback<ResponSaldo>{
                override fun onFailure(call: Call<ResponSaldo>, t: Throwable) {
                    Toast.makeText(activity, t.message.toString(), Toast.LENGTH_SHORT).show()
                }

                override fun onResponse(
                    call: retrofit2.Call<ResponSaldo>,
                    response: retrofit2.Response<ResponSaldo>
                ) {
                    if (response.isSuccessful){

                        val data = response.body()
                        saldo.text=data?.balance.toString()

                    }
                }


            })
    }
}
