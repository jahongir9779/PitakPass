package com.badcompany.pitakpass.data.mapper

import com.badcompany.pitakpass.data.model.PhotoEntity
import com.badcompany.pitakpass.domain.domainmodel.User
import com.badcompany.pitakpass.data.model.UserEntity
import com.badcompany.pitakpass.domain.domainmodel.PhotoBody
import javax.inject.Inject


/**
 * Map a [UserEntity] to and from a [User] instance when data is moving between
 * this later and the Domain layer
 */
open class PhotoMapper @Inject constructor(): Mapper<PhotoEntity, PhotoBody> {

    /**
     * Map a [UserEntity] instance to a [User] instance
     */
    override fun mapFromEntity(type: PhotoEntity): PhotoBody {
        return PhotoBody(type.id, type.name, type.type, type.size, type.link)
    }

    /**
     * Map a [User] instance to a [UserEntity] instance
     */
    override fun mapToEntity(type: PhotoBody): PhotoEntity {
        return PhotoEntity(type.id, type.name, type.type, type.size, type.link)
    }


}