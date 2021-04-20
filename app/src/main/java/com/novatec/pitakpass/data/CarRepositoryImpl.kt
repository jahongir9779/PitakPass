package com.novatec.pitakpass.data

import com.novatec.pitakpass.data.source.CarDataStoreFactory
import com.novatec.pitakpass.domain.model.Car
import com.novatec.pitakpass.domain.model.CarColor
import com.novatec.pitakpass.domain.model.CarDetails
import com.novatec.pitakpass.domain.model.CarModel
import com.novatec.pitakpass.domain.repository.CarRepository
import com.novatec.pitakpass.domain.repository.UserRepository
import com.novatec.pitakpass.util.ErrorWrapper
import com.novatec.pitakpass.util.ResultWrapper
import javax.inject.Inject

/**
 * Provides an implementation of the [UserRepository] interface for communicating to and from
 * data sources
 */
class CarRepositoryImpl @Inject constructor(private val factory: CarDataStoreFactory) :
    CarRepository {

//    override suspend fun getCars(token: String): ResultWrapper<List<CarDetails>> {
//        val response = factory.retrieveDataStore(false)
//            .getCars(token)
//        return when (response) {
//            is ErrorWrapper.ResponseError -> response
//            is ErrorWrapper.SystemError -> response
//            is ResultWrapper.Success -> {
//                ResultWrapper.Success(response.value)
//            }
//            ResultWrapper.InProgress -> ResultWrapper.InProgress
//        }
//    }


    override suspend fun getCarModels( ): ResultWrapper<List<CarModel>> {
        val response = factory.retrieveDataStore(false)
            .getCarModels()
        return when (response) {
            is ErrorWrapper.ResponseError -> response
            is ErrorWrapper.SystemError -> response
            is ResultWrapper.Success -> {
                ResultWrapper.Success(response.value)
            }
            ResultWrapper.InProgress -> ResultWrapper.InProgress
        }
    }

    override suspend fun getCarColors( ): ResultWrapper<List<CarColor>> {
        val response = factory.retrieveDataStore(false).getCarColors()
        return when (response) {
            is ErrorWrapper.ResponseError -> response
            is ErrorWrapper.SystemError -> response
            is ResultWrapper.Success -> {
                ResultWrapper.Success(response.value)
            }
            ResultWrapper.InProgress -> ResultWrapper.InProgress
        }
    }

//    override suspend fun createCar( car: Car): ResultWrapper<String> {
//        val response = factory.retrieveDataStore(false).createCar( car)
//        return when (response) {
//            is ErrorWrapper.ResponseError -> response
//            is ErrorWrapper.SystemError -> response
//            is ResultWrapper.Success -> {
//                ResultWrapper.Success("SUCCESS")
//            }
//            ResultWrapper.InProgress -> ResultWrapper.InProgress
//        }
//    }
//
//    override suspend fun updateCar( car: Car): ResultWrapper<String> {
//        val response = factory.retrieveDataStore(false).updateCar( car)
//        return when (response) {
//            is ErrorWrapper.ResponseError -> response
//            is ErrorWrapper.SystemError -> response
//            is ResultWrapper.Success -> {
//                ResultWrapper.Success("SUCCESS")
//            }
//            ResultWrapper.InProgress -> ResultWrapper.InProgress
//        }
//    }
//
//    override suspend fun deleteCar( id: Long): ResultWrapper<String> {
//        val response = factory.retrieveDataStore(false)
//            .deleteCar( id)
//        return when (response) {
//            is ErrorWrapper.ResponseError -> response
//            is ErrorWrapper.SystemError -> response
//            is ResultWrapper.Success -> {
//                ResultWrapper.Success("SUCCESS")
//            }
//            ResultWrapper.InProgress -> ResultWrapper.InProgress
//        }
//    }
//
//    override suspend fun setDefaultCar( id: Long): ResultWrapper<String> {
//        val response = factory.retrieveDataStore(false)
//            .setDefaultCar( id)
//        return when (response) {
//            is ErrorWrapper.ResponseError -> response
//            is ErrorWrapper.SystemError -> response
//            is ResultWrapper.Success -> {
//                ResultWrapper.Success("SUCCESS")
//            }
//            ResultWrapper.InProgress -> ResultWrapper.InProgress
//        }
//    }


}