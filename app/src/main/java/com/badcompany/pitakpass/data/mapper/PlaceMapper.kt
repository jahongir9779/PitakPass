package com.badcompany.pitakpass.data.mapper

import com.badcompany.pitakpass.data.model.PlaceEntity
import com.badcompany.pitakpass.domain.domainmodel.Place
import javax.inject.Inject


/**
 * Map a [PlaceEntity] to and from a [Place] instance when data is moving between
 * this later and the Domain layer
 */
open class PlaceMapper @Inject constructor() : Mapper<PlaceEntity, Place> {

    /**
     * Map a [PlaceEntity] instance to a [Place] instance
     */
    override fun mapFromEntity(type: PlaceEntity): Place {
        return Place(type.districtId,
                     type.regionId,
                     type.lat,
                     type.lon,
                     type.regionName,
                     type.districtName,
                     type.name)
    }

    /**
     * Map a [Place] instance to a [PlaceEntity] instance
     */
    override fun mapToEntity(type: Place): PlaceEntity {
        return PlaceEntity(type.districtId,
                           type.regionId,
                           type.lat,
                           type.lon,
                           type.regionName,
                           type.districtName,
                           type.name)
    }


}