package com.badcompany.pitakpass.data.mapper

import com.badcompany.pitakpass.data.model.*
import com.badcompany.pitakpass.domain.domainmodel.*
import javax.inject.Inject


/**
 * Map a [DriverPostEntity] to and from a [DriverPostModel] instance when data is moving between
 * this later and the Domain layer
 */
open class PassengerPostMapper @Inject constructor() :
    Mapper<PassengerPostEntity, PassengerPost> {

    /**
     * Map a [PassengerPostEntity] instance to a [PassengerPost] instance
     */
    override fun mapFromEntity(type: PassengerPostEntity): PassengerPost {

        val placeFrom = Place(type.from.districtId,
                                   type.from.regionId,
                                   type.from.lat,
                                   type.from.lon,
                                   type.from.regionName,
                                   type.from.name)

        val placeTo = Place(type.to.districtId,
                                 type.to.regionId,
                                 type.to.lat,
                                 type.to.lon,
                                 type.to.regionName,
                                 type.to.name)

        return PassengerPost(type.id,
                                  placeFrom,
                                  placeTo,
                                  type.price,
                                  type.departureDate,
                                  type.createdDate,
                                  type.updatedDate,
                                  type.finishedDate,
                                  type.timeFirstPart,
                                  type.timeSecondPart,
                                  type.timeThirdPart,
                                  type.timeFourthPart,
                                  type.airConditioner,
                                  type.remark,
                                  type.postStatus,
                                  type.seat,
                                  type.postType)
    }

    /**
     * Map a [PassengerPost] instance to a [PassengerPostEntity] instance
     */
    override fun mapToEntity(type: PassengerPost): PassengerPostEntity {

        val placeFrom = PlaceEntity(type.from.districtId,
                                    type.from.regionId,
                                    type.from.lat,
                                    type.from.lon,
                                    type.from.regionName,
                                    type.from.name)

        val placeTo = PlaceEntity(type.to.districtId,
                                  type.to.regionId,
                                  type.to.lat,
                                  type.to.lon,
                                  type.to.regionName,
                                  type.to.name)


        return PassengerPostEntity(type.id,
                                   placeFrom,
                                   placeTo,
                                   type.price,
                                   type.departureDate,
                                   type.createdDate,
                                   type.updatedDate,
                                   type.finishedDate,
                                   type.timeFirstPart,
                                   type.timeSecondPart,
                                   type.timeThirdPart,
                                   type.timeFourthPart,
                                   type.airConditioner,
                                   type.remark,
                                   type.postStatus,
                                   type.seat,
                                   type.postType)
    }

}