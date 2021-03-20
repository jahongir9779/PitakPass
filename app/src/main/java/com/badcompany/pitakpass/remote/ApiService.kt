package com.badcompany.pitakpass.remote

import com.badcompany.pitakpass.core.enums.EAppType
import com.badcompany.pitakpass.domain.model.User
import com.badcompany.pitakpass.domain.model.UserCredentials
import com.badcompany.pitakpass.remote.model.*
import com.badcompany.pitakpass.util.AppPrefs
import com.badcompany.pitakpass.util.RespFormatter
import okhttp3.MultipartBody
import retrofit2.http.*

/**
 * Defines the abstract methods used for interacting with the Bufferoo API
 */
interface ApiService {

    //AUTH API
    @Headers("Content-Type:application/json", "Accept: application/json")
    @POST("prof/mb/auth")
    suspend fun userLogin(@Body loginReq: LoginRequest,
                          @Header("Accept-Language") lang: String = AppPrefs.language): RespFormatter<UserCredentials>


    @Headers("Content-Type:application/json", "Accept: application/json")
    @POST("prof/mb/reg")
    suspend fun userRegister(@Body user: User,
                             @Header("Accept-Language") lang: String = AppPrefs.language): AuthResponse

    @Headers("Content-Type:application/json", "Accept: application/json")
    @POST("prof/mb/confirm")
    suspend fun smsConfirm(@Body user: UserCredentials): AuthSuccessResponse
    //END AUTH API


    //FILE UPLOAD API

    @Headers("Accept: application/json")
    @Multipart
    @POST("attach/image")
    suspend fun uploadPhoto(@Part file: MultipartBody.Part): PhotoUploadResponse


    @GET("force_update/action/versions")
    suspend fun getActiveAppVersions(@Query("appType") appType: String = EAppType.PASSENGER.name,
                                     @Query("platformType") platformType: String = "ANDROID"): RespFormatter<List<IdVersionDTO>>


    //END FILE UPLOAD API


//    class BufferooResponse {
//        lateinit var team: List<UserModel>
//    }

}

