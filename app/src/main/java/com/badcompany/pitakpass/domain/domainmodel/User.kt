package com.badcompany.pitakpass.domain.domainmodel

data class User (
    val phoneNum:String,
    val name:String,
    val surname:String,
//    val username:String,
//    val password:String,
    val role : String
)