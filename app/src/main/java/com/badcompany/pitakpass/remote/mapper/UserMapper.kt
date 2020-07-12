package com.badcompany.remote.mapper

import com.badcompany.data.model.UserCredentialsEntity
import com.badcompany.data.model.UserEntity
import com.badcompany.remote.model.UserCredentialsModel
import com.badcompany.remote.model.UserInfoModel
import com.badcompany.remote.model.UserModel
import javax.inject.Inject

/**
 * Map a [UserModel] to and from a [UserEntity] instance when data is moving between
 * this later and the Data layer
 */
open class UserMapper @Inject constructor(): EntityMapper<UserModel, UserEntity> {

    /**
     * Map an instance of a [UserModel] to a [UserEntity] model
     */
    override fun mapToEntity(type: UserModel): UserEntity {
        return UserEntity(type.phoneNum, type.name, type.surname, type.role)
    }

    override fun mapFromEntity(type: UserEntity): UserModel {
        return UserModel(type.phoneNum, type.name, type.surname, type.role)
    }


}