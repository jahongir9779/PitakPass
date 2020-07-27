package com.badcompany.pitakpass.domain.repository

import com.badcompany.pitakpass.util.ResultWrapper
import com.badcompany.pitakpass.domain.model.*


interface CarRepository {

//    suspend fun getCars(token: String): ResultWrapper<List<CarDetails>>
    suspend fun getCarModels(token: String, lang:String): ResultWrapper<List<CarModel>>
    suspend fun getCarColors(token: String, lang:String): ResultWrapper<List<CarColor>>
//    suspend fun createCar(token: String, car: Car): ResultWrapper<String>
//    suspend fun updateCar(token: String, car: Car): ResultWrapper<String>
//    suspend fun deleteCar(token: String, id: Long): ResultWrapper<String>
//    suspend fun setDefaultCar(token: String, id:Long): ResultWrapper<String>


}