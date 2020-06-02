package com.bokor.natural.addwisata

import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.core.app.ActivityCompat
import com.bokor.natural.R
import com.bumptech.glide.Glide
import com.vanillaplacepicker.presentation.builder.VanillaPlacePicker
import com.vanillaplacepicker.utils.MapType
import com.vanillaplacepicker.utils.PickerType
import kotlinx.android.synthetic.main.activity_addmain.*
import okhttp3.Call
import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.Response

class addmain : CameraFilePickerBaseActivity() {

    private var latitude : Double? = 0.0
    private var longitude : Double? = 0.0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_addmain)

        btn_kamera.setOnClickListener {
            if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED){
                launchCamera()
            }else{
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    requestPermissions(arrayOf(android.Manifest.permission.READ_EXTERNAL_STORAGE, android.Manifest.permission.WRITE_EXTERNAL_STORAGE), 200)
                }
            }
        }

        btn_galery.setOnClickListener {
            launchFilePicker()
        }


        map.setOnClickListener{
            val placePicker = VanillaPlacePicker.Builder(this@addmain)
                .with(PickerType.MAP_WITH_AUTO_COMPLETE)
                .setMapType(MapType.NORMAL)
                .build()

            startActivityForResult(placePicker, 1052)
        }

        btn_simpan.setOnClickListener {
            if(flagUpload){
//                doUpload()
            }else{
                Toast.makeText(this@addmain, "Silahkan pilih file ! ", Toast.LENGTH_SHORT).show()
            }
        }
    }

//    private fun doUpload() {
//        //buat inputan untuk file dengan mime type yang diterima adalah file image
//        val files = RequestBody.create("image/*".toMediaTypeOrNull(), imageViewToByte(imagePreview, 40))
//
//        //membuat body untuk input type file
//        val imageInput = MultipartBody.Part.createFormData("files", "${System.nanoTime()}.jpg", files)
//
//        //membuat input type text dengan value dari judul.text.toString()
//        val namawisata = RequestBody.create("text/plain".toMediaTypeOrNull(), namawisata.text.toString())
//
//        val deskripsi = RequestBody.create("text/plain".toMediaTypeOrNull(), deskripsi.text.toString())
//
//        val alamat = RequestBody.create("text/plain".toMediaTypeOrNull(), alamat.text.toString())
//
//        val lat = RequestBody.create("text/plain".toMediaTypeOrNull(), latitude.toString())
//        val lon = RequestBody.create("text/plain".toMediaTypeOrNull(), longitude.toString())
//        val notelp = RequestBody.create("text/plain".toMediaTypeOrNull(), "085761990862")
//
//        NetworkModule.getService().doUpload(
//            foto = imageInput,
//            namawisata = namawisata,
//            deskripsi = deskripsi,
//            alamat = alamat,
//            lat = lat,
//            lon = lon,
//            notelp = notelp
//        ).enqueue(object : retrofit2.Callback<ResponseUpload>{
//            override fun onFailure(call: Call<ResponseUpload>, t: Throwable) {
//                Toast.makeText(this@addmain,"Upload Gagal !", Toast.LENGTH_SHORT).show()
//            }
//
//            override fun onResponse(
//                call: Call<ResponseUpload>,
//                response: Response<ResponseUpload>
//            ) {
//                Log.d("onVerficationCompleted", response.errorBody().toString())
//                Toast.makeText(this@addmain,"Upload Berhasil !", Toast.LENGTH_SHORT).show()
//            }
//        })
//    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        //preview foto
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 1002 && resultCode == RESULT_OK) {
            data?.data?.let { getMetaData(it, image_poto) }
            flagUpload = true
        } else if (requestCode == 1001 && resultCode == Activity.RESULT_OK) {
            previewImage(image_poto)
        }

        //placepicker
        if (resultCode == Activity.RESULT_OK && data != null) {
            when (requestCode) {
                1052 -> {
                    val vanillaAddress = VanillaPlacePicker.onActivityResult(data)
                    latitude = vanillaAddress?.latitude
                    longitude = vanillaAddress?.longitude
                    setToImageView(vanillaAddress?.latitude, vanillaAddress?.longitude)
                }
            }
        }
    }

    private fun setToImageView(latitude: Double?, longitude: Double?) {
        val key = getString(R.string.google_key)
        val imageMapsStatic = "https://maps.googleapis.com/maps/api/" +
                "staticmap?zoom=15&" +
                "size=2000x320&" +
                "markers=icon:http://illegal-trade.server411.tech/assets/map-marker-alt-solid.svg" +
                "|$latitude,$longitude&" +
                "key=$key"
        Glide.with(this)
            .load(imageMapsStatic)
            .into(map)
    }

    //request pemission decline or allow
    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if(requestCode == 200){
            if(grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                launchCamera()
            }
        }

    }
}
