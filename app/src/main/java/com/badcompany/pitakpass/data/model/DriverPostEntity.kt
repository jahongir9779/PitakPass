package com.badcompany.pitakpass.data.model

import com.badcompany.pitakpass.util.Constants

data class DriverPostEntity(val id: Long? = null,
                            val from: PlaceEntity,
                            val to: PlaceEntity,
                            val price: Int,
                            val departureDate: String,
                            val finishedDate: String?=null,
                            val timeFirstPart: Boolean,
                            val timeSecondPart: Boolean,
                            val timeThirdPart: Boolean,
                            val timeFourthPart: Boolean,
                            val carId: Long?=null,
                            val car: CarInPostEntity?=null,
                            val remark: String,
                            val seat: Int,
                            val postType: String = Constants.DRIVER_POST_SIMPLE)

