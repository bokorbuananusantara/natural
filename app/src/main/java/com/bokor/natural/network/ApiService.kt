package com.bokor.natural.network

import com.bokor.natural.saldo.ResponSaldo
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {

    @GET("getsaldo/{key}")
    fun getsaldo(
        @Path("key") key : String): retrofit2.Call<ResponSaldo>



}