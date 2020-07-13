package com.badcompany.pitakpass.domain.domainmodel
import com.badcompany.pitakpass.util.Constants

/**
 * Representation for a [PassengerPost] fetched from the API
 */
data class PassengerPost(val id: Long?=null,
                         val from: Place,
                         val to: Place,
                         val price: Int,
                         val departureDate: String,
                         val createdDate: String,
                         val updatedDate: String,
                         val finishedDate: String?=null,
                         val timeFirstPart: Boolean,
                         val timeSecondPart: Boolean,
                         val timeThirdPart: Boolean,
                         val timeFourthPart: Boolean,
                         val airConditioner: Boolean,
                         val remark: String,
                         val postStatus: String,
                         val seat: Int,
                         val postType: String = Constants.PASSENGER_POST_SIMPLE)