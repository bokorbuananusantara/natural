package com.bokor.natural.network

import com.bokor.natural.addwisata.ResponseUpload
import com.bokor.natural.saldo.ResponSaldo
import com.bokor.natural.user.ResponseUser
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.http.*

interface ApiService {

    @GET("getsaldo/{key}")
    fun getsaldo(
        @Path("key") key : String): retrofit2.Call<ResponSaldo>

    //Multipart harus POST gak bisa pake GET
    @Multipart
    @POST("Wisata/addWisata")
    fun doUpload(
        @Part foto : MultipartBody.Part,
        @Part("nama_wisata") namawisata: RequestBody,
        @Part("deskripsi") deskripsi: RequestBody,
        @Part("alamat") alamat: RequestBody,
        @Part("latitude") lat: RequestBody,
        @Part("longitude") lon: RequestBody,
        @Part("no_telp") notelp: RequestBody
    ): retrofit2.Call<ResponseUpload>

    @FormUrlEncoded
    @POST("Register/doRegister")
    fun doRegister(
        @Field("notelp") noTelp : String?,
        @Field("nama") nama : String?,
        @Field("email") email : String?,
        @Field("alamat") alamat : String?
    ): retrofit2.Call<ResponseUpload>

    @FormUrlEncoded
    //post in APISERVICE WEB "Register/getDetail"
    @POST("Register/getDetail")
    fun doLogin(
        @Field("notelp") noTelp : String?
    ): retrofit2.Call<ResponseUser>


}