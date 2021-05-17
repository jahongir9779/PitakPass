package com.novatec.epitak_passenger.data.source

import com.novatec.epitak_passenger.util.ResultWrapper
import com.novatec.epitak_passenger.data.repository.CarDataStore
import com.novatec.epitak_passenger.data.repository.CarRemote
import com.novatec.epitak_passenger.data.repository.UserDataStore
import com.novatec.epitak_passenger.domain.model.Car
import com.novatec.epitak_passenger.domain.model.CarColor
import com.novatec.epitak_passenger.domain.model.CarDetails
import com.novatec.epitak_passenger.domain.model.CarModel
import javax.inject.Inject

/**
 * Implementation of the [UserDataStore] interface to provide a means of communicating
 * with the remote data source
 */
open class CarRemoteDataStore @Inject constructor(private val carRemote: CarRemote) :
    CarDataStore {
//    override suspend fun getCars(token: String): ResultWrapper<List<CarDetails>> {
//        return carRemote.getCars(token)
//    }

    override suspend fun getCarModels(
                                      ): ResultWrapper<List<CarModel>> {
        return carRemote.getCarModels()
    }

    override suspend fun getCarColors(
                                      ): ResultWrapper<List<CarColor>> {
        return carRemote.getCarColors()

    }

//    override suspend fun createCar( car: Car): ResultWrapper<String> {
//        return carRemote.createCar( car)
//    }
//
//    override suspend fun updateCar( car: Car): ResultWrapper<String> {
//        return carRemote.updateCar( car)
//    }
//
//    override suspend fun deleteCar( id: Long): ResultWrapper<String> {
//        return carRemote.deleteCar( id)
//    }
//
//    override suspend fun setDefaultCar( id: Long): ResultWrapper<String> {
//        return carRemote.setDefaultCar( id)
//    }

}