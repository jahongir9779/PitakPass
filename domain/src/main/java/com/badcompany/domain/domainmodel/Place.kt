package com.badcompany.domain.domainmodel

data class Place(val districtId: Int? = null,
                 val regionId: Int? = null,
                 val lat: Double? = null,
                 val lon: Double? = null,
                 val regionName: String? = null,
                 val name: String? = null)