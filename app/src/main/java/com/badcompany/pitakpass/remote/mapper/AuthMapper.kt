package com.badcompany.pitakpass.remote.mapper

import com.badcompany.pitakpass.data.model.AuthEntity
import com.badcompany.pitakpass.data.model.UserCredentialsEntity
import com.badcompany.pitakpass.remote.model.UserCredentialsModel
import com.badcompany.pitakpass.remote.model.UserInfoModel
import javax.inject.Inject

/**
 * Map a [UserInfoModel] to and from a [AuthEntity] instance when data is moving between
 * this later and the Data layer
 */
open class AuthMapper @Inject constructor(): EntityMapper<UserInfoModel, AuthEntity> {

    /**
     * Map an instance of a [UserInfoModel] to a [AuthEntity] model
     */
    override fun mapToEntity(type: UserInfoModel): AuthEntity {
        return AuthEntity(type.phoneNum, type.name,type.jwt, type.surname,  type.role)
    }

    override fun mapFromEntity(type: AuthEntity): UserInfoModel {
        return UserInfoModel(type.phoneNum, type.name, type.surname, type.jwt, type.role)
    }


}