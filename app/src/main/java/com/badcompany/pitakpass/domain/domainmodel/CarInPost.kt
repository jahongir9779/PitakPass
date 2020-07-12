package com.badcompany.domain.domainmodel

/**
 * Representation for a [CarInPost] fetched from the API
 */
data class CarInPost( var id: Long? = null,
                      var modelId: Long? = null,
                      var image: Image? = null,
                      var carModel: CarModelBody? = null,
                      var fuelType: String? = null,
                      var colorId: Long? = null,
                      var carNumber: String? = null,
                      var carYear: Int? = null,
                      var airConditioner: Boolean? = null)
