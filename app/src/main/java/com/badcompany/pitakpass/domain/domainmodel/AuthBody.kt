package com.badcompany.domain.domainmodel

data class AuthBody(val phoneNum: String? = null,
                    val name: String? = null,
                    val surname: String? = null,
                    val jwt: String? = null,
                    val role: String? = null)