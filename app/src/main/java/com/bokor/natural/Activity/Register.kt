package com.bokor.natural.Activity

import android.app.ProgressDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bokor.natural.R
import com.bokor.natural.addwisata.ResponseUpload
import com.bokor.natural.network.Networkmodule
import com.vanillaplacepicker.utils.ToastLength
import com.vanillaplacepicker.utils.ToastUtils
import kotlinx.android.synthetic.main.activity_register.*
import retrofit2.Call
import retrofit2.Response

class Register : AppCompatActivity() {

    private lateinit var verificationId : String
    private var pDialog : ProgressDialog? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        buttonregister.setOnClickListener {
            if (notelp.text.toString().isEmpty()){
                ToastUtils.showToast(this@Register, "Silahkan Isi Dulu", ToastLength.Long)
            }else{
                sendData(
                    kodenegara.text.toString() + notelp.text.toString(),
                    nama.text.toString(),
                    email.text.toString(),
                    alamat.text.toString()
                )
            }
        }
    }
    private fun sendData(notelp : String, nama:String, email:String, alamat:String){
        Networkmodule.getService().doRegister(noTelp = notelp, nama=nama, email=email, alamat=alamat)
            .enqueue(object: retrofit2.Callback<ResponseUpload>{

                override fun onFailure(call: Call<ResponseUpload>, t: Throwable) {
                    ToastUtils.showToast(this@Register, t.message.toString())
                }

                override fun onResponse(
                    call: Call<ResponseUpload>,
                    response: Response<ResponseUpload>
                ) {
                    pDialog?.dismiss()
                    if(response.isSuccessful){
                        if(response.body()?.code == "200"){
                            ToastUtils.showToast(this@Register, response.body()?.message)
                            finish()
                            Intent(this@Register, Loginactivity::class.java).apply {
                                startActivity(this)
                            }
                        }else {
                            ToastUtils.showToast(this@Register, response.errorBody().toString())
                        }
                    }else{
                        ToastUtils.showToast(this@Register, response.errorBody().toString())
                    }

                }
            })
    }

}
