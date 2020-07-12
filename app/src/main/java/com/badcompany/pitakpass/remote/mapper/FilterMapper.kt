package com.badcompany.remote.mapper

import com.badcompany.data.mapper.Mapper
import com.badcompany.data.model.DriverPostEntity
import com.badcompany.data.model.FilterEntity
import com.badcompany.data.model.PassengerPostEntity
import com.badcompany.remote.model.FilterModel
import javax.inject.Inject


/**
 * Map a [DriverPostEntity] to and from a [DriverPostModel] instance when data is moving between
 * this later and the Domain layer
 */
open class FilterMapper @Inject constructor() : Mapper<FilterEntity, FilterModel> {

    /**
     * Map a [PassengerPostEntity] instance to a [PassengerPost] instance
     */
    override fun mapFromEntity(type: FilterEntity): FilterModel {
        return FilterModel(type.airConditioner,
                           type.departureDate,
                           type.fromDistrictId,
                           type.fromRegionId,
                           type.toDistrictId,
                           type.toRegionId,
                           type.maxPrice,
                           type.minPrice,
                           type.priceOrder,
                           type.seat,
                           type.timeFirstPart,
                           type.timeSecondPart,
                           type.timeThirdPart,
                           type.timeFourthPart)
    }

    /**
     * Map a [PassengerPost] instance to a [PassengerPostEntity] instance
     */
    override fun mapToEntity(type: FilterModel): FilterEntity {
        return FilterEntity(type.airConditioner,
                            type.departureDate,
                            type.fromDistrictId,
                            type.fromRegionId,
                            type.toDistrictId,
                            type.toRegionId,
                            type.maxPrice,
                            type.minPrice,
                            type.priceOrder,
                            type.seat,
                            type.timeFirstPart,
                            type.timeSecondPart,
                            type.timeThirdPart,
                            type.timeFourthPart)
    }

}